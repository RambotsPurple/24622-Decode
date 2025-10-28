package org.firstinspires.ftc.teamcode.config.Subsytem;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer extends SubsystemBase {
    private DcMotorEx transfer;
    public Transfer(HardwareMap hw) {
        transfer = hw.get(DcMotorEx.class, "transfer");


        // Orientation for shooter
        transfer.setDirection(DcMotor.Direction.FORWARD);
    } // init

    public void run(double power){
        transfer.setPower(power);
    }

    public void stop(){
        transfer.setPower(0);
    }
}