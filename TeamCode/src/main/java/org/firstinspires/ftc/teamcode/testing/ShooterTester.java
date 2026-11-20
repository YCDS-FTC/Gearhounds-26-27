package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.teamcode.Mechanisms.Hood;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Config
@Utility(name = "ShooterTester")
public class ShooterTester extends OpMode {
    private final GearhoundsHardware robot = new GearhoundsHardware();
    private FtcDashboard dashboard;
    public static double shooterTarget;
    public static double hoodTarget;
    public static double turretTarget;
    Turret turret = new Turret(hardwareMap);
    Shooter shooter = new Shooter(hardwareMap);
    Hood hood = new Hood(hardwareMap);

    @Override
    public void init() {
        robot.init(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
        Scheduler.reset();

    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        Scheduler.schedule(shooter.setShooter(shooterTarget));
        Scheduler.schedule(hood.setHood(hoodTarget));
        Scheduler.schedule(turret.aimTurret(turretTarget));
        Scheduler.execute();
        packet.put("shooterTarget", shooterTarget);
        packet.put("shooterVelocity", shooter.getVelocity());
        packet.put("turretTarget", turretTarget);
        packet.put("turretAngle", turret.getTurretAngle());
        dashboard.sendTelemetryPacket(packet);

    }

}
