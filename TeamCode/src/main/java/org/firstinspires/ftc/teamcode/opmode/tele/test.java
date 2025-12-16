
package org.firstinspires.ftc.teamcode.opmode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;

@TeleOp(name
        = "real teleop not fake :D - RUN THIS ONE")
public class test extends OpMode {
    Robot robot;
    DcMotorEx shooter1;
    DcMotorEx shooter2;

//    GamepadEx driver = new GamepadEx(gamepad1);
//    GamepadEx operator = new GamepadEx(gamepad2);

    @Override
    public void init() {

        shooter1 = hardwareMap.get(DcMotorEx .class, "lShoot");
        shooter2 = hardwareMap.get(DcMotorEx.class, "rShoot");

        robot = new Robot(hardwareMap, Alliance.BLUE, gamepad1, gamepad2, telemetry);
        if(gamepad1.a){
            robot.alliance = Alliance.RED;
        }else{
            robot.alliance = Alliance.BLUE;
        }
        robot.tele();
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
