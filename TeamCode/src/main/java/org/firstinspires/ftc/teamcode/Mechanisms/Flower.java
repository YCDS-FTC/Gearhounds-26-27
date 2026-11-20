package org.firstinspires.ftc.teamcode.Mechanisms;

import static com.pedropathing.ivy.commands.Commands.instant;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.FLOWER_DOWN_POS;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.FLOWER_UP_POS;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Flower {
    private GearhoundsHardware robot;
    private Servo flower;

    public Flower(HardwareMap hwMap) {
        flower = hwMap.get(Servo.class, "Flower");
    }

    public Command flowerUp = instant(() -> flower.setPosition(FLOWER_UP_POS));
    public Command flowerDown = instant(() -> flower.setPosition(FLOWER_DOWN_POS));
    public Command setFlower(double position){
        return instant(() -> {
            flower.setPosition(position);
        });
    }
}
