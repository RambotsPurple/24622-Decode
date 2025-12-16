package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.config.Subsystem.DriveSubsystem;

@TeleOp(name = "stupid ahh op")
public class stupidAhhOp extends LinearOpMode {
    DcMotorEx shooter1;
    DcMotorEx shooter2;
    DriveSubsystem d;

    DcMotorEx Intake;


    @Override
    public void runOpMode() throws InterruptedException {
        d = new DriveSubsystem(hardwareMap);
        shooter1 = hardwareMap.get(DcMotorEx.class, "lShoot");
        shooter2 = hardwareMap.get(DcMotorEx.class, "rShoot");
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");

        shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while(opModeIsActive()) {
            d.drive(-gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

            if(gamepad1.a){
                d.resetAngle();
            }
            shooter1.setPower(gamepad2.left_stick_y);
            shooter2.setPower(gamepad2.left_stick_y);
            Intake.setPower(gamepad1.right_trigger);

            telemetry.addData("angle", d.getAngle());
            telemetry.update();

        }


    }

}
