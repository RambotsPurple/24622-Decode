package org.firstinspires.ftc.teamcode.config.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShooterSubsystem extends SubsystemBase {

    public DcMotorEx shooter1, shooter2;

    public ShooterSubsystem(HardwareMap hw, Telemetry t) {
        shooter1 = hw.get(DcMotorEx.class, "lShoot");
        shooter2 = hw.get(DcMotorEx.class, "rShoot");

        shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooter1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //issue with shooter2 when encoder cable not connected
        shooter2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Orientation for shooter
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        shooter2.setDirection(DcMotor.Direction.FORWARD);

        // zero pow behaviour brake
        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    } // init

    public void setPower(double p) {
        shooter1.setPower(p);
        shooter2.setPower(p);
    } // shoot



    // set RPM of motorv
    public void setRPM(int RPM) {
        shooter1.setVelocity(RPM * 6, AngleUnit.DEGREES);
    }

    public double getRPM() {
        return shooter1.getVelocity(AngleUnit.DEGREES) / 6;
    }

    public double getTPS() {
        return shooter1.getVelocity();
    }


    public void setMotorVel(double p) {
        shooter1.setPower(p);
        shooter2.setPower(p);
    }



}// end of shooter class