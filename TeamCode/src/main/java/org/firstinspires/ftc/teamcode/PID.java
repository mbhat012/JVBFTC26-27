package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    private final double kP, kI, kD, max;
    private double prevError = 0;
    private double integralSum = 0;
    private boolean first = true;
    private final ElapsedTime timer = new ElapsedTime();

    public PID(double kP, double kI, double kD, double max) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.max = max;
    }

    // Call when starting a new target so old error doesn't carry over
    public void reset() {
        prevError = 0;
        integralSum = 0;
        first = true;
    }

    public double update(double error) {
        double dt = timer.seconds();
        timer.reset();

        double derivative = 0;
        if (!first && dt > 0) {
            derivative = (error - prevError) / dt;
            integralSum += error * dt;
        }
        first = false;
        prevError = error;

        // Anti-windup: keep the I term from exceeding the output limit
        if (kI != 0) {
            double limit = max / kI;
            integralSum = Math.max(-limit, Math.min(limit, integralSum));
        }

        double out = kP * error + kI * integralSum + kD * derivative;
        return Math.max(-max, Math.min(max, out));
    }
    public static double wrapDegrees(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}