package org.firstinspires.ftc.teamcode.Mechanisms;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;

import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_CLOSED_POSITION;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_CYCLE_TIME_MS;
import static org.firstinspires.ftc.teamcode.utilities.RobotConstants.GATE_OPEN_POSITION;

import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

public class Gate {
    private Servo gate;
    //return false = continue to run
    //return true = stop running

    public Gate(HardwareMap robotMap) {
        gate = robotMap.get(Servo.class, "Gate");
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
           gate.setPosition(GATE_OPEN_POSITION);
        });
    }

    private Command closeGate(){
        return instant(() ->{
           gate.setPosition(GATE_CLOSED_POSITION);
        });
    }

    private Command wait = waitMs(GATE_CYCLE_TIME_MS); // waits 500ms

    public Command setGate(double position){
        return instant(() -> {
           gate.setPosition(position);
        });
    }

}