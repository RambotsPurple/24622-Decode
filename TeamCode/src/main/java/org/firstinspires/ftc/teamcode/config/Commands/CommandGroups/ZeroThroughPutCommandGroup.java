package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;

public class ZeroThroughPutCommandGroup extends ParallelCommandGroup {
    ZeroThroughPutCommandGroup(IntakeSubsystem intake, IndexerSubsystem index){
        addCommands(
               new  IndexCommand(index,0),
                new IntakeCommand(intake,0)
        );
        addRequirements(index,intake);
    }
}
