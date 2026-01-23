package org.firstinspires.ftc.teamcode.config.Util;

import com.arcrobotics.ftclib.util.InterpLUT;

public class ShooterConstants {

    public static final InterpLUT shooterDistanceVelocityLut = shooterDistanceVelocityLut();
    public static InterpLUT shooterDistanceVelocityLut() {
        InterpLUT lut = new InterpLUT();
        // Key = Distance from limeLight, Value = Shooter Velocity

        // TODO placeholder values, tune
        // lut.add(0, 1000);
//        lut.add(1.1, 0.2);
//        lut.add(2.7, 0.5);
//        lut.add(3.6, 0.75);
//        lut.add(4.1, 0.9);
//        lut.add(5.0, 1.0);
        lut.createLUT();
        return lut;
    }
}
