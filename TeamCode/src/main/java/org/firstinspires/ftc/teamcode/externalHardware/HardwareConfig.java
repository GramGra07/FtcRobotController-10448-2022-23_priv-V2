//import
package org.firstinspires.ftc.teamcode.externalHardware;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.testOpModes.distanceSensorCalibrator;

import java.util.List;
import java.util.Objects;

public class HardwareConfig {
    //auto
    public int turn = 77;
    public double yMult = 24;
    public double xMult = 10;
    public double ovrCurrX = 0;
    public double ovrCurrY = 0;
    public double ovrTurn = 0;
    public static final double COUNTS_PER_INCH_Side_dead = -665.08;
    public static final double COUNTS_PER_INCH_Side = -100;
    //offset
    public double rOffset = distanceSensorCalibrator.rOffset;
    public double lOffset = distanceSensorCalibrator.lOffset;//-5.1;
    public double fOffset = distanceSensorCalibrator.fOffset;
    public double bOffset = distanceSensorCalibrator.bOffset;
    //arm
    public static final double COUNTS_PER_MOTOR_REV_arm = 28;
    public static final double DRIVE_GEAR_REDUCTION_arm = 40;
    public static final double WHEEL_DIAMETER_INCHES_arm = 1.102;     // For figuring circumference
    public static final double COUNTS_PER_INCH_arm = (COUNTS_PER_MOTOR_REV_arm * DRIVE_GEAR_REDUCTION_arm) /
            (WHEEL_DIAMETER_INCHES_arm * 3.1415);
    //wheels
    public static final double COUNTS_PER_MOTOR_REV = 28;
    public static final double WHEEL_DIAMETER_MM = 96;
    public static final double DRIVE_GEAR_REDUCTION = 15;
    public static final double WHEEL_DIAMETER_INCHES = WHEEL_DIAMETER_MM * 0.0393701;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    public static final double COUNTS_PER_MOTOR_REV_dead = 8192;
    public static final double WHEEL_DIAMETER_MM_dead = 96;
    public static final double WHEEL_DIAMETER_INCHES_dead = WHEEL_DIAMETER_MM_dead * 0.0393701;     // For figuring circumference
    public static final double COUNTS_PER_INCH_dead = (COUNTS_PER_MOTOR_REV_dead) /
            (WHEEL_DIAMETER_INCHES_dead * Math.PI);
    //other variables
    public boolean slowModeIsOn = false;//declaring the slowModeIsOn variable
    public boolean reversed = false;//declaring the reversed variable

    //servo variables
    public double position = 0;//sets servo position to 0-1 multiplier
    public final double degree_mult = 0.00555555554;//100/180
    public final int baseClawVal = 30;//declaring the baseClawVal variable
    public final int magicNumOpen = 60;//declaring the magicNumOpen variable
    public boolean clawOpen = false;//declaring the clawOpen variable

    //arm labels
    public final int baseArmPosition = 0;
    public static final int armLimit = 4250;//declaring the armLimit variable
    public final int baseArm = 0;//declaring the baseArm variable
    public static final int lowPoleVal = 1570;//should be about 1/3 of arm limit
    public static final int midPoleVal = 290;//should be about 2/3 of arm limit
    public static final int fiveTallConeVal = 300;
    public static final int topPoleVal = armLimit;//should be close to armLimit
    //public boolean limiter = false;//declaring the limiter variable, is on or off
    //public boolean limiting = false;//declaring the limiting variable
    public final ElapsedTime runtime = new ElapsedTime();

    //rumble
    public Gamepad.RumbleEffect customRumbleEffect;//declaring the customRumbleEffect variable
    public final double endgame = 120;//declaring the endgame variable
    public boolean isEndgame = false;//declaring the isEndgame variable
    public Gamepad.RumbleEffect customRumbleEffect1;// declaring the customRumbleEffect1 variable
    public boolean rumble = false;//declaring the rumble variable
    public final double end = 150;//declaring the end variable
    public boolean isEnd = false;//declaring the isEnd variable

