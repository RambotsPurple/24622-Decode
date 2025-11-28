package org.firstinspires.ftc.teamcode.config.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths { // supposed to be static?

    /*
    all paths built for blue, mirror for red
    row 1 = row of 3 balls farthest from goals
    row 2 = middle row of balls
    row 3 = row of balls closest to the goals
    shooting position = (64, 16) at 113.5 degrees
     */
    public PathChain startToShoot; // move from start to shoot 3 balls
    public PathChain intakeRow1A, intakeRow1B, intakeRow1C; // starting from shooting pos, intake row 1 of balls and move back to shoot
    public PathChain intakeRow2A, intakeRow2B, intakeRow2C; // starting from shooting pos, intake row 2 of balls and move back to shoot
    private Pose shootingPose;

    public Paths(Follower follower) {

        shootingPose = new Pose(64.000, 16.000);

        startToShoot = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(56.000, 9.000), shootingPose)
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(113.5))
                .build();

        intakeRow1A = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, new Pose(45.000, 36.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(113.5), Math.toRadians(180))
                .build();

        intakeRow1B = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(45.000, 36.000), new Pose(16.000, 36.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        intakeRow1C = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(16.000, 36.000), shootingPose)
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(113.5))
                .build();

        intakeRow2A = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(shootingPose, new Pose(45.000, 60.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(113.5), Math.toRadians(180))
                .build();

        intakeRow2B = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(45.000, 60.000), new Pose(16.000, 60.000))
                )
                .setTangentHeadingInterpolation()
                .build();

        intakeRow2C = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(16.000, 60.000), shootingPose)
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(113.5))
                .build();
    }
}