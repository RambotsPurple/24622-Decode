package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;

public class ThroughPutCommandGroup extends ParallelCommandGroup {
    public ThroughPutCommandGroup(IntakeSubsystem intake, IndexerSubsystem index){
        addCommands(
                new IndexCommand(index, 1),
                new IntakeCommand(intake,1)
        );
        addRequirements(index,intake);
    }
}
