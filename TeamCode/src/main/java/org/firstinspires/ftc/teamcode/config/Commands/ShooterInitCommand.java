package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class ShooterInitCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double pow;
    public ShooterInitCommand(ShooterSubsystem s, double power){
        shooterSubsystem = s;
        pow = power;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.resetMotor();
        shooterSubsystem.setMotorVel(1);
    }

    @Override
    public void execute() {
        int d = shooterSubsystem.getTargetPosition() - shooterSubsystem.getCurrentPosition();
        if (d < 200) {
            double pow = Math.min((double) d / shooterSubsystem.getTargetPosition(), 1);
        }
    }

    @Override
    public boolean isFinished() {
        if (shooterSubsystem.getCurrentPosition() >= shooterSubsystem.getTargetPosition()) {
            shooterSubsystem.setMotorVel(0);
            return true;
        }
        return false;
    }

}
