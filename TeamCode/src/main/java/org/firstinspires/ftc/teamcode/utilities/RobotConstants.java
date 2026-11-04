package org.firstinspires.ftc.teamcode.utilities;


import com.acmerobotics.dashboard.config.Config;

@Config
public final class RobotConstants {

    public static double SHOOT_P = 0;
    public static double SHOOT_I = 0;
    public static double SHOOT_D = 0;
    public static double SHOOT_F = 0;

    public static double TURRET_P = 0;
    public static double TURRET_I = 0;
    public static double TURRET_D = 0;
    public static double TURRET_F = 0;

    //Point the turret forward and put the angle that it returns in here to set that as the zero point
    //TURRET_OFFSET_DEG should be at 0 when you do that lowk
    public static double TURRET_OFFSET_DEG = 0;
    public static boolean TURRET_DIRECTION_REVERSED = false;
    public static double MIN_TURRET_DEG = 0;
    public static double MAX_TURRET_DEG = 0;

    public static double MIN_HOOD_ANGLE = 0;
    public static double MAX_HOOD_ANGLE = 0;

    public static double GATE_OPEN_POSITION = 0;
    public static double GATE_CLOSED_POSITION = 0;
    public static double GATE_CYCLE_TIME_MS = 0;

    public static double shift = 1;

}
