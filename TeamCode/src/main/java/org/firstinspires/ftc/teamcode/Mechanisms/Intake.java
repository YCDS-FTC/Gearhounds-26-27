package org.firstinspires.ftc.teamcode.Mechanisms;

import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Intake {
    private final GearhoundsHardware robot;
    //return false = continue to run
    //return true = stop running
    public static double intakeOn = 1;
    public static double intakeOff = 0;
    public static double intakeFar = 1;
    public intakeState intakeMode = intakeState.intakeOff;

    public Intake(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command setIntake(double power) {
        return instant(() -> {
            robot.intake.setPower(power);
        });
    }
    public Command periodic() {
        return infinite(() ->{
            switch (intakeMode){
                case intakeOn:
                    robot.intake.setPower(intakeOn);
                    break;
                case intakeOff:
                    robot.intake.setPower(intakeOff);
                    break;
                case intakeFar:
                    robot.intake.setPower(intakeFar);
                    break;
            }
        });
    }

    public enum intakeState{
        intakeOn,
        intakeOff,
        intakeFar
    }
}