package org.firstinspires.ftc.teamcode.opmode.auto;

import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterVelocityByDistanceCommand;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoClose;

@Autonomous(name = "CloseBlueTest")
public class CloseBlueTest extends OpModeCommand {
    Robot robot;
    AutoClose path;

    @Override
    public void initialize() {
        reset();
        // Initialize robot and path
        robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
        path = new AutoClose(robot.getFollower(), robot.alliance);
        robot.aStart(path.start);

        // Schedule main loop with throttling
        schedule(

                //new RunCommand(robot :: aPeriodic),

                new SequentialCommandGroup(
                        // First path + shooter setup
                        new FollowPathCommand(robot.getFollower(), path.next())
                        //new ShooterVelocityByDistanceCommand(robot.shooterSubsystem, robot.limeLightSubsystem),
                        //new IndexCommand(robot.indexerSubsystem, 1)
                        //new WaitCommand(1000),

                        // Move to next point and index
                        //new FollowPathCommand(robot.getFollower(), path.next()),
                        //new IndexCommand(robot.indexerSubsystem, 0),
                        //new WaitCommand(500),

                        // Intake + path split into sequential to reduce current spikes
                        //new FollowPathCommand(robot.getFollower(), path.next()),
                        //new WaitCommand(100), // small delay before intake
                        //new IntakeCommand(robot.intakeSubsystem, 0.7), // reduced power to prevent overload
                        //new WaitCommand(500) // give some time to intake
                )
        );
    }
}
