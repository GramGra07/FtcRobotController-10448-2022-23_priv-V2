package org.firstinspires.ftc.teamcode.auto;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.teleOp.robotCentric;

import java.util.List;
import java.util.Objects;

@Autonomous(name = "minimalAuto", group = "Robot")
//@Disabled
public class minimalAuto extends robotCentric {
    public int turn = 77;


    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 28;
    static final double WHEEL_DIAMETER_MM = 96;
    static final double WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_MM * 0.0393701;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * 15) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    static final double COUNTS_PER_MOTOR_REV_dead = 8192;
    static final double WHEEL_DIAMETER_MM_dead = 96;
    static final double WHEEL_DIAMETER_INCHES_dead = WHEEL_DIAMETER_MM_dead * 0.0393701;     // For figuring circumference
    static final double COUNTS_PER_INCH_dead = (COUNTS_PER_MOTOR_REV_dead) /
            (WHEEL_DIAMETER_INCHES_dead * Math.PI);

    static final double ROBOT_DIAMETER = 13.05;
    //arm
    final int baseArmPosition = 0;
    public final int armLimit = robotCentric.armLimit;
    public final int lowPoleVal = robotCentric.lowPoleVal;//should be about 1/3 of arm limit
    public final int midPoleVal = robotCentric.midPoleVal;//should be about 2/3 of arm limit
    public final int topPoleVal = armLimit;//should be close to armLimit
    static final double COUNTS_PER_MOTOR_REV_arm = 28;
    static final double DRIVE_GEAR_REDUCTION_arm = 40;
    static final double WHEEL_DIAMETER_INCHES_arm = 1.102;     // For figuring circumference
    static final double COUNTS_PER_INCH_arm = (COUNTS_PER_MOTOR_REV_arm * DRIVE_GEAR_REDUCTION_arm) /
            (WHEEL_DIAMETER_INCHES_arm * 3.1415);

    static final double COUNTS_PER_INCH_Side_dead = -665.08;
    static final double COUNTS_PER_INCH_Side = -100;
    public final int baseClawVal = 30;
    public final int magicNumOpen = 60;
    public double position = 0;//sets servo position to 0-1 multiplier
    public final double degree_mult = 0.00555555554;//100/180

    private static final String TFOD_MODEL_ASSET = "custom.tflite";

    private static final String[] LABELS = {
            "capacitor",//3
            "led",//1
            "resistor"//2
    };

    private static final String VUFORIA_KEY =
            "AXmzBcj/////AAABme5HSJ/H3Ucup73WSIaV87tx/sFHYaWfor9OZVg6afr2Bw7kNolHd+mF5Ps91SlQpgBHulieI0jcd86kqJSwx46BZ8v8DS5S5x//eQWMEGjMDnvco4/oTcDwuSOLIVZG2UtLmJXPS1L3CipjabePFlqAL2JtBlN78p6ZZbRFSHW680hWEMSimZuQy/cMudD7J/MjMjMs7b925b8BkijlnTQYr7CbSlXrpDh5K+9fLlk2OyEZ4w7tm7e4UJDInJ/T3oi8PqqKCqkUaTkJWlQsvoELbDu5L2FgzsuDhBLe2rHtJRqfORd7n+6M30UdFSsxqq5TaZztkWgzRUr1GC3yBSTS6iFqEuL3g06GrfwOJF0F";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;
    public int spot = 0;
    public double IN_distanceR = 0;//in distance for distance sensor 1
    public double IN_distanceL = 0;
    public double myMagic = 7;
    //color
    final float[] hsvValues = new float[3];//gets values for color sensor
    private int redVal = 0;//the red value in rgb
    private int greenVal = 0;//the green value in rgb
    private int blueVal = 0;//the blue value in rgb
    private String colorName = "N/A";//gets color name
    NormalizedColorSensor colorSensorR;//declaring the colorSensor variable
    NormalizedColorSensor colorSensorL;//declaring the colorSensor variable
    public TouchSensor touchSensor;
    public RevBlinkinLedDriver lights;
    private DigitalChannel red1;
    private DigitalChannel green1;
    private DigitalChannel red2;
    private DigitalChannel green2;
    private DigitalChannel red3;
    private DigitalChannel green3;
    private DigitalChannel red4;
    private DigitalChannel green4;

    @Override
    public void runOpMode() {
        lights = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
        rDistance = hardwareMap.get(DistanceSensor.class, "rDistance");
        lDistance = hardwareMap.get(DistanceSensor.class, "lDistance");
        fDistance = hardwareMap.get(DistanceSensor.class, "fDistance");
        red1 = hardwareMap.get(DigitalChannel.class, "red1");
        green1 = hardwareMap.get(DigitalChannel.class, "green1");
        red2 = hardwareMap.get(DigitalChannel.class, "red2");
        green2 = hardwareMap.get(DigitalChannel.class, "green2");
        red3 = hardwareMap.get(DigitalChannel.class, "red3");
        green3 = hardwareMap.get(DigitalChannel.class, "green3");
        red4 = hardwareMap.get(DigitalChannel.class, "red4");
        green4 = hardwareMap.get(DigitalChannel.class, "green4");
        colorSensorR = hardwareMap.get(NormalizedColorSensor.class, "colorSensorR");
        colorSensorL = hardwareMap.get(NormalizedColorSensor.class, "colorSensorL");
        touchSensor = hardwareMap.get(TouchSensor.class, ("touchSensor"));

        motorFrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBackLeft = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorFrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorBackRight = hardwareMap.get(DcMotor.class, "motorBackRight");
        deadWheel = hardwareMap.get(DcMotor.class, "deadWheel");
        //deadWheelL = hardwareMap.get(DcMotor.class, "deadWheelL");
        //deadWheelR = hardwareMap.get(DcMotor.class, "deadWheelR");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        sparkLong = hardwareMap.get(DcMotor.class, "sparkLong");

        //onInit();
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
        sparkLong.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sparkLong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        deadWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //deadWheelL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //deadWheelR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorBackRight.setZeroPowerBehavior(BRAKE);
        motorBackLeft.setZeroPowerBehavior(BRAKE);
        motorFrontRight.setZeroPowerBehavior(BRAKE);
        motorFrontLeft.setZeroPowerBehavior(BRAKE);
        red2.setMode(DigitalChannel.Mode.OUTPUT);
        green2.setMode(DigitalChannel.Mode.OUTPUT);
        red1.setMode(DigitalChannel.Mode.OUTPUT);
        green1.setMode(DigitalChannel.Mode.OUTPUT);
        red3.setMode(DigitalChannel.Mode.OUTPUT);
        green3.setMode(DigitalChannel.Mode.OUTPUT);
        red4.setMode(DigitalChannel.Mode.OUTPUT);
        green4.setMode(DigitalChannel.Mode.OUTPUT);

        telemetry.addData("Starting at", "%7d :%7d",
                motorBackRight.getCurrentPosition(),
                motorBackLeft.getCurrentPosition(),
                motorFrontLeft.getCurrentPosition());
        closeClaw();
        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(1.0, 16.0 / 9.0);
        }
        runVu(6, false);
        telemetry.update();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        closeClaw();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        if (opModeIsActive()) {
            lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.valueOf(getColor()));
            doSetup();
            if (spot == 1) {
                simplerGoSpot(2, 0, 1, 2.5, 0.5, false, 0, false, false, 0,
                        2, 4);
            }
            if (spot == 2) {
                simplerGoSpot(2, 0, 2, 2.5, 0.5, false, 0, false, false, 0,
                        2, 4);
            }
            if (spot == 3) {
                simplerGoSpot(2, 0, 3, 2.5, 0.5, false, 0, false, false, 0,
                        2, 4);
            }
            telemetry.update();
        }
    }

    public void doSetup() {
        runVu(6, true);
        if (spot == 0) {
            spot = (int) (Math.floor(Math.random() * (3) + 1));
        }
        if (spot == 1) {
            green1.setState(true);
            red1.setState(false);
        } else if (spot == 2) {
            green2.setState(true);
            red2.setState(false);
        } else if (spot == 3) {
            green3.setState(true);
            red3.setState(false);
        } else {
            green1.setState(false);
            red1.setState(false);
            green2.setState(false);
            red2.setState(false);
            green3.setState(false);
            red3.setState(false);
        }
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.valueOf(getColor()));
    }

    public void simplerGoSpot(double currX, double currY, double targetX, double targetY, double power, boolean combo, int pose
            , boolean isUp, boolean endTurn, int turn, int timeOutX, int timeOutY) {
        double sidewaysInches = (targetX - currX) * xMult;
        double fwdInches = (targetY - currY) * yMult;
        telemetry.addData("fwdInches", fwdInches);
        telemetry.addData("sidewaysInches", sidewaysInches);
        sideWaysEncoderDrive(power, sidewaysInches, timeOutX);
        if (!combo) {
            encoderDrive(power, fwdInches, fwdInches, timeOutY);
        } else {
            encoderComboFwd(power, fwdInches, fwdInches, pose, timeOutY, isUp);
        }
        if (endTurn) {
            turn(turn);
        }
        setOvr(targetX, targetY);
        telemetry.update();
    }

    public String getColor() {
        final String[] favColors = {
                "RAINBOW_RAINBOW_PALETTE",
                "RAINBOW_PARTY_PALETTE",
                "BEATS_PER_MINUTE_RAINBOW_PALETTE",
                "BEATS_PER_MINUTE_PARTY_PALETTE",
                //"FIRE_MEDIUM",
                "COLOR_WAVES_RAINBOW_PALETTE",
                "COLOR_WAVES_PARTY_PALETTE",
                "CP2_END_TO_END_BLEND_TO_BLACK",
                "CP2_BREATH_SLOW",
                "CP1_2_END_TO_END_BLEND_1_TO_2",
                "CP1_2_END_TO_END_BLEND",
                "HOT_PINK",
                "GOLD",
                "VIOLET"
        };
        final int min = 0;
        final int max = favColors.length - 1;
        return favColors[(int) Math.floor(Math.random() * (max - min + 1) + min)];
    }

    public void runVu(int timeoutS, boolean giveSpot) {
        runtime.reset();
        while (opModeIsActive() && (spot == 0)) {
            if (runtime.seconds() > timeoutS) {
                spot = 4;
            }
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Objects Detected", updatedRecognitions.size());

                    // step through the list of recognitions and display image position/size information for each one
                    // Note: "Image number" refers to the randomized image orientation/number
                    for (Recognition recognition : updatedRecognitions) {
                        double col = (recognition.getLeft() + recognition.getRight()) / 2;
                        double row = (recognition.getTop() + recognition.getBottom()) / 2;
                        double width = Math.abs(recognition.getRight() - recognition.getLeft());
                        double height = Math.abs(recognition.getTop() - recognition.getBottom());

                        telemetry.addData("", " ");
                        telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                        telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
                        telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
                        if (giveSpot && spot == 0) {
                            if (Objects.equals(recognition.getLabel(), "led")) {
                                spot += 1;
                                break;
                            }
                            if (Objects.equals(recognition.getLabel(), "resistor")) {
                                spot += 2;
                                break;
                            }
                            if (Objects.equals(recognition.getLabel(), "capacitor")) {
                                spot += 3;
                                break;
                            }
                        }
                    }
                    telemetry.update();
                }
            }
        }
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
