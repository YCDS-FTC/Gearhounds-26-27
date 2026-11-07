package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;


// Generic robot class
public class GearhoundsHardware extends hardware {
    public HardwareMap robotMap;

// Examples Here
/*  public DcMotorEx ExampleMotor;
    public Servo ExampleServo;
*/

    public DcMotorEx intake;
    public DcMotorEx transfer;
    public DcMotorEx shooter;
    public DcMotorEx turret;
    public AnalogInput turretEncoder;
//    public Servo hood;
//    public Servo gate;
    public Servo flower;
    // Override to set actual robot configuration
    public void init(HardwareMap hwMap) {
        robotMap = hwMap;
/*      ExampleServo = robotMap.get(Servo.class, "ExampleServo");
        ExampleMotor = robotMap.get(DcMotorEx.class, "ExampleMotor");
        NameInCodeHere = robotMap.get(DeviceTypeHere.class, "DriverStationNameHere");
*/
        intake = robotMap.get(DcMotorEx.class, "Intake");
        transfer = robotMap.get(DcMotorEx.class, "Transfer");
//        shooter = robotMap.get(DcMotorEx.class, "Shooter");
        turret = robotMap.get(DcMotorEx.class, "Turret");
        turretEncoder = robotMap.get(AnalogInput.class, "TurretEncoder");
//        hood = robotMap.get(Servo.class,"Hood");
//        gate = robotMap.get(Servo.class, "Gate");
        flower = robotMap.get(Servo.class, "Flower");

        List<LynxModule> allHubs = robotMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

    }


    private DcMotorEx initMotor(String name, DcMotor.Direction direction) {
        DcMotorEx motor = robotMap.get(DcMotorEx.class, name);
        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return motor;
    }
}

