package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.Shooter;

public class CatapultShooterCommand extends CommandBase {
    private final Shooter shooter;
    private double pow;
    public CatapultShooterCommand(Shooter s,double power){
        shooter = s;
        pow = power;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.resetMotor();
        shooter.setMotorVel(1);
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return (shooter.getCurrentPosition() >= shooter.getTargetPosition() + 10);
    }

}
