package org.firstinspires.ftc.teamcode.Tuners;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Utility(name = "TurretTuner")
public class TurretAngleTuner extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    Turret turret = new Turret(robot);

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry.addLine("Turn the turret to where you want the zero position to be and record this value \n take that value and put it as the TURRET_OFFSET_DEG \n press start to begin");
    }

    @Override
    public void loop() {
        telemetry.addData("tuuret angle",turret.getTurretAngle());
        telemetry.update();
    }

}
