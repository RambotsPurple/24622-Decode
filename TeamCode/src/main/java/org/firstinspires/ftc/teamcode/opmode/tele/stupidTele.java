package org.firstinspires.ftc.teamcode.opmode.tele;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
@TeleOp(name = "stupid")
public class stupidTele extends OpMode {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, Alliance.BLUE, gamepad1, telemetry);
        robot.tele();
    }

    @Override
    public void start(){
        robot.tStart();
    }

    @Override
    public void loop() {
        robot.tPeriodic();
    }
}
