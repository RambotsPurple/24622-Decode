package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.config.Subsytem.Intake;

public class IntakeCommand extends InstantCommand {
    private final Intake intake;
    private double pow;
    public IntakeCommand(Intake sub,double power){
        intake = sub;
        pow = power;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        intake.run(pow);
    }


}
