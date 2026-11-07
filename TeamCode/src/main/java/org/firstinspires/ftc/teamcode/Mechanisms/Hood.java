package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.util.Range;

import static com.pedropathing.ivy.commands.Commands.instant;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.MAX_HOOD_ANGLE;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.MIN_HOOD_ANGLE;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Hood {
    private final GearhoundsHardware robot;
    //return false = continue to run
    //return true = stop running

    public Hood(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command setHood(double angle) {
        double clampedAngle = Range.clip(angle, MIN_HOOD_ANGLE, MAX_HOOD_ANGLE);
        return instant(() -> {
            robot.hood.setPosition(clampedAngle);
        });
    }


}