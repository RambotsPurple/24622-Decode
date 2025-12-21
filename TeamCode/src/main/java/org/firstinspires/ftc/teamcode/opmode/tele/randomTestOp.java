
package org.firstinspires.ftc.teamcode.opmode.tele;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Subsystem.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.config.Subsystem.ShooterSubsystem;

@TeleOp(name = "gurt yo 123")
public class randomTestOp extends OpMode {
    IntakeSubsystem intakeSubsystem;
    ShooterSubsystem shooterSubsystem;

    @Override
    public void init() {
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        shooterSubsystem = new ShooterSubsystem(hardwareMap,telemetry);

    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().run();
        if (gamepad1.a) {
            CommandScheduler.getInstance().schedule(new CatapultShooterCommand(shooterSubsystem, 1));
        }
        if (gamepad1.b) {
            CommandScheduler.getInstance().schedule(new IntakeCommand(intakeSubsystem, 1));
        }
        if (gamepad1.x) {
            CommandScheduler.getInstance().schedule(new IntakeCommand(intakeSubsystem, 0));
        }

        telemetry.addData("shooter current pos: ", shooterSubsystem.getCurrentPosition());
        telemetry.addData("shooter target pos: ", shooterSubsystem.getTargetPosition());
        telemetry.update();

    }

} // linearOpMod
