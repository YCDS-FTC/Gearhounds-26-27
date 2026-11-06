package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_CLOSED_POSITION;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_CYCLE_TIME_MS;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_OPEN_POSITION;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Gate {
    private final GearhoundsHardware robot;
    //return false = continue to run
    //return true = stop running

    public Gate(GearhoundsHardware robot) {
        this.robot = robot;
    }

    public Command cycleGate(double power) {
        return sequential(
                openGate(),
                wait,
                closeGate()
        );
    }

    private Command openGate(){
        return instant(() -> {
           robot.gate.setPosition(GATE_OPEN_POSITION);
        });
    }

    private Command closeGate(){
        return instant(() ->{
           robot.gate.setPosition(GATE_CLOSED_POSITION);
        });
    }

    private Command wait = waitMs(GATE_CYCLE_TIME_MS); // waits 500ms

    public Command setGate(double position){
        return instant(() -> {
            robot.gate.setPosition(position);
        });
    }

}