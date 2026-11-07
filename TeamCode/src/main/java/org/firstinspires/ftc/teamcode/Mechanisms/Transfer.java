package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.instant;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Transfer {
    private final GearhoundsHardware robot;
    //return false = continue to run
    //return true = stop running

    public Transfer(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command setTransfer(double power) {
        return instant(() -> {
            robot.transfer.setPower(power);
        });
    }



}