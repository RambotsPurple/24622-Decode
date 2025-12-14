package org.firstinspires.ftc.teamcode.config.Commands;
import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

public class CatapultShooterCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double pow;
    public CatapultShooterCommand(ShooterSubsystem s, double power){
        shooterSubsystem = s;
        pow = power;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {
        shooterSubsystem.resetMotor();
        shooterSubsystem.setTargetPosition(shooterSubsystem.getDownTick());
        shooterSubsystem.setMotorVel(1);
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        if (shooterSubsystem.getCurrentPosition() >= shooterSubsystem.getTargetPosition() + 10) {
            shooterSubsystem.setMotorVel(0);
            return true;
        }
        return false;
    }

}
