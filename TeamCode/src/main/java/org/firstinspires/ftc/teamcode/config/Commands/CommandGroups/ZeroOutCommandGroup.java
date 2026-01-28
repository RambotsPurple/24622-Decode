package org.firstinspires.ftc.teamcode.config.Commands.CommandGroups;

import com.arcrobotics.ftclib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.SetShooterVelocityCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class ZeroOutCommandGroup extends ParallelCommandGroup {
    public ZeroOutCommandGroup(IndexerSubsystem index, ShooterSubsystem shooter, IntakeSubsystem intake){
        addCommands(
                new IntakeCommand(intake,0),
                new SetShooterVelocityCommand(shooter,0),
                new IndexCommand(index,0)
        );
        addRequirements(index,intake,shooter);
    }
}
