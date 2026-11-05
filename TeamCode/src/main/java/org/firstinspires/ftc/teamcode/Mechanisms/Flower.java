package org.firstinspires.ftc.teamcode.Mechanisms;

import static com.pedropathing.ivy.commands.Commands.instant;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.FLOWER_DOWN_POS;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.FLOWER_UP_POS;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Flower {
    private GearhoundsHardware robot;

    public Flower(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command flowerUp = instant(() -> robot.flower.setPosition(FLOWER_UP_POS));
    public Command flowerDown = instant(() -> robot.flower.setPosition(FLOWER_DOWN_POS));
}
