package org.firstinspires.ftc.teamcode.Tuners;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_D;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_F;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_I;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.TURRET_P;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Config
@Utility(name = "TurretPIDFTuner", description = "Use this file to tune the constants on the turret")
public class TurretPIDFTuner extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    private FtcDashboard dashboard;
    Turret turret = new Turret(hardwareMap);
    public static double target = 0;
    PIDFController turretController;


    @Override
    public void init() {
        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
        turretController = new PIDFController(TURRET_P,TURRET_I,TURRET_D,TURRET_F);
        Scheduler.reset();
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
        Scheduler.reset();
        telemetry.update();
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        Scheduler.schedule(turret.aimTurret(target));
//        turret.updatePID();
        packet.put("target", target);
        packet.put("angle", turret.getTurretAngle());
        Scheduler.execute();
        dashboard.sendTelemetryPacket(packet);
    }

}
