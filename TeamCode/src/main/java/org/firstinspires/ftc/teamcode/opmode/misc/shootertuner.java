package org.firstinspires.ftc.teamcode.opmode.misc;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
@TeleOp(name = "shooterTuner")
public class shootertuner extends OpMode {
    public DcMotorEx shooter1, shooter2;
    double highvel = 5000;
    double lowvel = 1300;
    double currvelt = highvel;
    double p = 0;
    double f=0;
    double[] stepSizes = {10,1,.1,.001,.0001};
    int stepindex = 1;
    @Override
    public void init() {
        shooter1 = hardwareMap.get(DcMotorEx.class, "s1");
        shooter2 = hardwareMap.get(DcMotorEx.class, "s2");

        // Orientation for shooter
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        shooter2.setDirection(DcMotor.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p,0,0,f);
        shooter1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooter2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);


    } // init



    @Override
    public void start(){

    }

    @Override
    public void loop() {
        if(gamepad1.yWasPressed()){
            if(currvelt == highvel){
                currvelt = lowvel;
            }else{
                currvelt = highvel;
            }
        }if (gamepad1.bWasPressed()){
            stepindex = (stepindex +1)% stepSizes.length;
        }
        if(gamepad1.dpadUpWasPressed()){
            p += stepSizes[stepindex];
        }
        if(gamepad1.dpadDownWasPressed()){
            p-= stepSizes[stepindex];
        }
        if(gamepad1.dpadLeftWasPressed()){
            f+= stepSizes[stepindex];
        }
        if(gamepad1.dpadRightWasPressed()){
            f-= stepSizes[stepindex];
        }

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p,0,0,f);
        shooter1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooter2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        shooter1.setVelocity(currvelt*6, AngleUnit.DEGREES);
        shooter2.setVelocity(currvelt*6, AngleUnit.DEGREES);

        double currvel =  shooter2.getVelocity();
        double error  = currvel-currvelt;
        telemetry.addData("targ vel:",currvelt);
        telemetry.addData("curr vel","%.2f", currvel);
        telemetry.addData("error", error);
        telemetry.addData("p", p);
        telemetry.addData("f", f);
        telemetry.addData("index", stepindex);
        telemetry.update();

    }
}
