package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.config.Subsystem.DriveSubsystem;

public class ImuResetCommand extends InstantCommand {
    DriveSubsystem d;
    public ImuResetCommand(DriveSubsystem drive){
        d = drive;
        addRequirements(d);

    }
    @Override
    public void initialize() {
        d.resetAngle();
    }
}
