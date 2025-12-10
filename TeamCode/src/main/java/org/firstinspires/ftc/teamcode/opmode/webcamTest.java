
package org.firstinspires.ftc.teamcode.opmode;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.Robot;
import org.firstinspires.ftc.teamcode.config.Subsystem.Webcam;
import org.firstinspires.ftc.teamcode.config.Util.Alliance;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "webcam")
public class webcamTest extends OpMode {
    Webcam webcam;

    @Override
    public void init() {
        webcam = new Webcam(hardwareMap, telemetry);
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {

        //---------------- WEBCAM STUFF
        webcam.update();
        AprilTagDetection a = webcam.getTagById(20);
        webcam.displayAprilTagDetection(a);
    }

} // linearOpMod
