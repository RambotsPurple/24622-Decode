package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class ShooterVelocityCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double pow;
    public ShooterVelocityCommand(ShooterSubsystem s, double power){
        shooterSubsystem = s;
        pow = power;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.setPower(pow);
    }

    @Override
    public void execute() {
        
    }
}
