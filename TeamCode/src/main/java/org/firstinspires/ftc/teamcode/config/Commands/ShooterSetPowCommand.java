package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class ShooterSetPowCommand extends InstantCommand {
    private final ShooterSubsystem shooterSubsystem;
    private double pow;
    public ShooterSetPowCommand(ShooterSubsystem s, double power){
        shooterSubsystem = s;
        pow = power;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setPower(pow);
    }


}
