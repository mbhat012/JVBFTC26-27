package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
public class Intake {
    private final double intakePower = 1;

    private final double middlePower = 0.6;
    DcMotor intakeMotor;

    DcMotor middleMotor;
    Input input;
    public Intake(DcMotor intakeMotor, DcMotor middleMotor, Input input) {
        this.intakeMotor = intakeMotor;
        this.input = input;
        this.middleMotor = middleMotor;
    }
    public void run() {
        if (input.intake()) {
            intakeMotor.setPower(intakePower);
            middleMotor.setPower(middlePower);
        } else {
            intakeMotor.setPower(0);
            middleMotor.setPower(0);
        }
    }
}