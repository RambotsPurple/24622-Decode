package org.firstinspires.ftc.teamcode.config.Util.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.config.Util.Alliance;

public class AutoClose {

    private final Follower follower;
    private int index = 0;

    /* -------------------- Poses -------------------- */

    public Pose start          = new Pose(23.548, 127.146, Math.toRadians(140));
    public Pose shootPose      = new Pose(59.000, 84.000,  Math.toRadians(135));

    public Pose intake1Lineup  = new Pose(15.000, 84.000,  Math.toRadians(180));

    public Pose lineup2        = new Pose(40.000, 60.000,  Math.toRadians(180));
    public Pose intake2        = new Pose(15.000, 60.000,  Math.toRadians(180));

    public Pose shoot3Pose     = new Pose(58.000, 84.000,  Math.toRadians(140));
    public Pose exitPose       = new Pose(50.000, 75.000,  Math.toRadians(180));

    /* -------------------- Constructor -------------------- */

    public AutoClose(Follower follower, Alliance alliance) {
        this.follower = follower;

        if (alliance == Alliance.RED) {
            start         = start.mirror();
            shootPose     = shootPose.mirror();
            intake1Lineup = intake1Lineup.mirror();
            lineup2       = lineup2.mirror();
            intake2       = intake2.mirror();
            shoot3Pose    = shoot3Pose.mirror();
            exitPose      = exitPose.mirror();
        }
    }

    /* -------------------- PathChains -------------------- */

    public PathChain shoot() {
        return follower.pathBuilder()
                .addPath(new BezierLine(start, shootPose))
                .setLinearHeadingInterpolation(
                        start.getHeading(),
                        shootPose.getHeading()
                )
                .build();
    }

    public PathChain intake1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(shootPose, intake1Lineup))
                .setTangentHeadingInterpolation()
                .build();
    }

    public PathChain shoot2() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake1Lineup, shootPose))
                .setLinearHeadingInterpolation(
                        intake1Lineup.getHeading(),
                        shootPose.getHeading()
                )
                .build();
    }

    public PathChain lineup2() {
        return follower.pathBuilder()
                .addPath(new BezierLine(shootPose, lineup2))
                .setLinearHeadingInterpolation(
                        shootPose.getHeading(),
                        lineup2.getHeading()
                )
                .build();
    }

    public PathChain intake2() {
        return follower.pathBuilder()
                .addPath(new BezierLine(lineup2, intake2))
                .setTangentHeadingInterpolation()
                .build();
    }

    public PathChain shoot3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake2, shoot3Pose))
                .setLinearHeadingInterpolation(
                        intake2.getHeading(),
                        shoot3Pose.getHeading()
                )
                .build();
    }

    public PathChain exit() {
        return follower.pathBuilder()
                .addPath(new BezierLine(shoot3Pose, exitPose))
                .setTangentHeadingInterpolation()
                .build();
    }

    /* -------------------- Sequencer -------------------- */

    public PathChain next() {
        switch (index++) {
            case 0: return shoot();
            case 1: return intake1();
            case 2: return shoot2();
            case 3: return lineup2();
            case 4: return intake2();
            case 5: return shoot3();
            case 6: return exit();
            default: return null;
        }
    }
}
