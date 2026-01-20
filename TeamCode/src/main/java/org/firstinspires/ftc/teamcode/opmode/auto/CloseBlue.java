 package org.firstinspires.ftc.teamcode.opmode.auto;


 import com.arcrobotics.ftclib.command.RunCommand;
 import com.arcrobotics.ftclib.command.SequentialCommandGroup;
 import com.arcrobotics.ftclib.command.ParallelCommandGroup;
 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

 import org.firstinspires.ftc.teamcode.config.Commands.FollowPathCommand;
 import org.firstinspires.ftc.teamcode.config.Robot;
 import org.firstinspires.ftc.teamcode.config.Util.Alliance;
 import org.firstinspires.ftc.teamcode.config.Util.OpModeCommand;
 import org.firstinspires.ftc.teamcode.config.Util.Paths.AutoClose;

 @Autonomous(name = "CloseBlue")
 public class CloseBlue extends OpModeCommand {
     Robot robot;
     AutoClose path;
     @Override
     public void initialize() {
         // literally just flip alliance enum for other opMode
         robot = new Robot(hardwareMap, Alliance.BLUE, telemetry);
         path = new AutoClose(robot.getFollower(), robot.alliance);
         schedule(
                     new RunCommand(robot::aPeriodic),
                     new SequentialCommandGroup(
                             new ParallelCommandGroup(
                                     new FollowPathCommand(robot.getFollower(), path.next())

                             )



//                           new FollowPathCommand(robot.getFollower()path.next())
                     )
         );
     }
}
