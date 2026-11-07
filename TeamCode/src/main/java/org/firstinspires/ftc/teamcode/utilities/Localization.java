//package org.firstinspires.ftc.teamcode.utilities;
//
//import com.pedropathing.math.Pose;
//
//public class Localization {
//    private double turretForwardOffset = 0;
//    private double turretLeftOffset = 0;
//
//    public Pose getTurretFieldCoords(){
//        double turretFieldOffsetX = (turretForwardOffset * Math.cos(heading)) - (turretLeftOffset * Math.sin(heading));
//        double turretFieldOffsetY = (turretForwardOffset * Math.sin(heading)) + (turretLeftOffset * Math.cos(heading));
//
//        // Add robot's field-centric pose to get turret's actual field position
//        double turretFieldX = robotX + turretFieldOffsetX;
//        double turretFieldY = robotY + turretFieldOffsetY;
//        return new Pose (turretFieldX, turretFieldY);
//    }
//
//    public
//
//
//
//}
