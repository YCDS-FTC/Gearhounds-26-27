/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@Config
@TeleOp(name="shooterTest", group="Linear OpMode")
//@Disabled
public class shooterTest extends OpMode {

    //    public DcMotorEx leftFront;
//    public DcMotorEx rightFront;
//    public DcMotorEx leftBack;
//    public DcMotorEx rightBack;
    public DcMotorEx bottomMotor;
    public Servo hood;
    int shift = 1;

    public static double leftPower = 0;
    private FtcDashboard dashboard;


    public static double targetVelocity = 0;


    public static boolean direction = true;

    public double ticksPerRevolution = 28;
    public double topVelocity;
    public double topRpm;

    public double bottomVelocity;
    public double bottomRpm;

    public static double P = 0.0;
    public static double I = 0;
    public static double D = 0.00;
    public static double F = 0.;

    public static double hoodPosition = 0;


    private static PIDFController shooterController = new PIDFController(P, I, D, F);

    @Override
    public void init() {



        bottomMotor = hardwareMap.get(DcMotorEx.class, "shooterBottom");
        bottomMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        bottomMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        hood = hardwareMap.get(Servo.class,"hood");

        shift = 0;


        dashboard = FtcDashboard.getInstance();
        dashboard.setTelemetryTransmissionInterval(25);
        shooterController.setPIDF(P,I,D,F);

    }
    @Override
    public void loop(){
//            if (gamepad1.dpad_up) {
//                right.setPower(rightPower);
//                left.setPower(leftPower);
//            }

        hood.setPosition(hoodPosition);

        if (direction == true){
            bottomMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        } else{
            bottomMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        shooterController.setPIDF(P,I,D,F);




        bottomVelocity = bottomMotor.getVelocity();
        bottomRpm = bottomVelocity/ticksPerRevolution * 60;


//
        double output = shooterController.calculate(
                bottomMotor.getVelocity(), targetVelocity
        );
        bottomMotor.setPower(output);


//        double output1 = shooterController.calculate(
//               topVelocity, targetVelocity
//        );
//        topMotor.setPower(output1);


//        topMotor.setPower(gamepad1.right_stick_y);
//





        telemetry.addData("leftRPM", "%f", bottomMotor.getVelocity());
        telemetry.addData("RPM", bottomRpm);
        telemetry.update();

        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        dashboardTelemetry.addData("bottomRpm", bottomRpm );
        dashboardTelemetry.addData("targetVelocity", targetVelocity);
        dashboardTelemetry.addData("bottomVelocity", bottomMotor.getVelocity());




        dashboardTelemetry.update();

    }
}

