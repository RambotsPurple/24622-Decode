package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.config.Paths.Paths;
import org.firstinspires.ftc.teamcode.config.Subsystem.Drive;
import org.firstinspires.ftc.teamcode.config.Subsystem.Intake;

/**
 * A complex auto command that drives forward,
 * releases a stone, and then drives backward.
 */
public class AutoCommandGroup extends SequentialCommandGroup {
    Paths p = new Paths(follower);

    public AutoCommandGroup()
    {
        addCommands(
                new FollowPathCommand(follower, p.startToShoot),
                new WaitCommand(3000),
                new FollowPathCommand(follower, p.intakeRow1A),
                new FollowPathCommand(follower, p.intakeRow1B),
                new FollowPathCommand(follower, p.intakeRow1C)
        );
    }

}