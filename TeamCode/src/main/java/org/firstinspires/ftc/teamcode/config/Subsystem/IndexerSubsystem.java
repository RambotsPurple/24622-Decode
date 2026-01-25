package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerSubsystem extends SubsystemBase {

    public CRServo index;

    public IndexerSubsystem(HardwareMap hw, Telemetry t) {
        index = hw.get(CRServo.class, "index");
        index.setDirection(DcMotorSimple.Direction.FORWARD);

    } // init

    public void setPower(double p) {
        index.setPower(p);
    } // setPositon


}//index SubSystem