    //rake
    public final int baseFlip = -90;//declaring the baseFlip variable
    public final int magicFlip = baseFlip + 50;//declaring the magicFlip variable
    public final int baseUnCone = 0;
    public final int magicUnCone = baseUnCone + 90;
    double armPower;
    //motors
    public DcMotor motorFrontLeft = null;
    public DcMotor motorBackLeft = null;
    public DcMotor motorFrontRight = null;
    public DcMotor motorBackRight = null;
    public DcMotor deadWheel = null;//declaring the deadWheel motor
    public DcMotor tapeMeasure = null;
    //servo
    public DcMotor sparkLong = null;
    public TouchSensor touchSensor;
    public NormalizedColorSensor colorSensorR;//declaring the colorSensor variable
    public NormalizedColorSensor colorSensorL;//declaring the colorSensor variable
    public DigitalChannel red1;
    public DigitalChannel green1;
    public DigitalChannel red2;
    public DigitalChannel green2;
    public DigitalChannel red3;
    public DigitalChannel green3;
    public DigitalChannel red4;
    public DigitalChannel green4;
    public DistanceSensor rDistance;//declaring the rDistance sensor
    public DistanceSensor lDistance;//declaring the lDistance sensor
    public DistanceSensor fDistance;//declaring the fDistance sensor
    public RevBlinkinLedDriver lights;
    public Servo clawServo = null;
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
    private int spot = 0;
    //color
    final float[] hsvValues = new float[3];//gets values for color sensor
    private final float redVal = 0;//the red value in rgb
    private final float greenVal = 0;//the green value in rgb
    private final float blueVal = 0;//the blue value in rgb
    private final String colorName = "N/A";//gets color name
    //
    public String statusVal = "OFFLINE";
    public double fDistanceVal = 0;
    public double lDistanceVal = 0;
    public double rDistanceVal = 0;
    //isRight side
    public boolean right = true;//declaring the right variable
    private final float redValR = 0;//the red value in rgb
    private final float greenValR = 0;//the green value in rgb
    private final float blueValR = 0;//the blue value in rgb
    private final float redValL = 0;//the red value in rgb
    private final float greenValL = 0;//the green value in rgb
    private final float blueValL = 0;//the blue value in rgb
    //assistance to driver
    public boolean assisting = false;
    //
    //slow mode
    public double slowMult = 3;
    public double slowPower;
    //driving
    public double xControl;
    public double yControl;
    public double frontRightPower;
    public double frontLeftPower;
    public double backRightPower;
    public double backLeftPower;
    //
    //field centric
    double gamepadX;
    double gamepadY;
    double gamepadHypot;
    double controllerAngle;
    double robotDegree;
    double movementDegree;
    double offSet = 0;
    //
    //tape measure
    double tapePower;
    public double tapeMeasureDiameter = 7.5;
    public int tapeMeasureLength = 15 * 12;
    public double countsPerInchTape = 25;
    public double tickPerTapeMeasure = countsPerInchTape * tapeMeasureLength;
    //
    //imu
    public BNO055IMU imu;
    public Orientation angles;     //imu uses these to find angles and classify them
    public Acceleration gravity;    //Imu uses to get acceleration
    //
    //external
    HardwareMap hardwareMap = null;

    private LinearOpMode myOpMode = null;   // gain access to methods in the calling OpMode.

    public HardwareConfig(LinearOpMode opmode) {
        myOpMode = opmode;
    }

