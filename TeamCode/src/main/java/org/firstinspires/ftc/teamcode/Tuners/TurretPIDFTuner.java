package org.firstinspires.ftc.teamcode.Tuners;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_D;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_F;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_I;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_P;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Utility(name = "TurretPIDFTuner")
public class TurretPIDFTuner extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    private FtcDashboard dashboard;
    Turret turret = new Turret(robot);
    private static double target = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addLine("Use this file to tune the constants on the turret");
        telemetry.addLine("-----WARNING----- \n When you activate this file the turret will go to its 0 position \n make sure to use the TurretAngleTuner first");
        telemetry.addData("P", TURRET_P);
        telemetry.addData("I", TURRET_I);
        telemetry.addData("D", TURRET_D);
        telemetry.addData("F", TURRET_F);
    }

    @Override
    public void init_loop(){
        telemetry.addData( "current turret angle", turret.getTurretAngle());
        telemetry.update();
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        Scheduler.schedule(turret.aimTurret(target));
        packet.put("target", target);
        packet.put("angle", turret.getTurretAngle());
        dashboard.sendTelemetryPacket(packet);
    }

}
