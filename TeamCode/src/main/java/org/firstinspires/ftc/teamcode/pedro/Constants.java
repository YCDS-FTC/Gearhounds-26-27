package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {


    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("Frontleft");
        c.frontRightName.set("Frontright");
        c.backLeftName.set("Backleft");
        c.backRightName.set("Backright");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.manualBrakeMode.set(true);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("Pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(3.884741415189007);
        c.yPodOffset.set(6.170868160217766);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.454524943083825);
                Controller secondaryTranslationalForward = Controller.proportional(0.1679348213393141);
                Controller primaryTranslationalLateral = Controller.proportional(1.9165142605283827);
                Controller secondaryTranslationalLateral = Controller.proportional(0.7081008090610442);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.016362509292919682));
                c.brake.set(Controller.proportionalFeedforward(0.01390813289898173));

                c.headingFeedback.set(Controller.proportional(7.188086549669274));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06559463075242343, 0.008193726078172254));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07233563425818657, 0.004300335419539874));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0015593583974445131, 3.300196733172731E-4));

                c.maxAchievableForwardVelocity.set(64.64650904926765);
                c.maxAchievableStrafeVelocity.set(47.53658613698421);
                c.naturalForwardDeceleration.set(60.13275650162425);
                c.naturalStrafeDeceleration.set(93.39980496417309);
            }
    );
    public static Follower create(HardwareMap h) {

        // return new Follower(Drivetrain, Localizer, Foresight);
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
        new Mecanum(h, drivetrainConfig),
        new Foresight(foresightConfig)
        );

    }
}