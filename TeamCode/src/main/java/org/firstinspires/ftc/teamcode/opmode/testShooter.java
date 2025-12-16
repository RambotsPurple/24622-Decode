package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp(name = "Shooter Test")
public class testShooter extends LinearOpMode {
    DcMotorEx shooter1;
    DcMotorEx shooter2;

    @Override
    public void runOpMode() throws InterruptedException {
        shooter1 = hardwareMap.get(DcMotorEx.class, "lShoot");
        shooter2 = hardwareMap.get(DcMotorEx.class, "rShoot");


        waitForStart();
        while(opModeIsActive()) {
            shooter1.setPower(gamepad2.left_stick_y);
            shooter2.setPower(gamepad2.left_stick_y);
        }


    }

}
