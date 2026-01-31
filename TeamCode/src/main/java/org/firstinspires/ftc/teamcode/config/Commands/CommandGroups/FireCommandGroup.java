package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.SetShooterVelocityCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class FireCommandGroup extends SequentialCommandGroup {
    public FireCommandGroup(ShooterSubsystem shoot, IndexerSubsystem index, IntakeSubsystem intake){
        addCommands(
                //reverse the index and intake so we don't prematurly shoot bolls
                new ReverseThroughPutCommand(index,intake),
                new WaitCommand(300),
                //turn off all motors and start the flywheel
                new ParallelCommandGroup(
                        new ZeroThroughPutCommandGroup(intake,index),
                        new SetShooterVelocityCommand(shoot,5600)

                ),
                //wait while we rev
                new WaitCommand(2300),
                //push all the balls
                new ThroughPutCommandGroup(intake,index),
                new WaitCommand(1500),
                //power off everything to save power
                new ZeroOutCommandGroup(index,shoot,intake)

        );
    }
}
