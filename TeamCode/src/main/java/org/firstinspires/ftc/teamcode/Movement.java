package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Movement {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double slowMult = 0.2;
    DcMotor LeftFront, LeftBack, RightFront, RightBack;
    Input input;

    public Movement(DcMotor LF, DcMotor LB, DcMotor RF, DcMotor RB, Input input) {
        LeftFront = LF;
        LeftBack = LB;
        RightFront = RF;
        RightBack = RB;
        this.input = input;
    }

    private void move(double x, double y, double rot) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        double frontLeftPower = (y + x + rot) / denominator;
        double backLeftPower = (y - x + rot) / denominator;
        double frontRightPower = (y - x - rot) / denominator;
        double backRightPower = (y + x - rot) / denominator;

        LeftFront.setPower(FLFrontDir * frontLeftPower);
        LeftBack.setPower(BLFrontDir * backLeftPower);
        RightFront.setPower(FRFrontDir * frontRightPower);
        RightBack.setPower(BRFrontDir * backRightPower);
    }

    public void run() {
        double x = input.moveX();
        double y = input.moveY();
        double rot = input.rotation();
        if (input.slow()) {
            x *= slowMult;
            y *= slowMult;
            rot *= slowMult;
        }
        move(x, y, rot);
    }
}