package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Motor Test", group = "test")
public class MotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor fl = hardwareMap.get(DcMotor.class, "fl");
        DcMotor bl = hardwareMap.get(DcMotor.class, "bl");
        DcMotor fr = hardwareMap.get(DcMotor.class, "fr");
        DcMotor br = hardwareMap.get(DcMotor.class, "br");

        telemetry.addLine("Hold X = fl, A = bl, Y = fr, B = br");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            fl.setPower(gamepad1.x ? 0.5 : 0);
            bl.setPower(gamepad1.a ? 0.5 : 0);
            fr.setPower(gamepad1.y ? 0.5 : 0);
            br.setPower(gamepad1.b ? 0.5 : 0);

            telemetry.addData("fl (X)", gamepad1.x);
            telemetry.addData("bl (A)", gamepad1.a);
            telemetry.addData("fr (Y)", gamepad1.y);
            telemetry.addData("br (B)", gamepad1.b);
            telemetry.update();
        }
    }
}