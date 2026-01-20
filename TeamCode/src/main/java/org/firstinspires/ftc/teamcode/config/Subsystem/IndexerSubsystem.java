package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerSubsystem extends SubsystemBase {

    public DcMotorEx index;
    public IndexerSubsystem(HardwareMap hw, Telemetry t) {
        index = hw.get(DcMotorEx.class, "index");
    } // init

    public void setPower(double p) {
        index.setPower(p);
    } // setPositon



}// end of HoodSubsystem