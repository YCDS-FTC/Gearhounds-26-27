package org.firstinspires.ftc.teamcode.Mechanisms;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Intake {
    private final GearhoundsHardware robot;
    //return false = continue to run
    //return true = stop running

    public Intake(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command setIntake(double power) {
        return instant(() -> {
            robot.intake.setPower(power);
        });
    }


}
