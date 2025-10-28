package org.firstinspires.ftc.teamcode.config.Subsytem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class Intake extends SubsystemBase{

    // TODO check motorex vs motor
    private DcMotorEx intake;

    public Intake (HardwareMap hw){
        intake = hw.get(DcMotorEx.class, "Intake");


        // Orientation for intake
        intake.setDirection(DcMotor.Direction.REVERSE);

    } // init


    public void run(double power){
        intake.setPower(power);
    }

    public void stop(){
        intake .setPower(0);
    }

} // Intake