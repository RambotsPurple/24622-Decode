package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.OTOSConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class    Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(5);
    //@TODO get the vel from forward Velocity tuner
    //.xVelocity(velocity)
    //@Todo get teh vel from lateral vel tuner
    //.yVelocity(velocity)
    //@Todo get from FZPA
    //.forwardZeroPowerAcceleration(deceleration)
    //@Todo get from LZPA
    //.lateralZeroPowerAcceleration(deceleration)
//    .useSecondaryTranslationalPIDF(true)
//    .useSecondaryHeadingPIDF(true)
//    .useSecondaryDrivePIDF(true)
//    @TODO PLEASE FOR THE LOVE OF DEFENSE CONTRACTORS READ THE PID DOCS AND TUNING TUTS
//https://pedropathing.com/docs/pathing/tuning/pids

    //    @TODO find the relative measurements from the center dummy
    public static OTOSConstants localizerConstants = new OTOSConstants()
            .hardwareMapName("otos")
            .linearUnit(DistanceUnit.INCH)
//            .linearScalar(multiplier)
//            .angularScalar(multiplier)
            .offset(new SparkFunOTOS.Pose2D(0, 0, 0))
            .angleUnit(AngleUnit.DEGREES);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rightFront")
            .rightRearMotorName("rightBack")
            .leftRearMotorName("leftBack")
            .leftFrontMotorName("leftFront")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .OTOSLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)

                .build();
    }


}