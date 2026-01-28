package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;

public class ReverseThroughPutCommand extends ParallelCommandGroup {
    ReverseThroughPutCommand(IndexerSubsystem index, IntakeSubsystem intake){
        addCommands(
            new IndexCommand(index, -4),
            new IntakeCommand(intake, -4)
        );
    }
}
