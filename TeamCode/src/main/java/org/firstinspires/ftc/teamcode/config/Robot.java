package org.firstinspires.ftc.teamcode.config;

import static org.firstinspires.ftc.teamcode.config.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.util.Timer;
import com.qualcomm.hardware.lynx.LynxModule;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.config.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.config.Commands.SetShooterVelocityCommand;
import org.firstinspires.ftc.teamcode.config.Util.*;
import org.firstinspires.ftc.teamcode.config.Subsystem.*;
import org.firstinspires.ftc.teamcode.config.pedroPathing.Constants;

import java.util.List;

public class Robot {

    private final List<LynxModule> allHubs;
    private final Timer loop = new Timer();
    public Alliance alliance;
    public CommandScheduler cs = CommandScheduler.getInstance();
    public ElapsedTime loopTimer;
    protected GamepadEx driver;
    protected GamepadEx oper;

    public IndexerSubsystem indexerSubsystem;

    // Instantiate subsystems
    public ShooterSubsystem shooterSubsystem;
    public IntakeSubsystem intakeSubsystem;

    public DriveSubsystem driveSubsystem;

    //public ElapsedTime turnTimer;


    public LimeLightSubsystem limeLightSubsystem;
    private final Telemetry telemetry;
    public state state;

    public ElapsedTime lastTime   = new ElapsedTime();


    //for pd control for auto alignment
    public double error = 0 ;
    public double lastError = 0 ;



    public Follower getFollower(){
        return follower;
    } // end of getFollower


    /**
     * This constructor below is for a single player auton anywhere
     * of the field
     * @param h hardwaremap
     * @param alliance blue or red
     * @param driver driver gamepad
     * @param telemetry allows for telemetry output
     **/
    public Robot(HardwareMap h, Alliance alliance, Gamepad driver,Gamepad oper, Telemetry telemetry) {
        shooterSubsystem = new ShooterSubsystem(h,telemetry);
        intakeSubsystem = new IntakeSubsystem(h);
        limeLightSubsystem = new LimeLightSubsystem(h,alliance);
        indexerSubsystem = new IndexerSubsystem(h,telemetry);
        driveSubsystem  = new DriveSubsystem(h);
        follower = Constants.createFollower(h);
        this.alliance = alliance;
        this.driver = new GamepadEx(driver);
        this.oper = new GamepadEx(oper);
        this.telemetry = telemetry;
        loopTimer = new ElapsedTime();

        allHubs = h.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }//end of for

