
package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Commands.CatapultShooterCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.config.Subsystem.Shooter;
import org.firstinspires.ftc.teamcode.config.Subsystem.Webcam;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "gurt yo 123")
public class randomTestOp extends OpMode {
    Intake intake;
    Shooter shooter;

    @Override
    public void init() {
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);

    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
        if (gamepad1.a) {
            CommandScheduler.getInstance().schedule(new CatapultShooterCommand(shooter, 1));
        }
        if (gamepad1.b) {
            CommandScheduler.getInstance().schedule(new IntakeCommand(intake, 1));
        }
        if (gamepad1.x) {
            CommandScheduler.getInstance().schedule(new IntakeCommand(intake, 0));
        }

        telemetry.addData("shooter current pos: ", shooter.getCurrentPosition());
        telemetry.addData("shooter target pos: ", shooter.getTargetPosition());
        telemetry.update();

    }

} // linearOpMod
