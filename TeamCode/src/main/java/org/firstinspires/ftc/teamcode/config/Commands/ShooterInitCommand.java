package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.Shooter;

public class ShooterInitCommand extends CommandBase {
    private final Shooter shooter;
    private double pow;
    public ShooterInitCommand(Shooter s,double power){
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
    public void execute() {
        int d = shooter.getTargetPosition() - shooter.getCurrentPosition();
        if (d < 200) {
            double pow = Math.min((double) d / shooter.getTargetPosition(), 1);
        }
    }

    @Override
    public boolean isFinished() {
        if (shooter.getCurrentPosition() >= shooter.getTargetPosition()) {
            shooter.setMotorVel(0);
            return true;
        }
        return false;
    }

}
