package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase{

    private DcMotorEx intake;

    public IntakeSubsystem(HardwareMap hw){
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