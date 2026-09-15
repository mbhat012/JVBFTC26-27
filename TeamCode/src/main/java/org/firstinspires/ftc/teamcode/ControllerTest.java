package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Sim Controller Test")
public class ControllerTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized! Press Play.");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Left Stick Y", gamepad1.left_stick_y);
            telemetry.addData("Button A Pressed", gamepad1.a);
            telemetry.update();
        }
    }
}
