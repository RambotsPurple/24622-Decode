package org.firstinspires.ftc.teamcode.config.Util.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

import org.firstinspires.ftc.teamcode.config.Util.Alliance;

public class AutoClose {

    private final Follower follower;
    private final Alliance alliance;

    /* -------------------- Poses -------------------- */

    public Pose start        = new Pose(117.950, 130.488, Math.toRadians(48));

    public Pose scorePos     = new Pose(86.505, 85.025,  Math.toRadians(48));

    public Pose lineUp1      = new Pose(105.289, 59.736, Math.toRadians(0));
    public Pose intake1      = new Pose(125.022, 59.516, Math.toRadians(0));

    public Pose intake2      = new Pose(132.820, 59.903, Math.toRadians(38));

    public Pose lineUp3      = new Pose(96.612, 84.765,  Math.toRadians(0));
    public Pose intake3      = new Pose(128.816, 83.930, Math.toRadians(0));

    public Pose resetPose    = new Pose(71.917, 96.111,  Math.toRadians(0));

    public int index = 0;

    /* -------------------- Constructor -------------------- */

    public AutoClose(Follower follower, Alliance alliance) {
        this.follower = follower;
        this.alliance = alliance;

        if (alliance == Alliance.RED) {
            start     = start.mirror();
            scorePos  = scorePos.mirror();
            lineUp1   = lineUp1.mirror();
            intake1   = intake1.mirror();
            intake2   = intake2.mirror();
            lineUp3   = lineUp3.mirror();
            intake3   = intake3.mirror();
            resetPose = resetPose.mirror();
        }
    }

    public Pose returnStart() {
        return start;
    }

    /* -------------------- PathChains -------------------- */

    public PathChain launch1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(start, scorePos))
                .setConstantHeadingInterpolation(start.getHeading())
                .build();
    }

    public PathChain lineUp1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePos, lineUp1))
                .setLinearHeadingInterpolation(scorePos.getHeading(), lineUp1.getHeading())
                .build();
    }

    public PathChain intake1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(lineUp1, intake1))
                .setConstantHeadingInterpolation(intake1.getHeading())
                .build();
    }

    public PathChain return1() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake1, scorePos))
                .setLinearHeadingInterpolation(intake1.getHeading(), scorePos.getHeading())
                .build();
    }

    public PathChain intake2() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePos, intake2))
                .setLinearHeadingInterpolation(scorePos.getHeading(), intake2.getHeading())
                .build();
    }

    public PathChain launch2() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake2, scorePos))
                .setConstantHeadingInterpolation(scorePos.getHeading())
                .build();
    }

    public PathChain lineUp3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePos, lineUp3))
                .setLinearHeadingInterpolation(scorePos.getHeading(), lineUp3.getHeading())
                .build();
    }

    public PathChain intake3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(lineUp3, intake3))
                .setConstantHeadingInterpolation(intake3.getHeading())
                .build();
    }

    public PathChain launch3() {
        return follower.pathBuilder()
                .addPath(new BezierLine(intake3, scorePos))
                .setLinearHeadingInterpolation(intake3.getHeading(), scorePos.getHeading())
                .build();
    }

    public PathChain reset() {
        return follower.pathBuilder()
                .addPath(new BezierLine(scorePos, resetPose))
                .setLinearHeadingInterpolation(scorePos.getHeading(), resetPose.getHeading())
                .build();
    }

    /* -------------------- Sequencer -------------------- */

    public PathChain next() {
        switch (index++) {
            case 0: return launch1();
            case 1: return lineUp1();
            case 2: return intake1();
            case 3: return return1();
            case 4: return intake2();
            case 5: return launch2();
            case 6: return lineUp3();
            case 7: return intake3();
            case 8: return launch3();
            case 9: return reset();
            default: return null;
        }
    }
}