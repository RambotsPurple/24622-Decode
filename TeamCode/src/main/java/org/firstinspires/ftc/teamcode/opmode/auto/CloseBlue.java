package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.FireCommandGroup;
import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterVelocityByDistanceCommand;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoClose;

@Autonomous(name = "CloseBlue")
public class CloseBlue extends OpModeCommand {
    Robot robot;
    AutoClose path;

    @Override
    public void initialize() {
        // Initialize robot and path
        robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
        path = new AutoClose(robot.getFollower(), robot.alliance);
        robot.aStart(path.start);

        // Schedule main loop with throttling
        schedule(
                new RunCommand(() -> {
                     //Run aPeriodic at ~50Hz to prevent hub overload
                    if (System.currentTimeMillis() % 20 < 5) {
                        robot.aPeriodic();
                    }
                }),

                new SequentialCommandGroup(
                        //move from start to shoot1
                    new FollowPathCommand(robot.getFollower(),path.next()),
                    new FireCommandGroup(robot.shooterSubsystem,robot.indexerSubsystem,robot.intakeSubsystem),
                    //shoot1 to intake1
                        new IntakeCommand(robot.intakeSubsystem,1),
                    new FollowPathCommand(robot.getFollower(),path.next()),
                    new IntakeCommand(robot.intakeSubsystem,0),
                    //dirve back shoot postion
                    new FollowPathCommand(robot.getFollower(),path.next()),
                    new FireCommandGroup(robot.shooterSubsystem,robot.indexerSubsystem,robot.intakeSubsystem),

                        //lineup2
                    new FollowPathCommand(robot.getFollower(),path.next()),

                    //intake2
                    new IntakeCommand(robot.intakeSubsystem,1),
                    new FollowPathCommand(robot.getFollower(),path.next()),
                    new IntakeCommand(robot.intakeSubsystem,0),

                        //drive back to shooting postion

                    new FollowPathCommand(robot.getFollower(),path.next()),
                    new FireCommandGroup(robot.shooterSubsystem,robot.indexerSubsystem,robot.intakeSubsystem),

                        //move to exit
                    new FollowPathCommand(robot.getFollower(),path.next())



                )
        );
    }
}
