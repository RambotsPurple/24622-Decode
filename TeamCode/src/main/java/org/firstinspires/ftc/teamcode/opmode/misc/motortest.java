package org.firstinspires.ftc.teamcode.opmode.misc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "ShooterFullPowerTest")
public class motortest extends OpMode {

    private DcMotorEx shooter1, shooter2;
    private boolean motorsOn = false;

    @Override
    public void init() {
        shooter1 = hardwareMap.get(DcMotorEx.class, "s1");
        // shooter2 = hardwareMap.get(DcMotorEx.class, "s2");

        // Make sure motors spin the same way
        shooter1.setDirection(DcMotor.Direction.REVERSE);
        // shooter2.setDirection(DcMotor.Direction.REVERSE);

        // Use RUN_WITHOUT_ENCODER for full power
        shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        // Toggle motors with B button
        if (gamepad1.b) {
            motorsOn = !motorsOn;

            if (motorsOn) {
                shooter1.setPower(1.0);
                // shooter2.setPower(1.0);
            } else {
                shooter1.setPower(0.0);
                // shooter2.setPower(0.0);
            }

            // Debounce so button press only toggles once
        }

        // Telemetry: show shooter RPM
        double rpm1 = shooter1.getVelocity(AngleUnit.DEGREES) / 6.0; // DEGREES/sec → RPM
        // double rpm2 = shooter2.getVelocity(AngleUnit.DEGREES) / 6.0;

        telemetry.addData("Motors On?", motorsOn);
        telemetry.addData("Shooter1 RPM", "%.2f", shooter1.getVelocity());
        // telemetry.addData("Shooter2 RPM", "%.2f", rpm2);
        telemetry.update();
    }


}
