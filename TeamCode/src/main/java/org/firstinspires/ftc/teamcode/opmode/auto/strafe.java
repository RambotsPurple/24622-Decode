package org.firstinspires.ftc.teamcode.opmode.auto;


import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.FireCommandGroup;
import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.ThroughPutCommandGroup;
import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.ZeroOutCommandGroup;
import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.SetShooterVelocityCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterVelocityByDistanceCommand;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoClose;
import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoFarPath;

@Autonomous(name = "test")
public class strafe extends OpModeCommand {
    Robot robot;
    AutoFarPath path;
    @Override
    public void initialize() {
        // literally just flip alliance enum for other opMode
        robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
        path = new AutoFarPath(robot.getFollower(), robot.alliance);
        robot.aStart(path.startPose());
        schedule(
                new RunCommand(robot::aPeriodic),
                new SequentialCommandGroup(
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next()),
                        new FollowPathCommand(robot.getFollower(), path.next())



                )
        );
    }
}
