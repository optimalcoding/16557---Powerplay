package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.Arrays;

@TeleOp(name = "Teleop")
public class Teleop extends LinearOpMode {
    Robot robot = new Robot();



    int robotCycle = 0;

    @Override
    public void runOpMode() throws InterruptedException {



        //initialization variables, notifying robot is initialized and shows how long robot ran for
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Status", "Runtime " + robot.runtime.toString());
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Robot Cycle", robotCycle);
            telemetry.addData("Match Time (s)", getRuntime());
            telemetry.update();

            //controller 1 functions
            double FrontLeftVal = gamepad1.left_stick_y - gamepad1.left_stick_x + -gamepad1.right_stick_x;
            double FrontRightVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) - -gamepad1.right_stick_x;
            double BackLeftVal = gamepad1.left_stick_y + (gamepad1.left_stick_x) + -gamepad1.right_stick_x;
            double BackRightVal = gamepad1.left_stick_y - (gamepad1.left_stick_x) - -gamepad1.right_stick_x;

            //Move range to between 0 and +1, if not already
            double[] wheelPowers = {FrontRightVal, FrontLeftVal, BackLeftVal, BackRightVal};
            Arrays.sort(wheelPowers);
            if (wheelPowers[3] > 1) {
                FrontLeftVal /= wheelPowers[3];
                FrontRightVal /= wheelPowers[3];
                BackLeftVal /= wheelPowers[3];
                BackRightVal /= wheelPowers[3];
            }
                robot.frontLeft.setPower(FrontLeftVal * 1);
                robot.frontRight.setPower(FrontRightVal * 1);
                robot.backLeft.setPower(BackLeftVal * 1);
                robot.backRight.setPower(BackRightVal * 1);


            idle();
        }
    }
}