
package org.firstinspires.ftc.teamcode.opmode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Subsystem.WebcamSubsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "webcam")
public class webcamTest extends OpMode {
    WebcamSubsystem webcamSubsystem;

    @Override
    public void init() {
        webcamSubsystem = new WebcamSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        //---------------- WEBCAM STUFF
        webcamSubsystem.update();
        AprilTagDetection a = webcamSubsystem.getTagById(20);
        webcamSubsystem.displayAprilTagDetection(a);
    }

} // linearOpMod
