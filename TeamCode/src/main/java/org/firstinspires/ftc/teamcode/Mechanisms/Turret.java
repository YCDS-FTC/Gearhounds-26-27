package org.firstinspires.ftc.teamcode.Mechanisms;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_D;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_F;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_I;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_OFFSET_DEG;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_P;

import com.pedropathing.ivy.Command;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.sun.tools.javac.comp.Todo;


import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Turret {
    private final GearhoundsHardware robot;
    private final PIDFController turretController;
    //return false = continue to run
    //return true = stop running

    public Turret(GearhoundsHardware robot) {
        this.robot = robot;
        this.turretController = new PIDFController(TURRET_P,TURRET_I,TURRET_D,TURRET_F);
    }

    public Command aimTurret(double angle) {
        double turretAngle = getTurretAngle();
        return Command.build()
                .setExecute(() -> {
                    robot.turret.setPower(turretController.calculate(turretAngle, angle));
                });
    }




    private double getTurretAngle(){
        return (robot.turretEncoder.getVoltage() / 3.3 * 360) - TURRET_OFFSET_DEG;
    }

    /**
     * Turret math for a +/-140 deg window with a back deadzone (140..220 deg).
     * All angles in degrees. Turret 0 = facing forward.
     *
     * Assumes the abs encoder reads the turret 1:1 (no gearing in between).
     */
        public static final double LIMIT_DEG = 140.0;     // soft limit each side
        public static final double FLIP_BAND_DEG = 5.0;   // anti-flip band around the back

        private final double zeroOffsetDeg = 0; // encoder reading when turret faces forward
        private final boolean reversed = false;     // true if encoder counts opposite to turret +


        /** Fold any angle into [-180, 180). Safe for negatives and multi-turn values. */
        public static double fold180(double a) {
            return ((a + 180.0) % 360.0 + 360.0) % 360.0 - 180.0;
        }

        /** Step 1: raw abs encoder (0-360) -> turret angle theta in [-180, 180). */
        public double encoderToTurret(double encoderDeg) {
            double raw = reversed ? (zeroOffsetDeg - encoderDeg) : (encoderDeg - zeroOffsetDeg);
            return fold180(raw);
        }

        /**
         * Steps 2-6: desired field angle + robot heading + current turret angle
         * -> legal setpoint s in [-140, 140].
         */
        public double setpoint(double fieldAngleDeg, double robotHeadingDeg, double thetaDeg) {
            // Step 2: target relative to turret forward
            double phi = fold180(fieldAngleDeg - robotHeadingDeg);

            // Step 3: reachable, so go straight there
            if (Math.abs(phi) <= LIMIT_DEG) return phi;

            // Steps 5-6: target is in the back deadzone, so pick an edge.
            // Near dead-center of the back, stay on the side the turret is already on
            // so it doesn't flip 280 deg across the front.
            double sign;
            if (180.0 - Math.abs(phi) < FLIP_BAND_DEG) {
                sign = (thetaDeg >= 0) ? 1.0 : -1.0;
            } else {
                sign = (phi >= 0) ? 1.0 : -1.0;
            }
            return sign * LIMIT_DEG;
        }

        /** Step 4: error is plain subtraction. Do NOT wrap it, or it can cut through the deadzone. */
        public static double error(double setpointDeg, double thetaDeg) {
            return setpointDeg - thetaDeg;
        }

}