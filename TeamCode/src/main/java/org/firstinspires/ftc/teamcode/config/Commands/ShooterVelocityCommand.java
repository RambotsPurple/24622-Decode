package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.config.Subsystem.Shooter;

public class ShooterVelocityCommand extends CommandBase {
    private final Shooter shooter;
    private double pow;
    public ShooterVelocityCommand(Shooter s,double power){
        shooter = s;
        pow = power;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setPower(pow);
    }

    @Override
    public void execute() {
        
    }
}
