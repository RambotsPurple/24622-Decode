package org.firstinspires.ftc.teamcode.config.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class Paths { // supposed to be static?
// Basic Path
    public PathChain Path1, Path2; // 1 = red, 2 = blue, start at back, move up to shoot
    public PathChain Path3, Path4; // 3 = red, 4 = blue, start at top, move down to shoot
    private double shootingPositionX, shootingPositionY;

    public Paths(Follower follower) {
        shootingPositionX = 96;
        shootingPositionY = 96;
        Path1 = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(56.000, 8.000),
                                new Pose(56.000, 36.000),
                                new Pose(shootingPositionX, shootingPositionY)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                .build();

        Path2 = follower
                .pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(88.000, 8.000),
                                new Pose(88.000, 36.000),
                                new Pose(144 - shootingPositionX, shootingPositionY)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(135))
                .build();

        Path3 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(120.7, 126),
                                new Pose(shootingPositionX, shootingPositionY))
                )
                .setLinearHeadingInterpolation(Math.toRadians(38), Math.toRadians(45))
                .build();

        Path4 = follower
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(23.3, 126.000),
                                new Pose(144 - shootingPositionX, shootingPositionY))
                )
                .setLinearHeadingInterpolation(Math.toRadians(142), Math.toRadians(135))
                .build();
    }
}
