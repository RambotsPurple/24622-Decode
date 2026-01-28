package org.firstinspires.ftc.teamcode.config.Util.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.config.Util.Alliance;

public class TestPathAuto {

    private final Follower follower;
    private int index = 0;

    /* -------------------- Poses -------------------- */

    public Pose startPose      = new Pose(56.000, 8.000, Math.toRadians(90));
    public Pose scorePose      = new Pose(56.000, 8.000, Math.toRadians(115));

    public Pose lineup1        = new Pose(40.126, 35.272, Math.toRadians(180));
    public Pose intake1        = new Pose(18.712, 35.306, Math.toRadians(180));

    public Pose lineup3        = new Pose(41.238, 60.391, Math.toRadians(180));
    public Pose intake3        = new Pose(21.906, 60.040, Math.toRadians(180));

    public Pose observation    = new Pose(11.452, 8.283, Math.toRadians(180));
    public Pose exit           = new Pose(56.321, 23.470, Math.toRadians(180));

    /* -------------------- Constructor -------------------- */

    public Pose startPose(){
        return startPose;
    }
    public TestPathAuto(Follower follower, Alliance alliance) {
        this.follower = follower;

        if (alliance == Alliance.RED) {
            startPose   = startPose.mirror();
            scorePose   = scorePose.mirror();
            lineup1     = lineup1.mirror();
            intake1     = intake1.mirror();
            lineup3     = lineup3.mirror();
            intake3     = intake3.mirror();
            observation = observation.mirror();
            exit        = exit.mirror();
        }
    }

    /* -------------------- PathChains -------------------- */

    public PathChain start() {
        return follower.pathBuilder()
                .addPath(new BezierLine(startPose, scorePose))
                .setLinearHeadingInterpolation(
                        startPose.getHeading(),
                        scorePose.getHeading()
                )
                .build();
    }

    public PathChain lineup1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePose, lineup1))
                .setLinearHeadingInterpolation(
                        scorePose.getHeading(),
                        lineup1.getHeading()
                )
                .build();
    }

    public PathChain intake1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(lineup1, intake1))
                .setTangentHeadingInterpolation()
                .build();
    }

    public PathChain returnFromIntake1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake1, scorePose))
                .setLinearHeadingInterpolation(
                        intake1.getHeading(),
                        scorePose.getHeading()
                )
                .build();
    }

    public PathChain lineup3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePose, lineup3))
                .setLinearHeadingInterpolation(
                        scorePose.getHeading(),
                        lineup3.getHeading()
                )
                .build();
    }

    public PathChain intake3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(lineup3, intake3))
                .setTangentHeadingInterpolation()
                .build();
    }

    public PathChain observation() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePose, observation))
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public PathChain exit() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePose, exit))
                .setTangentHeadingInterpolation()
                .build();
    }

    /* -------------------- Sequencer -------------------- */

    public PathChain next() {
        switch (index++) {
            case 0: return start();
            case 1: return lineup1();
            case 2: return intake1();
            case 3: return returnFromIntake1();
            case 4: return lineup3();
            case 5: return intake3();
            case 6: return observation();
            case 7: return exit();
            default: return null;
        }
    }
}
