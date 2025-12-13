package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.config.Subsystem.Shooter;

public class ShooterSetPowCommand extends InstantCommand {
    private final Shooter shooter;
    private double pow;
    public ShooterSetPowCommand(Shooter s,double power){
        shooter = s;
        pow = power;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setPower(pow);
    }


}
