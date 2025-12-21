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
    public void initialize() {
        robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
            schedule(


        );
    }


}
