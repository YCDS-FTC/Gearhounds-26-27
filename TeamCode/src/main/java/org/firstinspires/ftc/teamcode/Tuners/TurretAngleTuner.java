package org.firstinspires.ftc.teamcode.Tuners;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Utility;

//import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.Mechanisms.Turret;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;

@Config
@Utility(name = "TurretTuner")
public class TurretAngleTuner extends OpMode {

    Turret turret = new Turret(hardwareMap);

    @Override
    public void init() {
        telemetry.addLine("Turn the turret to where you want the zero position to be and record this value \n take that value and put it as the TURRET_OFFSET_DEG \n press start to begin");
    }

    @Override
    public void loop() {
        telemetry.addData("tuuret angle",turret.getTurretAngle());
        telemetry.update();
    }

}
