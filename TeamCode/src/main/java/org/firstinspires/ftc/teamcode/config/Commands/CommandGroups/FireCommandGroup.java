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
                new ReverseThroughPutCommand(index,intake),
                new WaitCommand(1000),
                new SetShooterVelocityCommand(shoot,5600),
                new WaitCommand(2000),
                new ReverseThroughPutCommand(index,intake),
                new WaitCommand(2000),
                new ZeroOutCommandGroup(index,shoot,intake)

        );
    }
}
