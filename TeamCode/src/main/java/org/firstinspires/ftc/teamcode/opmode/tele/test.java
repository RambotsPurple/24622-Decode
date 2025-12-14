
package org.firstinspires.ftc.teamcode.opmode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;

@TeleOp(name = "real teleop not fake :D - RUN THIS ONE")
public class test extends OpMode {
    Robot robot;

//    GamepadEx driver = new GamepadEx(gamepad1);
//    GamepadEx operator = new GamepadEx(gamepad2);

    @Override
    public void init() {
        robot = new Robot(hardwareMap, Alliance.BLUE, gamepad1, gamepad2, telemetry);
    }

    @Override
    public void start() {
        robot.tStart();
    }

    @Override
    public void loop() {
        robot.periodic();
        robot.tele();
    }

} // linearOpMod
