package org.firstinspires.ftc.teamcode.Tuners;


import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_D;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_F;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_I;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_P;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Config
@Utility(name = "ShooterPIDFTuner")
public class ShooterPIDFTuner extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    private FtcDashboard dashboard;
    Shooter shooter = new Shooter(robot);
    public static double target = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
        telemetry.addLine("Use this file to tune the constants on the shooter");
        telemetry.addData("P", SHOOT_P);
        telemetry.addData("I", SHOOT_I);
        telemetry.addData("D", SHOOT_D);
        telemetry.addData("F", SHOOT_F);
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        Scheduler.schedule(shooter.setShooter(target));
        packet.put("target", target);
        packet.put("velocity", robot.shooter.getVelocity());
        dashboard.sendTelemetryPacket(packet);
    }

}
