package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;

public class FollowPathCommand extends CommandBase {

    private final Follower follower;
    private final PathChain path;

    public FollowPathCommand(Follower follower, PathChain path) {
        this.follower = follower;
        this.path = path;
    }

    @Override
    public void initialize() {
        follower.followPath(path);
    }

    @Override
    public void execute() {
        follower.update();  // keep path following active
    }

    // idk why exsurgo doesn't have an ifFinished but according to FTClib docs if u dont have this the command will never end :(
    @Override
    public boolean isFinished() {
        return !follower.isBusy();
    }

}