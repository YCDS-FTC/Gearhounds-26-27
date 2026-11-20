package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.pedropathing.ivy.commands.Commands.instant;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Transfer {
    private DcMotorEx transfer;
    //return false = continue to run
    //return true = stop running

    public Transfer(HardwareMap robotMap) {
        transfer = robotMap.get(DcMotorEx.class, "Transfer");
    }

    public Command setTransfer(double power) {
        return instant(() -> {
            transfer.setPower(power);
        });
    }



}