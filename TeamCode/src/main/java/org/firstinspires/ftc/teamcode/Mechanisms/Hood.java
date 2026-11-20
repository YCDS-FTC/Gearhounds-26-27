package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static com.pedropathing.ivy.commands.Commands.instant;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.MAX_HOOD_ANGLE;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.MIN_HOOD_ANGLE;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Hood {
    private Servo hood;
    //return false = continue to run
    //return true = stop running

    public Hood(HardwareMap robotMap) {
        hood = robotMap.get(Servo.class,"Hood");
    }

    public Command setHood(double angle) {
        double clampedAngle = Range.clip(angle, MIN_HOOD_ANGLE, MAX_HOOD_ANGLE);
        return instant(() -> {
            hood.setPosition(clampedAngle);
        });
    }


}