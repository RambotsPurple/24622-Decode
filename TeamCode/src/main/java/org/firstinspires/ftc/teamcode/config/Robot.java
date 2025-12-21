package org.firstinspires.ftc.teamcode.config;

import static org.firstinspires.ftc.teamcode.config.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.lynx.LynxModule;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.Commands.ImuResetCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterInitCommand;
import org.firstinspires.ftc.teamcode.config.Commands.ShooterSetPowCommand;
import org.firstinspires.ftc.teamcode.config.Util.*;
import org.firstinspires.ftc.teamcode.config.Subsystem.*;
import org.firstinspires.ftc.teamcode.config.pedroPathing.Constants;

import java.util.List;

public class Robot {

    private  List<LynxModule> allHubs;
    private final Timer loop = new Timer();
    public Alliance alliance;
    public CommandScheduler cs = CommandScheduler.getInstance();


    protected GamepadEx driver, operator;
//    private final List<LynxModule> hubs;




    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;
    public TransferSubsystem transferSubsystem;
    public DriveSubsystem driveSubsystem;
    private Telemetry telemetry;



    public static Pose endPose = new Pose();


    /**
     * This constructor below is for a double player teleop on either side
     * of the field
     *
     * @author Alex
     * @param h
     * @param alliance
     * @param driver
     * @param operator
     * @param telemetry
     * */
    public Robot(HardwareMap h, Alliance alliance, Gamepad driver, Gamepad  operator, Telemetry telemetry) {
         shooterSubsystem = new ShooterSubsystem(h,telemetry);
         intakeSubsystem = new IntakeSubsystem(h);
         transferSubsystem = new TransferSubsystem(h);
        driveSubsystem  = new DriveSubsystem(h);
        follower = Constants.createFollower(h);
        follower.setStartingPose(new Pose(0,0,0));
        this.alliance = alliance;
        this.driver = new GamepadEx(driver);
        this.operator = new GamepadEx(operator);
        this.telemetry = telemetry;

        allHubs = h.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        loop.resetTimer();

        cs.registerSubsystem(
                shooterSubsystem, transferSubsystem, intakeSubsystem,driveSubsystem
        );

    }//end of teleop constructor

    /**
     * The constructor below is for auto on any side of the field
     *
     * @author Alex
     * @param h
     * @param alliance
     * @param telemetry
     */

    public Robot(HardwareMap h, Alliance alliance, Telemetry telemetry, Pose p) {
        shooterSubsystem = new ShooterSubsystem(h,telemetry);
        intakeSubsystem = new IntakeSubsystem(h);
        transferSubsystem = new TransferSubsystem(h);
        driveSubsystem  = new DriveSubsystem(h);
        follower = Constants.createFollower(h);
        follower.setStartingPose(new Pose(0,0,0));
        this.alliance = alliance;
        this.telemetry = telemetry;

        //instaniate the lynx mod
        allHubs = h.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }//end of for


        cs.registerSubsystem(
                shooterSubsystem, transferSubsystem, intakeSubsystem,driveSubsystem
        ); // end of cs

    }//end of teleop constructor

    public void periodic() {

        //every 5 milliseconds clear the cache
        if (loop.getElapsedTime() % 5 == 0) {
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }//end of for
        }//end of if

        driveSubsystem.drive(-driver.getLeftX(),-driver.getLeftY(),driver.getRightX());
        telemetry.addData("ticks", shooterSubsystem.getCurrentPosition());
        telemetry.addData("input",shooterSubsystem.shooter1.getPower());
        telemetry.addData("heading",driveSubsystem.getAngle());
        telemetry.update();
        //follower.update();
        //follower.setTeleOpDrive(
        //        -driver.getLeftX() ,
        //        -driver.getLeftY() ,
        //        -driver.getRightX(),
        //        false
        //);

        cs.run();
    }//end of periodic
    public void tStart(){
        //follower.update();
        //follower.startTeleopDrive(true);

    }// end of tStart

    public void tele() {

        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0)
                .whenActive(new IntakeCommand(intakeSubsystem, 1))
                .whenInactive(new IntakeCommand(intakeSubsystem, 0));



        new Trigger(() -> operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0)
                .whenActive(new ShooterSetPowCommand(shooterSubsystem, 1))
                .whenInactive(new ShooterSetPowCommand(shooterSubsystem, 0));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new ImuResetCommand(driveSubsystem));
    }//end of tele method





}//end of Robot