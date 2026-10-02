package org.firstinspires.ftc.teamcode.utilities;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.hardware.GearhoundsHardware;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;

@TeleOp(name = "HiveTiltDetector")
public class HiveTiltDetector extends OpMode {

    private final GearhoundsHardware robot = new GearhoundsHardware();
    public Position cameraPosition = new Position(DistanceUnit.INCH,
            0, 0, 0, 0);
    public YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
            0, 0, 0, 0);
    public VisionPortal visionPortal;
    public AprilTagProcessor tagProcessor;
    public double redScoringPitch;
    public double redAudiencePitch;
    public double blueScoringPitch;
    public double blueAudiencePitch;
    public double upAngle = 30;
    public double downAngle = 210;
    enum redTilt {
        SCORING,
        TILTING,
        AUDIENCE
    }
    enum blueTilt {
        SCORING,
        TILTING,
        AUDIENCE
    }
    public redTilt redTiltPos;
    public blueTilt blueTiltPos;

    @Override
    public void init() {
        robot.init(hardwareMap);
        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setLensIntrinsics(539.0239404, 539.0239404, 316.450283269, 236.36479005)
                .setCameraPose(cameraPosition, cameraOrientation)
                .build();
        visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
//                .enableLiveView(true)
                .build();
    }

    @Override
    public void loop() {
        List<AprilTagDetection> detections = tagProcessor.getDetections();


        for (AprilTagDetection detection : detections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;
                telemetry.addLine(String.format("\n==== (ID %d) %s", singleDet.id, singleDet.metadata.name));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                if(singleDet.metadata.name.contains("red scoring")){
                    redScoringPitch = singleDet.ftcPose.pitch;
                }
                if(singleDet.metadata.name.contains("red audience")){
                    redAudiencePitch = singleDet.ftcPose.pitch;
                }
                if(singleDet.metadata.name.contains("blue scoring")){
                    blueScoringPitch = singleDet.ftcPose.pitch;
                }
                if(singleDet.metadata.name.contains("blue audience")){
                    blueAudiencePitch = singleDet.ftcPose.pitch;
                }
            }
            else {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;
                telemetry.addLine(String.format("\n==== (percent of cluster %d) %s", clusterDet.percentClusterFound, clusterDet.metadata.name));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                if(clusterDet.metadata.name.contains("red scoring")){
                    redScoringPitch = clusterDet.ftcPose.pitch;
                }
                if(clusterDet.metadata.name.contains("red audience")){
                    redAudiencePitch = clusterDet.ftcPose.pitch;
                }
                if(clusterDet.metadata.name.contains("blue scoring")){
                    blueScoringPitch = clusterDet.ftcPose.pitch;
                }
                if(clusterDet.metadata.name.contains("blue audience")){
                    blueAudiencePitch = clusterDet.ftcPose.pitch;
                }

            }


            if(blueScoringPitch >= upAngle || blueAudiencePitch <= downAngle){
                blueTiltPos = blueTilt.SCORING;
            } else if (blueScoringPitch <= downAngle || blueAudiencePitch >= upAngle) {
                blueTiltPos = blueTilt.AUDIENCE;
            }else{
                blueTiltPos = blueTilt.TILTING;
            }

            if(redScoringPitch >= upAngle || redAudiencePitch <= downAngle){
                redTiltPos = redTilt.SCORING;
            } else if (redScoringPitch <= downAngle || redAudiencePitch >= upAngle) {
                redTiltPos = redTilt.AUDIENCE;
            }else{
                redTiltPos = redTilt.TILTING;
            }
        }
    }
}
