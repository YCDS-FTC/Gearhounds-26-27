//package org.firstinspires.ftc.teamcode.Tuners;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.pedropathing.ivy.Scheduler;
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.Utility;
//
//import org.firstinspires.ftc.teamcode.Mechanisms.Hood;
//import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;
//
//@Config
//@Utility(name = "HoodTuner")
//public class HoodTuner extends OpMode {
//
//    private final GearhoundsHardware robot = new GearhoundsHardware();
//    Hood hood = new Hood(robot);
//    public static double position = 0;
//
//    @Override
//    public void init() {
//        robot.init(hardwareMap);
//        telemetry.addLine("Use this file to tune the position of the hood");
//        telemetry.addLine("-----WARNING----- \n When you activate this file the hood will go to its 0 position");
//    }
//
//    @Override
//    public void loop() {
//        Scheduler.schedule(hood.setHood(position));
//    }
//
//}
