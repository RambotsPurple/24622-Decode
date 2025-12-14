package org.firstinspires.ftc.teamcode.opmode.auto;
import org.firstinspires.ftc.teamcode.config.Commands.CommandGroups.AutoCommandGroup;
import org.firstinspires.ftc.teamcode.config.Paths.Paths;

import static org.firstinspires.ftc.teamcode.config.pedroPathing.Tuning.follower;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

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

