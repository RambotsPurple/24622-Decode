// package org.firstinspires.ftc.teamcode.opmode.auto;
//
//
// import com.arcrobotics.ftclib.command.CommandOpMode;
// import com.arcrobotics.ftclib.command.RunCommand;
// import com.arcrobotics.ftclib.command.SequentialCommandGroup;
// import com.arcrobotics.ftclib.command.WaitCommand;
// import com.pedropathing.geometry.Pose;
// import com.qualcomm.robotcore.eventloop.opmode.OpMode;
// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
// import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
// import org.firstinspires.ftc.teamcode.config.Robot;
// import org.firstinspires.ftc.teamcode.config.Util.Alliance;
// import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
// import org.firstinspires.ftc.teamcode.config.Util.Paths.autoCloseBLue;
//
// import java.nio.file.Path;
//
// @Autonomous(name = "CloseBlue")
// public class CloseBlue extends OpModeCommand {
//     Robot robot;
//     Path path;
//     @Override
//     public void initialize() {
//         robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
//
//         schedule(
//                     new RunCommand(robot::aPeriodic),
//                     new SequentialCommandGroup(
//                             new FollowPathCommand(robot.getFollower())
//                     )
//         );
//     }
//
//
// // }
