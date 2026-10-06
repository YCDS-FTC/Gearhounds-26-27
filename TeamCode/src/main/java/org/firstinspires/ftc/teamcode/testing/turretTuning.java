package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.hardware.AnalogInput;

public class turretTuning {

    private static final double REF_VOLTAGE = 3.3;


    public static double getEncoderAngle(AnalogInput encoder) {
        return encoder.getVoltage() / REF_VOLTAGE * 360.0;
    }

//    public static double closestAngle(double currentAngle, double targetAngle) {
//        // normalize both angles into 0-360, using true modulo, not Java's %
//        currentAngle = modulo(currentAngle);
//        targetAngle = modulo(targetAngle);
//
//        double safetarget = targetAngle - currentAngle;
//
//        // convert from -360..360 to -180..180
//        if (Math.abs(direction) > 180.0) {
//            direction = direction - Math.signum(direction) * 360.0;
//        }
//        return direction;
//    }



    private static double modulo(double angle) {
        double result = angle % 360.0;
        if (result < 0) {
            result += 360.0;
        }
        return result;
    }

}
