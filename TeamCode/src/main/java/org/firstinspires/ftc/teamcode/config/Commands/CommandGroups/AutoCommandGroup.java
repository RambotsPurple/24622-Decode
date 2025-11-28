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

    /**
     * Creates a new AutoCommandGroup command group.
     *
     * @param drive The drive subsystem this command will run on
     * @param intake The gripper subsystem this command will run on
     */
    public AutoCommandGroup(Drive drive, Intake intake)
    {
        addCommands(
                new FollowPathCommand(follower, p.startToShoot),
                new WaitCommand(3000),
                new FollowPathCommand(follower, p.intakeRow1A),
                new FollowPathCommand(follower, p.intakeRow1B),
                new FollowPathCommand(follower, p.intakeRow1C)
        );
        addRequirements(drive, intake);
    }

}