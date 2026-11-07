package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

//Type of Program (Auto or TeleOp)
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "test")


// INT: 0
// FL: 1
// BL: 2
// BR: 3
// EXP HUB
// FR: 0

// temp stuff so i can push Iakfjleridjfopvervji;wekf hello ethan

public class TeleOp extends LinearOpMode {
    final int FLFrontDir = -1;
    final int FRFrontDir = 1;
    final int BLFrontDir = 1;
    final int BRFrontDir = 1;
    final double sprintMoveMult = 1.2;
    final double sprintTurnMult = 1.2;
    final double brakeMoveMult = 0.7;
    final double brakeTurnMult = 0.7;

    double flywheelPower = 1;

    public void runOpMode() throws InterruptedException {
        // GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        DcMotor LeftFront = hardwareMap.get(DcMotor.class, "fl");
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor RightFront = hardwareMap.get(DcMotor.class, "fr");
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor LeftBack = hardwareMap.get(DcMotor.class, "bl");
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor RightBack = hardwareMap.get(DcMotor.class, "br");
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor MiddleMotor = hardwareMap.get(DcMotor.class, "Middle");
        MiddleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        MiddleMotor.setDirection(DcMotor.Direction.REVERSE);
        MiddleMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        DcMotor IntakeMotor = hardwareMap.get(DcMotor.class, "Intake");
        IntakeMotor.setDirection(DcMotor.Direction.REVERSE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Input input = new Input(gamepad1, gamepad2);
        Movement movement = new Movement(LeftFront, LeftBack, RightFront, RightBack, input);
        Intake intake = new Intake(IntakeMotor, MiddleMotor, input);

        DcMotor OutputMotor = hardwareMap.get(DcMotor.class, "Output");
        OutputMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        // OutputMotor.setDirection(DcMotor.Direction.REVERSE);
        OutputMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Output output = new Output(OutputMotor, MiddleMotor, input);

        waitForStart();
        // pinpoint.resetPosAndIMU();
        // pinpoint.setOffsets(9.5, 0.5, DistanceUnit.CM);

        while (opModeIsActive()) {
            movement.run();
            intake.run();
            output.run();

            telemetry.addData("Move X (left stick X)", "%.2f", input.moveX());
            telemetry.addData("Move Y (left stick Y)", "%.2f", input.moveY());
            telemetry.addData("Rotation (right stick X)", "%.2f", input.rotation());
            telemetry.addData("Slow (L1)", input.slow());
            telemetry.addData("Reset (Y)", input.reset());
            telemetry.addData("Intake (L2)", input.intake());
            telemetry.addData("Output (R2)", input.output());

            telemetry.update();
        }
    }
}