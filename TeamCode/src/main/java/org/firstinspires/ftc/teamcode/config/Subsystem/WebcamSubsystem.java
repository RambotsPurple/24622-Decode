package org.firstinspires.ftc.teamcode.config.Subsystem;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;


public class WebcamSubsystem {

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;
    private List<AprilTagDetection> detectedTags = new ArrayList<>();
    private Telemetry telemetry;

    public WebcamSubsystem(HardwareMap hw, Telemetry telemetry) {
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hw.get(WebcamName.class, "webcam"));
        builder.setCameraResolution(new Size(640, 480));
        builder.addProcessor(aprilTagProcessor);

        visionPortal = builder.build();
    }

    public void update() {
        detectedTags = aprilTagProcessor.getDetections();
    }

    public List<AprilTagDetection> returnDetectedTags() {
        return detectedTags;
    }

    public void displayAprilTagDetection(AprilTagDetection detectedId) {
        if (detectedId == null) {
            telemetry.addLine("detected tag is null");
            return;
        }
        if (detectedId.metadata != null) {
            telemetry.addLine(String.format("(ID %d) %s", detectedId.id, detectedId.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f", detectedId.ftcPose.x, detectedId.ftcPose.y, detectedId.ftcPose.z));
            telemetry.addLine(String.format("Pitch Roll Yaw %6.1f %6.1f %6.1f", detectedId.ftcPose.pitch, detectedId.ftcPose.roll, detectedId.ftcPose.yaw));
            telemetry.addLine(String.format("Range Bearing Elevation %6.1f %6.1f %6.1f", detectedId.ftcPose.range, detectedId.ftcPose.bearing, detectedId.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("(ID %d) Unknown", detectedId.id));
            telemetry.addLine(String.format("Center (pixels): %6.0f %6.0f ", detectedId.center.x, detectedId.center.y));
        }
    }

    public AprilTagDetection getTagById (int id) {
        for (AprilTagDetection a : detectedTags) {
            if (a.id == id) return a;
        }

        return null;
    }

    public void stop() {
        if (visionPortal != null) visionPortal.close();
    }

}
