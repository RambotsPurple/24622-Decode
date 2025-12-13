package org.firstinspires.ftc.teamcode.config.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.pedropathing.ftc.localization.RevHubIMU;

import org.firstinspires.ftc.teamcode.config.Subsystem.Drive;

public class ImuReset extends CommandBase {
     Drive d;
    public ImuReset(Drive drive){
        d = drive;
        addRequirements(d);

    }
    @Override
    public void initialize() {
        d.resetAngle();
    }
}
