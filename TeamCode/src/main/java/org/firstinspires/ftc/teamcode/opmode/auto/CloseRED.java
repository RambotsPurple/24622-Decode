package org.firstinspires.ftc.teamcode.opmode.auto;


import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.SetShooterVelocityCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterVelocityByDistanceCommand;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoClose;

@Autonomous(name = "CloseRED")
public class CloseRED extends OpModeCommand {
    Robot robot;
    AutoClose path;
    @Override
    public void initialize() {
        // literally just flip alliance enum for other opMode
        robot = new Robot(hardwareMap, Alliance.RED, telemetry);
        path = new AutoClose(robot.getFollower(), robot.alliance);
        robot.aStart();
        schedule(
                new RunCommand(robot::aPeriodic),
                new SequentialCommandGroup(
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new ShooterVelocityByDistanceCommand(robot.shooterSubsystem,robot.limeLightSubsystem),
                        new IndexCommand(robot.indexerSubsystem, 1),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new IndexCommand(robot.indexerSubsystem, 0),
                        new ParallelCommandGroup(
                                new IntakeCommand(robot.intakeSubsystem, 1),
                                new FollowPathCommand(robot.getFollower(), path.next())
                        )






//                           new FollowPathCommand(robot.getFollower()path.next())
                )
        );
    }
}
