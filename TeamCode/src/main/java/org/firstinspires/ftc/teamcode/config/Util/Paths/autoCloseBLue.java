package org.firstinspires.ftc.teamcode.config.Util.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class autoCloseBLue {
    public static class Paths {

        public PathChain launch1;
        public PathChain lineUp1;
        public PathChain intake1;
        public PathChain return1;
        public PathChain intake2;
        public PathChain launch2;
        public PathChain lineup3;
        public PathChain intake3;
        public PathChain launch3;
        public PathChain reset;

        public Paths(Follower follower) {
            launch1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(117.950, 130.488), new Pose(86.505, 85.025))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(48))
                    .build();

            lineUp1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(86.505, 85.025), new Pose(105.289, 59.736))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(48), Math.toRadians(0))
                    .build();

            intake1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(105.289, 59.736), new Pose(125.022, 59.516))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            return1 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(125.022, 59.516), new Pose(86.505, 85.025))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(48))
                    .build();

            intake2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(86.505, 85.025), new Pose(132.820, 59.903))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(48), Math.toRadians(38))
                    .build();

            launch2 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(132.820, 59.903), new Pose(86.505, 85.025))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(48))
                    .build();

            lineup3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(86.505, 85.025), new Pose(96.612, 84.765))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(48), Math.toRadians(0))
                    .build();

            intake3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(96.612, 84.765), new Pose(128.816, 83.930))
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();

            launch3 = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(128.816, 83.930), new Pose(86.505, 85.025))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(48))
                    .build();

            reset = follower
                    .pathBuilder()
                    .addPath(
                            new BezierLine(new Pose(86.505, 85.025), new Pose(71.917, 96.111))
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(48), Math.toRadians(0))
                    .build();
        }
    }
}
