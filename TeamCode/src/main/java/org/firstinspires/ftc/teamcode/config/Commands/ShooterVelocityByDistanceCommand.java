package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.config.Subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.config.Util.ShooterConstants;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.util.InterpLUT;

public class ShooterVelocityByDistanceCommand extends InstantCommand {
    private final ShooterSubsystem shooter;
    private final LimeLightSubsystem limelight;
    private final InterpLUT shooterDistanceVelocityLut;
    private double currentDistance;


    public ShooterVelocityByDistanceCommand(ShooterSubsystem s, LimeLightSubsystem l) {
        shooter = s;
        limelight = l;
        addRequirements(shooter, limelight);
        currentDistance = limelight.getDist();
        shooterDistanceVelocityLut = ShooterConstants.shooterDistanceVelocityLut;
    }

    @Override
    public void initialize() {
        shooter.setPower(shooterDistanceVelocityLut.get(currentDistance));
    }
}