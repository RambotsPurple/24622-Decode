package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.config.Subsystem.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;

public class IndexCommand extends InstantCommand {
    private final IndexerSubsystem IndexerSubsystem;
    private double pow;
    public IndexCommand(IndexerSubsystem sub, double power){
        IndexerSubsystem = sub;
        pow = power;
        addRequirements(IndexerSubsystem);
    }

    @Override
    public void initialize() {
        IndexerSubsystem.setPower(pow);
    }


}
