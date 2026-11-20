package org.firstinspires.ftc.teamcode.Mechanisms;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_D;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_F;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_I;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.SHOOT_P;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Shooter {
    private DcMotorEx shooter;
    private final PIDFController shooterController;
    //return false = continue to run
    //return true = stop running

    public Shooter(HardwareMap robotMap) {
        shooter = robotMap.get(DcMotorEx.class, "Shooter");
        this.shooterController = new PIDFController(SHOOT_P, SHOOT_I, SHOOT_D, SHOOT_F);
    }


    public Command setShooter(double target) {
        return Command.build()
                .setExecute(() -> {
                    if (target <= 0) {
                        shooter.setPower(0);
                    } else {
//                        shooterController.setPIDF(SHOOT_P, SHOOT_I,SHOOT_D,SHOOT_F);
//                        robot.shooter.setPower(shooterController.calculate(robot.shooter.getVelocity(), target));
                        shooter.setPower(SHOOT_P);
                    }
                })
                .requiring(this);
    }

    public double getVelocity(){
        return shooter.getVelocity();
    }
}