        loop.resetTimer();
        cs.registerSubsystem(
                shooterSubsystem, intakeSubsystem,limeLightSubsystem, indexerSubsystem
        );

    } //end of teleop constructor

    /**
     * The constructor below is for auto on any side of the field
     *
     * @param h
     * @param alliance
     * @param telemetry
     */

    public Robot(HardwareMap h, Alliance alliance, Telemetry telemetry) {
        shooterSubsystem = new ShooterSubsystem(h, telemetry);
        intakeSubsystem = new IntakeSubsystem(h);
        limeLightSubsystem = new LimeLightSubsystem(h, alliance);
        indexerSubsystem = new IndexerSubsystem(h, telemetry);
        loopTimer = new ElapsedTime();

        follower = Constants.createFollower(h);
        follower.setStartingPose(new Pose(0,0,0));
        this.alliance = alliance;
        this.telemetry = telemetry;

        // instaniate the lynx mod
        allHubs = h.getAll(LynxModule.class);
        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }//end of for

        cs.registerSubsystem(
                shooterSubsystem, intakeSubsystem,limeLightSubsystem, indexerSubsystem
        ); // end of cs

    }//end of teleop constructor

    /**
     * loops periodically during teleOp
     */
    public void tPeriodic() {
        teleTelemetry();

        //every 5 milliseconds clear the cache
        if (loop.getElapsedTime() % 5 == 0) {
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            } // end of for
        } // end of if

        // logic for auto tracking
        double turn;

        if (state == state.Locked){
            error = limeLightSubsystem.getHorizontalError();
            turn = trackTo(error);
        } else {
            turn = -driver.getRightX();
        } // end of if..else
        telemetry.addData("turnh",turn);

        ////params for drive
        follower.setTeleOpDrive(
               driver.getLeftY() ,
               -driver.getLeftX() ,
                turn,
               false
        );
        
        follower.update();
        telemetry.update();
        cs.run();
    } //end of periodic
    public void aPeriodic(){
        if (loop.getElapsedTime() % 5 == 0) {
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            } // end of for
        } // end of if
        autoTelemetry();
        //follower.update();
        //
        //cs.run();
    } //end of aPeriodic

    /**
     * Run on start of teleOp
     */
    public void tStart() {
        //turnTimer = new ElapsedTime();
        state = state.Manual;
        limeLightSubsystem.lStart();
        follower.update();
        follower.startTeleopDrive(true);
    } // end of tStart

    /**
     * We use the error from our limelight for our pd and use that to adjust
     * to the april tag accordingly
     * @param error
     * @param time
     * @return pow
     */
    double kp = 0.1;
    double kd = 0.002;

    public double trackTo(double error) {


        double derivative = (error - lastError) / lastTime.seconds();
        double output = kp * error + kd * derivative;

        lastError = error;
        lastTime.reset();

        return output;
    }


    /**
     * Sets up listeners at the start of teleOp
     */
    public void tele() {

        // event listeners
        // all input goes here except for driving which is passed in periodically
        // --------------------

        /*
         * right trigger - hold: intake
         * right bumper - toggle: indexer
         * left bumper - toggle: Locked state so LLS take's over turn
         * A - activate shooter
         * B - deactivate shooter
         */

        new Trigger(() -> oper.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0)
                .whenActive(new IntakeCommand(intakeSubsystem, 1))
                .whenInactive(new IntakeCommand(intakeSubsystem, 0));

        oper.getGamepadButton(GamepadKeys.Button.A).toggleWhenActive(
                new SetShooterVelocityCommand(shooterSubsystem, 5600),
                new SetShooterVelocityCommand(shooterSubsystem, 0)
        );

        oper.getGamepadButton(GamepadKeys.Button.X).toggleWhenActive(
                new SetShooterVelocityCommand(shooterSubsystem, 2800),
                new SetShooterVelocityCommand(shooterSubsystem, 0)
        );





        oper.getGamepadButton(GamepadKeys.Button.B).toggleWhenActive(
                new IndexCommand(indexerSubsystem, 1),
                new IndexCommand(indexerSubsystem, 0)
        );

        new Trigger(() -> oper.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0)
                .whenActive( new ParallelCommandGroup(
                        new IntakeCommand(intakeSubsystem, -.4),new IndexCommand(indexerSubsystem,-.4)))
                .whenInactive( new ParallelCommandGroup(
                        new IntakeCommand(intakeSubsystem, 0),new IndexCommand(indexerSubsystem,0)));


        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).toggleWhenActive(
               new InstantCommand(()-> setState(state.Locked)),
                new InstantCommand(()-> setState(state.Manual))
        );

        driver.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenActive(
                new InstantCommand(()-> follower.setPose(follower.getPose().withHeading(0)))
        );


    } //end of tele method

    public void setState(state s){
        this.state = s;
        //if (s == state.Locked) {
        //    turnTimer.reset();
        //    lastError = 0;
        //}
    }

    /**
     * Telemetry data for teleOp
     */
    public void teleTelemetry() {
        double loopTime = loopTimer.milliseconds();
        // telemetry.addData("ticks", shooterSubsystem.getCurrentPosition());
        telemetry.addLine("--------------------------SubSystem------------------------");
        telemetry.addData("horizontal",limeLightSubsystem.getHorizontalError());
        telemetry.addData("dist",limeLightSubsystem.getDist());
        //telemetry.addData("input", shooterSubsystem.shooter1.getPower());
        telemetry.addData("heading-w-follower", follower.getPose().getHeading());
        telemetry.addData("state", state);
        telemetry.addData("shooter 1 vel with ticks", shooterSubsystem.shooter1.getVelocity());
        telemetry.addData("shooter 2 vel", shooterSubsystem.shooter2.getVelocity());
        telemetry.addData("shooter RPM", shooterSubsystem.getRPM());
        telemetry.addLine("--------------------------LOOP TIMINGS------------------------");
        loopTimer.reset();
        telemetry.addData("Loop Time (ms)", loopTime);
        telemetry.addData("Loop Rate (Hz)", 1000 / loopTime); // Approximate

    } //end of teleTelemetry

    /**
     * Telemetry data for autoOp
     */
    public void autoTelemetry() {
        double loopTime = loopTimer.milliseconds();
        loopTimer.reset();
        telemetry.addData("Loop Time (ms)", loopTime);
        telemetry.addData("Loop Rate (Hz)", 1000 / loopTime); // Approximate
        telemetry.update();
    } //end of autoTelemetry

    /**
     * loops periodically during autoOp
     */


    /**
     * Runs on the start of autoOp
     */
    public void aStart(Pose p){
        state = state.Manual;
        follower.setStartingPose(p);
    } //end of aStart
} //end of Robot