
package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;

@TeleOp(name = "tel")
public class test extends OpMode {
    Robot robot;
    GamepadEx driver = new GamepadEx(gamepad1);
    GamepadEx operator = new GamepadEx(gamepad2);

    @Override
    public void init() {
        robot = new Robot(hardwareMap, Alliance.BLUE, driver, operator);
    }

    @Override
    public void start() {
        robot.tStart();
    }

    @Override
    public void loop() {
        robot.periodic();
    }

} // linearOpMod
