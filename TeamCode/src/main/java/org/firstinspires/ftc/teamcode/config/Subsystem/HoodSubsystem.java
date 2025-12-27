package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HoodSubsystem extends SubsystemBase {

    public Servo lHood, rHood;
    public  InterpLUT lutHood;
    public HoodSubsystem(HardwareMap hw, Telemetry t) {
        //need to go on the field a tune key points top of the key inside paint and from half
        lutHood = new InterpLUT();
        // lutHood.add(1.1, 0.2);
        // lutHood.add(2.7, .5);
        // lutHood.add(3.6, 0.75);
        // lutHood.add(4.1, 0.9);
        // lutHood.add(5, 1);
        lutHood.createLUT(); //calc the cubuic
        lHood = hw.get(Servo.class, "lHood");
        rHood = hw.get(Servo.class, "rHood");
    } // init

    public void setPositon(double p) {
        lHood.setPosition(p);
        rHood.setPosition(p);
    } // setPositon



}// end of HoodSubsystem