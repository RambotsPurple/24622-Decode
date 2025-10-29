package org.firstinspires.ftc.teamcode.config;
/*
/*
Class: Robot
------------------------------
Purpose:
The point of this class is to contain all the objects like commands and subsystems
to be used later in the OpModes. Think of one class that will instantiate what we
need for each OpMode, no matter the case.

We create two or more different constructors for TeleOp and Auto, and create the
corresponding methods to call the CommandScheduler to run our commands.

We use the Opmode enum to determine which constructor the OpMode will use
(Util/Opmode.java) and use the Alliance enum to set the Alliance.

------------------------------
Q&A:
Q: Why do we need the Alliance enum?
A: The Alliance enum allows us to set the correct AprilTag ID for target acquisition
   or establish the right starting pose for Auto.
   - BLUE goals AprilTag: ID 20
   - RED goals AprilTag: ID 24

------------------------------
Finite State Machine (FSM):
The robot also uses a finite state machine for greater automation.
You can see the states in Util/state.java.

------------------------------
Manual Bulk Caching:
We’re using manual bulk caching to increase loop speed so we can get faster reads
on our sensors and better response times.

Q: What is caching?
A: Caching is simply getting all the sensor values on the Control/Expansion Hub
   at once and returning them in a single instance.

Q: What does this even mean?
A: By default, when running an OpMode or LinearOpMode, if you want to get a sensor
   value, you’ll only get that single value per read call. Getting multiple values
   adds a queue where it reads sensors one by one, which is slow.

Q: The difference?
A: Reading sensor values happens on every get call like:
      motor.getCurrentPosition();
      motor.getVelocity();
   The SDK processes each method individually, while bulk caching retrieves all
   the values instantly because they’re already stored locally.

Q: Wouldn’t the values be inaccurate?
A: Not really—since we’re manually caching every loop, we can run up to 70–100 Hz
   per loop iteration versus 25–35 Hz without caching.

Q: Why even do it?
A: Critical subsystems like vision (for AprilTags) and telemetry sensors need that
   fast iteration time to get the correct values and maintain the highest accuracy.
*/


import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Util.*;
import java.util.List;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.config.Subsytem.*;

public class Robot {
    public final Alliance alliance;
    public CommandScheduler cs = CommandScheduler.getInstance();


    protected GamepadEx driver, operator;
//    private final List<LynxModule> hubs;
    private final Timer loop = new Timer();
    private int loops = 0;
    private double loopTime = 0;

    public Shooter shooter;
    public Intake intake;
    public Transfer transfer;
    public Drive drive;



    public static Pose endPose = new Pose();

    public Robot(HardwareMap h, Alliance alliance, Gamepad driver, Gamepad  operator) {
         shooter = new Shooter(h);
         intake = new Intake(h);
         transfer = new Transfer(h);
         drive  = new Drive(h);
//        follower = Constants.createFollower(h);
//        follower.setStartingPose(new Pose(0,0,0));
        this.alliance = alliance;
        this.driver = new GamepadEx(driver);
        this.operator = new GamepadEx(operator);
        //bulk caching to increase loop speeds
//        hubs = h.getAll(LynxModule.class);
//        for (LynxModule module : hubs) {
//            module.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
//        }
//
//        loop.resetTimer();
        cs.registerSubsystem(
                shooter, transfer,intake,drive
        );

    }//end of teleop constructor

    public void periodic() {
        drive.drive(-driver.getLeftX(),-driver.getLeftY(),driver.getRightX());
//        follower.update();
//        follower.setTeleOpDrive(
//                -driver.getLeftX() ,
//                -driver.getLeftY() ,
//                -driver.getRightX(),
//                false
//        );
        //bulk cache
//        loops++;
//
//        if (loop.getElapsedTime() % 5 == 0) {
//            for (LynxModule hub : hubs) {
//                hub.clearBulkCache();
//            }
//
//            loopTime = (double) loop.getElapsedTime() / loops;
//        }
//        //bulk cache
        cs.run();
    }//end of periodic
    public void tStart(){
//        follower.update();
//        follower.startTeleopDrive(true);
    }
    public void tele(){
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0)
                .whenActive(new IntakeCommand(intake, 1))
                .whenInactive(new IntakeCommand(intake, 0));

    }
    public void stop() {
    }//end of stop

    public double getLoopTime() {
        return loopTime;
    }//end og getLoopTime

}//end of Robot