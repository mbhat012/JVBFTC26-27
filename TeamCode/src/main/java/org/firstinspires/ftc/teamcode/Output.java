package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Output {
    private final double outputPower = 1.0;
    DcMotor outputMotor;
    Input input;

    public Output(DcMotor outputMotor, Input input) {
        this.outputMotor = outputMotor;
        this.input = input;
    }

    public void run() {
        if (input.output()) {
            outputMotor.setPower(outputPower);
        } else {
            outputMotor.setPower(0);
        }
    }
}