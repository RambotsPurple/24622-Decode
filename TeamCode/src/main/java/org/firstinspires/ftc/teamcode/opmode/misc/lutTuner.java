package org.firstinspires.ftc.teamcode.opmode.misc;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Subsystem.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
@TeleOp(name = "lutTuner")
public class lutTuner extends OpMode {
    public ShooterSubsystem sb;
    public LimeLightSubsystem ll;
    @Override
    public void init() {
        sb = new ShooterSubsystem(hardwareMap, telemetry);
        ll = new LimeLightSubsystem(hardwareMap,Alliance.RED);



    } // init
    @Override
    public void start(){
        ll.lStart();
    }

    @Override
    public void loop() {
        if (gamepad1.dpadUpWasPressed()){
            sb.setRPM(+200);
        }
        if (gamepad1.dpadUpWasPressed()){
            sb.setRPM(-200);
        }

        if (gamepad1.dpadLeftWasPressed()){
            sb.setRPM(0);
        }

        if(gamepad1.dpadRightWasPressed()){
            sb.setRPM(6000);
        }

        telemetry.addData("rpm:", sb.getRPM());
        telemetry.addData("distance from tag:", ll.getDist());
        telemetry.addData("offset:",ll.getHorizontalError()) ;
        telemetry.update();
    }
}
