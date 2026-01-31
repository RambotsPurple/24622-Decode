package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;

public class ReverseThroughPutCommand extends ParallelCommandGroup {
    private double pow;

    ReverseThroughPutCommand(IndexerSubsystem index, IntakeSubsystem intake){
        addCommands(
            new IndexCommand(index, -.4),
            new IntakeCommand(intake, -.4)
        );
    }
    ReverseThroughPutCommand(IndexerSubsystem index, IntakeSubsystem intake,double power){
        this.pow = power;
        addCommands(
                new IndexCommand(index, pow),
                new IntakeCommand(intake, pow)
        );
    }
}