    public void init(HardwareMap ahwMap) {
        updateStatus("Initializing");
        ElapsedTime runtime = new ElapsedTime();//declaring the runtime variable
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = ahwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = imu.getGravity();
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        lights = ahwMap.get(RevBlinkinLedDriver.class, "blinkin");
        rDistance = ahwMap.get(DistanceSensor.class, "rDistance");//getting the rDistance sensor
        lDistance = ahwMap.get(DistanceSensor.class, "lDistance");//getting the lDistance sensor
        fDistance = ahwMap.get(DistanceSensor.class, "fDistance");//getting the fDistance sensor
        red1 = ahwMap.get(DigitalChannel.class, "red1");//getting the red1 light
        green1 = ahwMap.get(DigitalChannel.class, "green1");//getting the green1 light
        red2 = ahwMap.get(DigitalChannel.class, "red2");//getting the red2 light
        green2 = ahwMap.get(DigitalChannel.class, "green2");//getting the green2 light
        red3 = ahwMap.get(DigitalChannel.class, "red3");//getting the red3 light
        green3 = ahwMap.get(DigitalChannel.class, "green3");//getting the green3 light
        red4 = ahwMap.get(DigitalChannel.class, "red4");//getting the red4 light
        green4 = ahwMap.get(DigitalChannel.class, "green4");//getting the green4 light
        colorSensorR = ahwMap.get(NormalizedColorSensor.class, "colorSensorR");
        colorSensorL = ahwMap.get(NormalizedColorSensor.class, "colorSensorL");
        // Declare our motors
        // Make sure your ID's match your configuration
        motorFrontLeft = ahwMap.get(DcMotor.class, "motorFrontLeft");//getting the motorFrontLeft motor
        motorBackLeft = ahwMap.get(DcMotor.class, "motorBackLeft");//getting the motorBackLeft motor
        motorFrontRight = ahwMap.get(DcMotor.class, "motorFrontRight");//getting the motorFrontRight motor
        motorBackRight = ahwMap.get(DcMotor.class, "motorBackRight");//getting the motorBackRight motor
        deadWheel = ahwMap.get(DcMotor.class, "deadWheel");//getting the deadWheel motor
        clawServo = ahwMap.get(Servo.class, "clawServo");//getting the clawServo servo
        sparkLong = ahwMap.get(DcMotor.class, "sparkLong");//getting the sparkLong motor
        touchSensor = ahwMap.get(TouchSensor.class, ("touchSensor"));
        tapeMeasure = ahwMap.get(DcMotor.class, "tapeMeasure");

        sparkLong.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the sparkLong encoder
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the motorFrontLeft encoder
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the motorBackRight encoder
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the motorBackLeft encoder
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the motorFrontRight encoder
        deadWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//resetting the deadWheel encoder

        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        tapeMeasure.setDirection(DcMotor.Direction.REVERSE);
        sparkLong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the sparkLong encoder to run using encoder
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the motorFrontLeft encoder to run using encoder
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the motorBackLeft encoder to run using encoder
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the motorBackRight encoder to run using encoder
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the motorFrontRight encoder to run using encoder
        deadWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//setting the deadWheel encoder to run using encoder

        motorBackRight.setZeroPowerBehavior(BRAKE);
        motorBackLeft.setZeroPowerBehavior(BRAKE);
        motorFrontRight.setZeroPowerBehavior(BRAKE);
        motorFrontLeft.setZeroPowerBehavior(BRAKE);
        sparkLong.setZeroPowerBehavior(BRAKE);
        red1.setMode(DigitalChannel.Mode.OUTPUT);//setting the red1 light to output
        green1.setMode(DigitalChannel.Mode.OUTPUT);//setting the green1 light to output
        red2.setMode(DigitalChannel.Mode.OUTPUT);//setting the red2 light to output
        green2.setMode(DigitalChannel.Mode.OUTPUT);//setting the green2 light to output
        red3.setMode(DigitalChannel.Mode.OUTPUT);//setting the red3 light to output
        green3.setMode(DigitalChannel.Mode.OUTPUT);//setting the green3 light to output
        red4.setMode(DigitalChannel.Mode.OUTPUT);//setting the red4 light to output
        green4.setMode(DigitalChannel.Mode.OUTPUT);//setting the green4 light to output

        //flipper.setPosition(setServo(magicFlip));//setting the flipper servo to the magicFlip position
        runtime.reset();//resetting the runtime variable
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        myOpMode.waitForStart();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.valueOf(getColor()));
        if (myOpMode.isStopRequested()) return;
    }

    public void doBulk(boolean fieldCentric) {
        switches();
        if (rumble) {
            rumble();
        }
        doClaw();
        drive(fieldCentric, slowPower);
        runArm();
        tapeMeasure();
        power();
        buildTelemetry();
    }

    public void power() {
        motorFrontLeft.setPower(frontLeftPower);
        motorBackLeft.setPower(backLeftPower);
        motorFrontRight.setPower(frontRightPower);
        motorBackRight.setPower(backRightPower);
        tapeMeasure.setPower(tapePower);
        sparkLong.setPower(armPower);
    }

    public void tapeMeasure() {
        tapePower = 0;
        if ((myOpMode.gamepad1.dpad_up || myOpMode.gamepad2.dpad_right)) {// && (tapeMeasure.getCurrentPosition() < tapeLimit - tapeLimit / 5)) {
            //extend
            tapePower = 1;
        }
        if ((myOpMode.gamepad1.dpad_down || myOpMode.gamepad2.dpad_left)) {// && (tapeMeasure.getCurrentPosition() > 0 + tapeLimit / 5))) {
            //retract
            tapePower = -1;
        }
    }

    public void runArm() {
        armPower = -myOpMode.gamepad2.left_stick_y;
        if (myOpMode.gamepad2.dpad_down) {
            sparkLong.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sparkLong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        presets();
    }

    public void presets() {
        if (myOpMode.gamepad2.left_stick_y != 0) {
            armPower = -myOpMode.gamepad2.left_stick_y;
        } else if (myOpMode.gamepad2.y) {//top level
            if (sparkLong.getCurrentPosition() < topPoleVal) {//go up
                armPower = 1;
            }
        } else if (myOpMode.gamepad2.a) {//base
            if (sparkLong.getCurrentPosition() > baseArm) {//go down
                armPower = -1;
            }
        } else if (myOpMode.gamepad2.b) {//middle
            if (sparkLong.getCurrentPosition() > midPoleVal + 50) {//go down
                armPower = -1;
            }
            if (sparkLong.getCurrentPosition() < midPoleVal - 50) {//go up
                armPower = 1;
            }
        } else if (myOpMode.gamepad2.x) {//low
            if (sparkLong.getCurrentPosition() > lowPoleVal + 50) {//go down
                armPower = -1;
            }
            if (sparkLong.getCurrentPosition() < lowPoleVal - 50) {//go up
                armPower = 1;
            }
        }
    }

    public void doClaw() {
        //claw code
        if (myOpMode.gamepad2.left_bumper) {
            clawServo.setPosition(setServo(magicNumOpen));
            clawOpen = true;
            //open claw
        } else if (myOpMode.gamepad2.right_bumper) {
            clawServo.setPosition(setServo(baseClawVal));
            //close claw
            clawOpen = false;
        }
        if (clawOpen) {
            green1.setState(false);
            red1.setState(true);
        } else {
            green1.setState(true);
            red1.setState(false);
        }
    }

    public void switches() {
        //switches
        if (myOpMode.gamepad1.left_trigger > 0) {
            slowModeIsOn = false;
        }
        if (myOpMode.gamepad1.right_trigger > 0) {
            slowModeIsOn = true;
        }
        if (slowModeIsOn) {
            slowPower = slowMult;
        } else {
            slowPower = 1;
        }
        //
    }

    public void drive(boolean fieldCentric, double slow) {
        if (fieldCentric) {
            gamepadX = myOpMode.gamepad1.left_stick_x;//get the x val of left stick and store
            myOpMode.telemetry.addData("gamepadX", gamepadX);//tell us what gamepadX is
            gamepadY = -myOpMode.gamepad1.left_stick_y;//get the y val of left stick and store
            myOpMode.telemetry.addData("gamepadY", gamepadY);//tell us what gamepadY is
            gamepadHypot = Range.clip(Math.hypot(gamepadX, gamepadY), 0, 1);//get the
            // hypotenuse of the x and y values,clip it to a max of 1 and store
            myOpMode.telemetry.addData("gamepadHypot", gamepadHypot);//tell us what gamepadHypot is
            controllerAngle = Math.toDegrees(Math.atan2(gamepadY, gamepadX));//Get the angle of the controller stick using arc tangent
            myOpMode.telemetry.addData("controllerAngle", controllerAngle);//tell us what controllerAngle is
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);//get and initialize the IMU
            robotDegree = angles.firstAngle;//store robot angle in robotDegree
            myOpMode.telemetry.addData("robotDegree", robotDegree);//tell us what robotDegree is
            movementDegree = (controllerAngle - robotDegree);//get the movement degree based on the controller vs robot angle
            myOpMode.telemetry.addData("movementDegree", movementDegree);//tell us what movementDegree is
            xControl = Math.cos(Math.toRadians(movementDegree)) * gamepadHypot;//get the x value of the movement
            myOpMode.telemetry.addData("xControl", xControl);//tell us what xControl is
            yControl = Math.sin(Math.toRadians(movementDegree)) * gamepadHypot;//get the y value of the movement
            myOpMode.telemetry.addData("yControl", yControl);//tell us what yControl is
            double turn = -myOpMode.gamepad1.right_stick_x;
            frontRightPower = (yControl * Math.abs(yControl) - xControl * Math.abs(xControl) + turn) / slowPower;
            backRightPower = (yControl * Math.abs(yControl) + xControl * Math.abs(xControl) + turn) / slowPower;
            frontLeftPower = (yControl * Math.abs(yControl) + xControl * Math.abs(xControl) - turn) / slowPower;
            backLeftPower = (yControl * Math.abs(yControl) - xControl * Math.abs(xControl) - turn) / slowPower;
        } else {
            yControl = -myOpMode.gamepad1.left_stick_y;
            xControl = myOpMode.gamepad1.left_stick_x;
            double turn = -myOpMode.gamepad1.right_stick_x;
            frontRightPower = (yControl - xControl + turn) / slow;
            backRightPower = (yControl + xControl + turn) / slow;
            frontLeftPower = (yControl + xControl - turn) / slow;
            backLeftPower = (yControl - xControl - turn) / slow;
        }
    }

    public void rumble() {
        if ((runtime.seconds() > endgame) && !isEndgame) {
            myOpMode.gamepad1.runRumbleEffect(customRumbleEffect);
            myOpMode.gamepad2.runRumbleEffect(customRumbleEffect);
            isEndgame = true;
        }
        if ((runtime.seconds() > end) && !isEnd) {
            myOpMode.gamepad1.runRumbleEffect(customRumbleEffect1);
            myOpMode.gamepad2.runRumbleEffect(customRumbleEffect1);
            isEnd = true;
        }
    }

    public void buildTelemetry() {
        myOpMode.telemetry.addData("Status", statusVal);//shows current status
        myOpMode.telemetry.addLine("Arm")
                .addData("Val", String.valueOf(sparkLong.getCurrentPosition()));
        myOpMode.telemetry.addData("reversed", reversed);
        myOpMode.telemetry.addData("slowMode", slowModeIsOn);
        myOpMode.telemetry.addData("dead", deadWheel.getCurrentPosition());
        teleSpace();
        updateStatus("Running");
        myOpMode.telemetry.update();
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

    public void setUniPower(double power) {
        motorFrontLeft.setPower(power);
        motorBackLeft.setPower(power);
        motorFrontRight.setPower(power);
        motorBackRight.setPower(power);
    }
    //public void unConeUp() {
    //    unConer.setPosition(setServo(magicUnCone));
    //    unConed = true;
    //}
    //public void unConeDown() {
    //    unConer.setPosition(setServo(baseUnCone));
    //    unConed = false;
    //}
    //public void ejectUp() {
    //    unConer.setPosition(setServo(magicEject));
    //}
    //public void ejectDown() {
    //    unConer.setPosition(setServo(baseEject));
    //}

    public void teleSpace() {
        myOpMode.telemetry.addLine();
    }

    public void updateStatus(String status) {
        statusVal = status;
    }//set a new controller/game status

    public int getAverage(double firstVal, double secondVal) {
        return (int) ((firstVal + secondVal) / 2);
    }

    public void openClaw() {
        clawServo.setPosition(setServo(magicNumOpen));
    }

    public void closeClaw() {
        clawServo.setPosition(setServo(baseClawVal));
    }

    public void dropArm(int prevHeight) {
        armEncoder(prevHeight - 100, 0.5, 2, true);
    }


    public void distanceTelemetry() {
        updateDistance();
        myOpMode.telemetry.addLine("Distance")
                .addData("", "")
                .addData("f", String.valueOf(fDistanceVal))
                .addData("l", String.valueOf(lDistanceVal))
                .addData("r", String.valueOf(rDistanceVal));
    }

    public void situate() {
        encoderDrive(1, 4, 4, 1);
    }

    public void encoderComboFwd(double speed, double lInches, double rInches,
                                double pose, double timeoutS, boolean isUp) {
        int newLeftTarget;
        int newRightTarget;
        int newDLeftTarget;
        int newDRightTarget;
        int target;
        target = (int) pose;
        sparkLong.setTargetPosition(target);
        sparkLong.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        newLeftTarget = motorBackLeft.getCurrentPosition() + (int) (lInches * COUNTS_PER_INCH);
        newRightTarget = motorBackRight.getCurrentPosition() + (int) (rInches * COUNTS_PER_INCH);
        //newDLeftTarget = deadWheelL.getCurrentPosition() + (int) (lInches * COUNTS_PER_INCH_dead);
        //newDRightTarget = deadWheelR.getCurrentPosition() + (int) (rInches * COUNTS_PER_INCH_dead);

        //deadWheelL.setTargetPosition(-newDLeftTarget);
        //deadWheelR.setTargetPosition(-newDRightTarget);
        motorFrontRight.setTargetPosition(-newRightTarget);
        motorBackRight.setTargetPosition(-newRightTarget);
        motorFrontLeft.setTargetPosition(-newLeftTarget);
        motorBackLeft.setTargetPosition(-newLeftTarget);

        motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //deadWheelL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //deadWheelR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        if (isUp) {
            sparkLong.setPower(speed);//go down
        }
        if (!isUp) {
            sparkLong.setPower(-speed);
        }
        motorBackLeft.setPower((speed));
        motorBackRight.setPower((speed));
        motorFrontRight.setPower((speed));
        motorFrontLeft.setPower((speed));
        while (myOpMode.opModeIsActive() &&
                (runtime.seconds() < timeoutS) && sparkLong.isBusy()) {

            // Display it for the driver.
            myOpMode.telemetry.addData(" arm Running to", sparkLong.getCurrentPosition());
            myOpMode.telemetry.addData("arm Currently at",
                    sparkLong.getCurrentPosition());
            // Display it for the driver.
            myOpMode.telemetry.update();
        }

        // Stop all motion;
        motorBackLeft.setPower(0);
        motorFrontRight.setPower(0);
        motorBackRight.setPower(0);
        motorFrontLeft.setPower(0);

        sparkLong.setPower(0);
        sparkLong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Turn off RUN_TO_POSITION
        motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //deadWheelR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //deadWheelL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        resetEncoders();
        myOpMode.telemetry.update();
    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newDLeftTarget;
        int newDRightTarget;
        if (myOpMode.opModeIsActive()) {

            newLeftTarget = motorBackLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = motorBackRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            //newDLeftTarget = deadWheelL.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH_dead);
            //newDRightTarget = deadWheelR.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH_dead);

            //deadWheelL.setTargetPosition(-newDLeftTarget);
            //deadWheelR.setTargetPosition(-newDRightTarget);
            motorFrontRight.setTargetPosition(-newRightTarget);
            motorBackRight.setTargetPosition(-newRightTarget);
            motorFrontLeft.setTargetPosition(-newLeftTarget);
            motorBackLeft.setTargetPosition(-newLeftTarget);

            motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //deadWheelL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //deadWheelR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            motorBackLeft.setPower((speed));
            motorFrontRight.setPower((speed));
            motorFrontLeft.setPower((speed));
            motorBackRight.setPower((speed));
            while (myOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (motorBackLeft.isBusy())) {

                // Display it for the driver.
                //telemetry.addData("Running to", "%7d :%7d", -newDLeftTarget, -newDRightTarget);//"%7d :%7d"
                //telemetry.addData("Currently at", "%7d :%7d",
                //        deadWheelL.getCurrentPosition(), deadWheelR.getCurrentPosition());
                myOpMode.telemetry.addData("fr", motorFrontRight.getCurrentPosition());
                myOpMode.telemetry.update();
            }

            // Stop all motion;
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorBackRight.setPower(0);
            motorFrontLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //deadWheelR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //deadWheelL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            resetEncoders();
        }
    }

    public void sideWaysEncoderDrive(double speed,
                                     double inches,
                                     double timeoutS) {//+=right //-=left
        int newFRTarget;
        int newFLTarget;
        int newBRTarget;
        int newBLTarget;
        int newDeadTarget;
        inches *= -1;
        if (myOpMode.opModeIsActive()) {
            if (inches < 0) {
                newFLTarget = motorFrontLeft.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newBLTarget = motorBackLeft.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newFRTarget = motorFrontRight.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newBRTarget = motorBackRight.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newDeadTarget = deadWheel.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side_dead);
                motorFrontLeft.setTargetPosition(-newFLTarget);
                motorBackLeft.setTargetPosition(newBLTarget);
                motorBackRight.setTargetPosition(-newBRTarget - 10);
                motorFrontRight.setTargetPosition(newFRTarget);
                deadWheel.setTargetPosition(-newDeadTarget);
            }
            if (inches > 0) {
                newFLTarget = motorFrontLeft.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newBLTarget = motorBackLeft.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newFRTarget = motorFrontRight.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newBRTarget = motorBackRight.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side);
                newDeadTarget = deadWheel.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH_Side_dead);
                motorFrontLeft.setTargetPosition(-newFLTarget);
                motorBackLeft.setTargetPosition(newBLTarget);
                motorBackRight.setTargetPosition(-newBRTarget);
                motorFrontRight.setTargetPosition(newFRTarget);
                deadWheel.setTargetPosition(newDeadTarget);
            } else {
                motorFrontLeft.setTargetPosition(0);
                motorBackLeft.setTargetPosition(0);
                motorBackRight.setTargetPosition(0);
                motorFrontRight.setTargetPosition(0);
                deadWheel.setTargetPosition(0);
            }

            motorFrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            deadWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            motorBackLeft.setPower(Math.abs(speed));
            motorFrontRight.setPower(Math.abs(speed));
            motorFrontLeft.setPower(Math.abs(speed));
            motorBackRight.setPower(Math.abs(speed));
            while (myOpMode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) && deadWheel.isBusy()) {

                // Display it for the driver.
                myOpMode.telemetry.addData("Running to", "%7d:%7d", motorFrontLeft.getCurrentPosition()
                        , motorBackRight.getCurrentPosition());
                myOpMode.telemetry.addData("Running to", "%7d:%7d", motorBackLeft.getCurrentPosition()
                        , motorFrontRight.getCurrentPosition());
                myOpMode.telemetry.addData("Currently at", "%7d:%7d",
                        motorFrontLeft.getCurrentPosition()
                        , motorBackRight.getCurrentPosition());
                myOpMode.telemetry.addData("Currently at", "%7d:%7d",
                        motorFrontRight.getCurrentPosition()
                        , motorBackLeft.getCurrentPosition());
                myOpMode.telemetry.update();
            }

            // Stop all motion;
            motorBackLeft.setPower(0);
            motorFrontRight.setPower(0);
            motorFrontLeft.setPower(0);
            motorBackRight.setPower(0);

            // Turn off RUN_TO_POSITION
            motorFrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorBackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            deadWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            resetEncoders();
        }
    }

    public void armEncoder(double pose, double speed, double timeOut, boolean isUp) {
        int target;
        target = (int) pose;
        sparkLong.setTargetPosition(target);
        sparkLong.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        runtime.reset();
        if (isUp) {
            sparkLong.setPower(speed);//go down
        }
        if (!isUp) {
            sparkLong.setPower(-speed);
        }
        while (myOpMode.opModeIsActive() &&
                (runtime.seconds() < timeOut) && sparkLong.isBusy()) {

            // Display it for the driver.
            myOpMode.telemetry.addData("Running to", sparkLong.getCurrentPosition());
            myOpMode.telemetry.addData("Currently at",
                    sparkLong.getCurrentPosition());
            myOpMode.telemetry.update();
        }
        sparkLong.setPower(0);
        sparkLong.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        myOpMode.telemetry.update();
    }

    public void resetEncoders() {
        motorFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        deadWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //deadWheelL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //deadWheelR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //sparkLong.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public double setServo(int degrees) {
        position = degree_mult * degrees;
        return position;
    }

    public void updateDistance() {
        fDistanceVal = (fDistance.getDistance(DistanceUnit.CM) + fOffset);
        lDistanceVal = (lDistance.getDistance(DistanceUnit.CM) + lOffset);
        rDistanceVal = (rDistance.getDistance(DistanceUnit.CM) + rOffset);
    }

    public void adjustWDist(String sensor, double dist, double power, boolean closerThanDist) {//less than goes opposite way
        updateDistance();
        if (Objects.equals(sensor, "f")) {
            if (!closerThanDist) {
                while (fDistanceVal > dist) {
                    updateDistance();
                    fDistanceVal = (fDistance.getDistance(DistanceUnit.CM) + fOffset);
                    myOpMode.telemetry.addData("Distance", fDistanceVal);
                    myOpMode.telemetry.update();
                    motorFrontLeft.setPower(-power);
                    motorFrontRight.setPower(-power);
                    motorBackLeft.setPower(-power);
                    motorBackRight.setPower(-power);
                    if (fDistanceVal <= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
            if (closerThanDist) {
                power = -power;
                while (fDistanceVal < dist) {
                    updateDistance();
                    fDistanceVal = (fDistance.getDistance(DistanceUnit.CM) + fOffset);
                    myOpMode.telemetry.addData("Distance", fDistanceVal);
                    myOpMode.telemetry.update();
                    motorFrontLeft.setPower(-power);
                    motorFrontRight.setPower(-power);
                    motorBackLeft.setPower(-power);
                    motorBackRight.setPower(-power);
                    if (fDistanceVal >= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
        }
        if (Objects.equals(sensor, "r")) {//shift right
            if (!closerThanDist) {
                while (rDistanceVal > dist) {
                    updateDistance();
                    myOpMode.telemetry.addData("Distance", rDistanceVal);
                    myOpMode.telemetry.update();
                    rDistanceVal = (rDistance.getDistance(DistanceUnit.CM) + rOffset);
                    motorFrontLeft.setPower(-power);
                    motorFrontRight.setPower(power);
                    motorBackLeft.setPower(power);
                    motorBackRight.setPower(-power);
                    if (rDistanceVal <= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
            if (closerThanDist) {
                while (rDistanceVal < dist) {
                    updateDistance();
                    myOpMode.telemetry.addData("Distance", rDistanceVal);
                    myOpMode.telemetry.update();
                    rDistanceVal = (rDistance.getDistance(DistanceUnit.CM) + rOffset);
                    motorFrontLeft.setPower(power);
                    motorFrontRight.setPower(-power);
                    motorBackLeft.setPower(-power);
                    motorBackRight.setPower(power);
                    if (rDistanceVal >= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
        }
        if (Objects.equals(sensor, "l")) {//shift left
            if (!closerThanDist) {
                while (lDistanceVal > dist) {
                    updateDistance();
                    myOpMode.telemetry.addData("Distance", lDistanceVal);
                    myOpMode.telemetry.update();
                    lDistanceVal = (lDistance.getDistance(DistanceUnit.CM) + lOffset);
                    motorFrontLeft.setPower(power);
                    motorFrontRight.setPower(-power);
                    motorBackLeft.setPower(-power);
                    motorBackRight.setPower(power);
                    if (lDistanceVal <= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
            if (closerThanDist) {
                while (lDistanceVal < dist) {
                    updateDistance();
                    myOpMode.telemetry.addData("Distance", lDistanceVal);
                    myOpMode.telemetry.update();
                    lDistanceVal = (lDistance.getDistance(DistanceUnit.CM) + lOffset);
                    motorFrontLeft.setPower(-power);
                    motorFrontRight.setPower(power);
                    motorBackLeft.setPower(power);
                    motorBackRight.setPower(-power);
                    if (lDistanceVal >= dist) {
                        motorFrontLeft.setPower(0);
                        motorFrontRight.setPower(0);
                        motorBackLeft.setPower(0);
                        motorBackRight.setPower(0);
                    }
                }
            }
        }
    }

    public void setOvr(double x, double y) {
        ovrCurrX = x;
        ovrCurrY = y;
    }

    public void advGoSpot(double currX, double currY, double targetX, double targetY, double power, boolean combo, int pose
            , boolean isUp, String orientation, double orientationVal, boolean endTurn, int turn,
                          boolean checkDistanceX, boolean checkDistanceY, String xSensor, double xDist, boolean lessThanX,
                          String ySensor, double yDist, boolean lessThanY) {
        if (ovrTurn % 180 == 0) {
            double mult = ovrTurn / 180;
            orientationVal *= (Math.pow(-1, mult));
        }
        if (Objects.equals(orientation, "|")) {
            double sidewaysInches = (targetX - currX) * xMult;
            double fwdInches = (targetY - currY) * yMult;
            fwdInches *= orientationVal;
            sidewaysInches *= orientationVal;
            myOpMode.telemetry.addData("fwdInches", fwdInches);
            myOpMode.telemetry.addData("sidewaysInches", sidewaysInches);
            if (currX < targetX) {
                sideWaysEncoderDrive(power, sidewaysInches, 1);
            } else if (currX > targetX) {
                sideWaysEncoderDrive(power, -sidewaysInches, 1);
            }
            if (currY < targetY) {
                if (!combo) {
                    encoderDrive(power, fwdInches, fwdInches, 6);
                } else {
                    encoderComboFwd(power, fwdInches, fwdInches, pose, 6, isUp);
                }
            } else if (currY > targetY) {
                if (!combo) {
                    encoderDrive(power, -fwdInches, -fwdInches, 6);
                } else {
                    encoderComboFwd(power, -fwdInches, -fwdInches, pose, 6, isUp);
                }
            }
        }
        if (Objects.equals(orientation, "-")) {
            double sidewaysInches = (targetY - currY) * xMult;
            double fwdInches = (targetX - currX) * yMult;
            if (orientationVal == -90 || orientationVal == 90) {
                orientationVal /= 90;
            }
            fwdInches *= orientationVal;
            sidewaysInches *= orientationVal;
            myOpMode.telemetry.addData("fwdInches", fwdInches);
            myOpMode.telemetry.addData("sidewaysInches", sidewaysInches);
            if (currY < targetY) {
                sideWaysEncoderDrive(power, sidewaysInches, 6);
            } else if (currY > targetY) {
                sideWaysEncoderDrive(power, -sidewaysInches, 6);
            }
            if (currX < targetX) {
                if (!combo) {
                    encoderDrive(power, fwdInches, fwdInches, 6);
                } else {
                    encoderComboFwd(power, fwdInches, fwdInches, pose, 6, isUp);
                }
            } else if (currX > targetX) {
                if (!combo) {
                    encoderDrive(power, -fwdInches, -fwdInches, 6);
                } else {
                    encoderComboFwd(power, -fwdInches, -fwdInches, pose, 6, isUp);
                }
            }
        }
        if (checkDistanceX) {
            adjustWDist(xSensor, xDist, power, lessThanX);
        }
        if (checkDistanceY) {
            adjustWDist(ySensor, yDist, power, lessThanY);
        }
        if (endTurn) {
            turn(turn);
        }
        setOvr(targetX, targetY);
        ovrTurn += turn;
        //telemetry.addData("orientation", orientation);
        myOpMode.telemetry.update();
        //sleep(5000);
    }

    public void turn(int degrees) {
        resetEncoders();
        if (degrees > 180) {
            degrees = (360 - degrees) * -1;
        }
        if (degrees <= 0) {
            degrees += 1;
        }
        int mult = 360 / (degrees + 1);
        int inches = (turn / mult);
        encoderDrive(0.65, -inches, inches, 3);
        resetEncoders();
    }

    public void runVu(int timeoutS, boolean giveSpot) {
        runtime.reset();
        while (myOpMode.opModeIsActive() && (spot == 0)) {
            if (runtime.seconds() > timeoutS) {
                spot = 4;
            }
            if (tfod != null) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    myOpMode.telemetry.addData("# Objects Detected", updatedRecognitions.size());

                    // step through the list of recognitions and display image position/size information for each one
                    // Note: "Image number" refers to the randomized image orientation/number
                    for (Recognition recognition : updatedRecognitions) {
                        double col = (recognition.getLeft() + recognition.getRight()) / 2;
                        double row = (recognition.getTop() + recognition.getBottom()) / 2;
                        double width = Math.abs(recognition.getRight() - recognition.getLeft());
                        double height = Math.abs(recognition.getTop() - recognition.getBottom());

                        myOpMode.telemetry.addData("", " ");
                        myOpMode.telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                        myOpMode.telemetry.addData("- Position (Row/Col)", "%.0f / %.0f", row, col);
                        myOpMode.telemetry.addData("- Size (Width/Height)", "%.0f / %.0f", width, height);
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
                    myOpMode.telemetry.update();
                }
            }
        }
    }

    public void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.75f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 300;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);

        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }

    public boolean colorInRange(float red, double targetR, float green, double targetG, float blue, double targetB, float range) {
        boolean rCheck = false;
        boolean gCheck = false;
        boolean bCheck = false;
        if (targetR - range < red && red < targetR + range) {
            rCheck = true;
        }
        if (targetG - range < green && green < targetG + range) {
            gCheck = true;
        }
        if (targetB - range < blue && blue < targetB + range) {
            bCheck = true;
        }
        return rCheck && gCheck && bCheck;
    }

    public void getAllColorR() {
        //gives color values
        NormalizedRGBA colorsR = colorSensorR.getNormalizedColors();
        Color.colorToHSV(colorsR.toColor(), hsvValues);
        myOpMode.telemetry.addLine()
                .addData("Red", "%.3f", colorsR.red)
                .addData("Green", "%.3f", colorsR.green)
                .addData("Blue", "%.3f", colorsR.blue)
                .addData("Hue", "%.3f", hsvValues[0])
                .addData("Saturation", "%.3f", hsvValues[1])
                .addData("Value", "%.3f", hsvValues[2])
                .addData("Alpha", "%.3f", colorsR.alpha);
        myOpMode.telemetry.addLine()
                .addData("Color", colorName)
                .addData("RGB", "(" + redValR + "," + greenValR + "," + blueValR + ")");//shows rgb value
    }

    public void getAllColorL() {
        //gives color values
        NormalizedRGBA colors = colorSensorL.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        myOpMode.telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue)
                .addData("Hue", "%.3f", hsvValues[0])
                .addData("Saturation", "%.3f", hsvValues[1])
                .addData("Value", "%.3f", hsvValues[2])
                .addData("Alpha", "%.3f", colors.alpha);
        myOpMode.telemetry.addLine()
                .addData("Color", colorName)
                .addData("RGB", "(" + redValL + "," + greenValL + "," + blueValL + ")");//shows rgb value
    }

    public void assist() {
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        getAllColorR();
        getAllColorL();
        NormalizedRGBA colorsR = colorSensorR.getNormalizedColors();
        Color.colorToHSV(colorsR.toColor(), hsvValues);
        NormalizedRGBA colors = colorSensorL.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        float redValR = colorsR.red;//the red value in rgb
        float greenValR = colorsR.green;//the green value in rgb
        float blueValR = colorsR.blue;//the blue value in rgb
        float redValL = colors.red;//the red value in rgb
        float greenValL = colors.green;//the green value in rgb
        float blueValL = colors.blue;//the blue value in rgb
        //right
        double redTargetRR = 0.003;//the red value in rgb
        double redTargetGR = 0.004;//the green value in rgb
        double redTargetBR = 0.003;//the blue value in rgb
        //left
        double redTargetRL = 0.003;//the red value in rgb
        double redTargetGL = 0.003;//the green value in rgb
        double redTargetBL = 0.002;//the blue value in rgb
        //right
        double blueTargetRR = 0.002;//the red value in rgb
        double blueTargetGR = 0.004;//the green value in rgb
        double blueTargetBR = 0.005;//the blue value in rgb
        //left
        double blueTargetRL = 0.001;//the red value in rgb
        double blueTargetGL = 0.003;//the green value in rgb
        double blueTargetBL = 0.0038;//the blue value in rgb
        double range = 0.0005;
        double speed = -0.2;
        //left
        while (colorInRange(redValL, redTargetRL, greenValL, redTargetGL, blueValL, redTargetBL, (float) range)
                || colorInRange(redValL, blueTargetRL, greenValL, blueTargetGL, blueValL, blueTargetBL, (float) range)
                || colorInRange(redValR, redTargetRR, greenValR, redTargetGR, blueValR, redTargetBR, (float) range)
                || colorInRange(redValR, blueTargetRR, greenValR, blueTargetGR, blueValR, blueTargetBR, (float) range)) {
            if ((colorInRange(redValR, redTargetRR, greenValR, redTargetGR, blueValR, redTargetBR, (float) range)
                    || colorInRange(redValR, blueTargetRR, greenValR, blueTargetGR, blueValR, blueTargetBR, (float) range))) {
                getAllColorR();
                motorFrontLeft.setPower(-speed);
                motorFrontRight.setPower(speed);
                motorBackLeft.setPower(speed);
                motorBackRight.setPower(-speed);
                //right side has seen red or blue
            }
            if (colorInRange(redValL, redTargetRL, greenValL, redTargetGL, blueValL, redTargetBL, (float) range)
                    || colorInRange(redValL, blueTargetRL, greenValL, blueTargetGL, blueValL, blueTargetBL, (float) range)) {
                getAllColorL();
                motorFrontLeft.setPower(speed);
                motorFrontRight.setPower(-speed);
                motorBackLeft.setPower(-speed);
                motorBackRight.setPower(speed);
            }
            if (!colorInRange(redValL, redTargetRL, greenValL, redTargetGL, blueValL, redTargetBL, (float) range)
                    || !colorInRange(redValL, blueTargetRL, greenValL, blueTargetGL, blueValL, blueTargetBL, (float) range)
                    || !colorInRange(redValR, redTargetRR, greenValR, redTargetGR, blueValR, redTargetBR, (float) range)
                    || !colorInRange(redValR, blueTargetRR, greenValR, blueTargetGR, blueValR, blueTargetBR, (float) range)) {
                break;
            }
        }
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.valueOf(getColor()));
        boolean pressed = touchSensor.isPressed();
        while (!pressed) {
            pressed = touchSensor.isPressed();
            if (pressed) {
                break;
            }
            double speed1 = 0.5;
            motorBackLeft.setPower(speed1);
            motorBackRight.setPower(speed1);
            motorFrontLeft.setPower(speed1);
            motorFrontRight.setPower(speed1);
        }
        assisting = false;
    }
}
