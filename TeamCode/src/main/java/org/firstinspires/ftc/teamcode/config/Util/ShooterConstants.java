package org.firstinspires.ftc.teamcode.config.Util;

import com.arcrobotics.ftclib.util.InterpLUT;

public class ShooterConstants {

    public static final InterpLUT shooterDistanceVelocityLut = shooterDistanceVelocityLut();
    public static InterpLUT shooterDistanceVelocityLut() {
        InterpLUT lut = new InterpLUT();
        // Key = Distance from limeLight, Value = Shooter Velocity

        // TODO placeholder values, tune
        // lut.add(0, 1000);
        lut.createLUT();
        return lut;
    }
}
