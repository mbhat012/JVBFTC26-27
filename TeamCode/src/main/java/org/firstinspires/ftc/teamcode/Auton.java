package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auton")
public class Auton extends LinearOpMode {
    // Tune these
    final double DRIVE_POWER = 0.5;
    final double FORWARD_TIME = 1.0;  // seconds
    final double PAUSE_TIME = 1.0;    // seconds stopped
    final double BACK_TIME = 1.0;     // seconds

    // Same signs you use in Movement (order: FL, BL, FR, BR)
    final int[] dir = {-1, 1, 1, 1};

    DcMotor fl, bl, fr, br;

    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotor.class, "fl");
        bl = hardwareMap.get(DcMotor.class, "bl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        br = hardwareMap.get(DcMotor.class, "br");

        for (DcMotor m : new DcMotor[]{fl, bl, fr, br}) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            m.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();

        drive(DRIVE_POWER, FORWARD_TIME, "Forward");
        drive(0, PAUSE_TIME, "Stopped");
        drive(-DRIVE_POWER, BACK_TIME, "Backward");
        drive(0, 0, "Done");
    }

    // y > 0 = forward, y < 0 = backward
    private void drive(double y, double seconds, String label) {
        fl.setPower(dir[0] * y);
        bl.setPower(dir[1] * y);
        fr.setPower(dir[2] * y);
        br.setPower(dir[3] * y);

        telemetry.addData("Step", label);
        telemetry.update();

        long end = System.currentTimeMillis() + (long) (seconds * 1000);
        while (opModeIsActive() && System.currentTimeMillis() < end) {
            idle();
        }
    }
}