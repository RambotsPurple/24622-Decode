package org.firstinspires.ftc.teamcode.config.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class   Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
    //@TODO measure the mass
    .mass(12.247)

    //@Todo get from FZPA
    .forwardZeroPowerAcceleration(-41.08996471425825)
    //@Todo get from LZPA
    .lateralZeroPowerAcceleration(-81.5064706014293)
    .translationalPIDFCoefficients(new PIDFCoefficients(0.05, 0, 0.01, 0.025))
    .headingPIDFCoefficients(new PIDFCoefficients(0.95, 0, 0.01, 0.05))
    .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.0,0.0,0.0,0.5,0.3))
    .centripetalScaling(0.00009);

    //    @TODO tune

    public static PinpointConstants localizerConstants = new PinpointConstants()
            // manual change the offsets
            .forwardPodY(-0.25)
            .strafePodX(-2.875)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rf")
            .rightRearMotorName("rb")
            .leftRearMotorName("lb")
            .leftFrontMotorName("lf")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            //@TODO get the vel from forward Velocity tuner
             .xVelocity(48.56584203524853)
            //@Todo get teh vel from lateral vel tuner
            .yVelocity(33.4133380078894);
    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }//end of createFollower


}//end of Constants class
