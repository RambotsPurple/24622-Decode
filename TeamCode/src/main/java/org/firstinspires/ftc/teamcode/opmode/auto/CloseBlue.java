package org.firstinspires.ftc.teamcode.opmode.auto;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;

@Autonomous(name = "CloseBlue")
public class CloseBlue extends OpModeCommand {
    Robot robot;

    @Override
    public void init() {
        robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
        if(gamepad1.xWasPressed()){
            robot.alliance = Alliance.BLUE;

        }else if(gamepad1.bWasPressed()){
            robot.alliance = Alliance.RED;
        }//end of else if

        telemetry.addLine("press X to change to BLUE Alliance");
        telemetry.addLine("press B to change to RED Alliance");
        telemetry.addData("current alliance selected:", robot.alliance);
        telemetry.update();
        robot.tele();
    }

    @Override
    public void start(){
        robot.aStart();
    }

    @Override
    public void loop() {
        robot.aPeriodic();
    }

    @Override
    public void initialize() {

    }
}
