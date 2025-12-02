package org.firstinspires.ftc.teamcode.opmode;
import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.AutoCommandGroup;
import org.firstinspires.ftc.teamcode.config.Paths.Paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "pedro test", group = "Examples")
public class CommandBasedAutoOp extends CommandOpMode {

    /** This method is called once at the init of the OpMode. **/
    @Override
    public void initialize() {
        Paths p = new Paths(follower);
        // hardware & subsystems
        // schedule your autonomous sequence
        schedule(
                new AutoCommandGroup()
        );
    }

    // Optional
    // TODO lowk delet this
    @Override
    public void run() {
        // Only if you want custom code running every loop
    }

}

