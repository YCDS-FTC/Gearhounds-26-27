package org.firstinspires.ftc.teamcode;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import static com.pedropathing.ivy.Scheduler.schedule;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.shift;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;

import org.firstinspires.ftc.teamcode.Mechanisms.Gate;
import org.firstinspires.ftc.teamcode.Mechanisms.Hood;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Shooter;
import org.firstinspires.ftc.teamcode.Mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@TeleOp(name = "Teleop", group = "Example")
public class Teleop extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    private Follower follower;
    Gate gate = new Gate(robot);
    Hood hood = new Hood(robot);
    Intake intake = new Intake(robot);
    Shooter shooter = new Shooter(robot);
    Transfer transfer = new Transfer(robot);
    Turret turret = new Turret(robot);

    @Override
    public void init() {


        robot.init(hardwareMap);
    }

    @Override
    public void start(){
        Scheduler.schedule(intake.setIntake(1));
    }

    @Override
    public void loop() {

        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y * shift,
                gamepad1.left_stick_x * shift,
                gamepad1.right_stick_x * shift,
                follower.pose().heading()
        );

        ManualDrive.driveOrHold(follower, powers);
        follower.update();

        if (gamepad1.yWasPressed()){
            Scheduler.schedule(intake.setIntake(-1));
        } else if (gamepad1.yWasReleased()) {
            Scheduler.schedule(intake.setIntake(1));
        }

        if(gamepad1.left_trigger > 0.1){
            shift = 0.3;
        }else shift = 1;


    }

}
