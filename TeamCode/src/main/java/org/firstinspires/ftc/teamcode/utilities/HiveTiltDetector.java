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
            }
            else {
                AprilTagClusterDetection clusterDet = (AprilTagClusterDetection) detection;
                telemetry.addLine(String.format("\n==== (percent of cluster %d) %s", clusterDet.percentClusterFound, clusterDet.metadata.name));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
            }
        }
    }
}
