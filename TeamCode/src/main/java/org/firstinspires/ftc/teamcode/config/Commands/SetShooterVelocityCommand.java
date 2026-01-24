package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;
import com.arcrobotics.ftclib.command.InstantCommand;

public class SetShooterVelocityCommand extends InstantCommand  {
    private final ShooterSubsystem shooter;
    private double rpm;

    public SetShooterVelocityCommand(ShooterSubsystem s, double rpm) {
        shooter = s;
        this.rpm = rpm;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setPower(rpm);
    }


}