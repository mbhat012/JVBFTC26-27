package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Output {
    private final double outputPower = 1.0;

    private final double middlePower = 1.0;
    DcMotor outputMotor;
    DcMotor middleMotor;
    Input input;

    public Output(DcMotor outputMotor, DcMotor middleMotor, Input input) {
        this.outputMotor = outputMotor;
        this.input = input;
        this.middleMotor = middleMotor;
    }

    public void run() {
        if (input.output()) {
            outputMotor.setPower(outputPower);
            middleMotor.setPower(middlePower);
        } else {
            outputMotor.setPower(0);
            if (!input.intake()) {
                middleMotor.setPower(0);
            }
        }
    }
}