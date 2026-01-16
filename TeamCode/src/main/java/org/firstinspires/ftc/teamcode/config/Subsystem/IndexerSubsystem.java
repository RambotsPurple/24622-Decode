package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerSubsystem extends SubsystemBase {

    public Servo hood;
    public IndexerSubsystem(HardwareMap hw, Telemetry t) {
        hood = hw.get(Servo.class, "flip");
    } // init

    public void setPositon(double p) {
        hood.setPosition(p);
    } // setPositon



}// end of HoodSubsystem