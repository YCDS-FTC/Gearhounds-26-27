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

    public double normA(double angle) {angle %= 360; if (angle < -180) angle += 360; else if (angle > 180) angle -= 360;return angle;}
    double target = normA(angleWant - robotHeading);
        if (target > 140) {target = 140;} else if (target < -140) {target = -140;}
    //TODO write a util op-mode to center the turret

}