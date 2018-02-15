package org.usfirst.frc.team2473.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
	private static final double KP = 0;
	private static final double KI = 0;
	private static final double KD = 0;

	private double pidValue;

	private DifferentialDrive differentialDrive;

	private WPI_TalonSRX talonFrontLeft;
	private WPI_TalonSRX talonBackLeft;
	private SpeedControllerGroup leftTalons;

	private WPI_TalonSRX talonFrontRight;
	private WPI_TalonSRX talonBackRight;
	private SpeedControllerGroup rightTalons;

	private DigitalInput leftDarkSensor, middleDarkSensor, rightDarkSensor;
	private AnalogInput leftLightSensor, middleLightSensor, rightLightSensor;

	private ArrayList<DigitalInput> digitalSensorList = new ArrayList<>();
	private ArrayList<AnalogInput> analogSensorList = new ArrayList<>();

	// Initialize your subsystem here
	public DriveTrain() {
		super(KP, KI, KD);

		pidValue = 0;

		setInputRange(0, 1.0);
		setOutputRange(-1.0, 1.0);
		this.getPIDController().setContinuous(true);

		talonFrontLeft = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_LEFT);
		talonBackLeft = new WPI_TalonSRX(RobotMap.MOTOR_BACK_LEFT);
		leftTalons = new SpeedControllerGroup(talonFrontLeft, talonBackLeft);

		talonFrontRight = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_RIGHT);
		talonBackRight = new WPI_TalonSRX(RobotMap.MOTOR_BACK_RIGHT);
		rightTalons = new SpeedControllerGroup(talonFrontRight, talonBackRight);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);

		leftDarkSensor = new DigitalInput(RobotMap.LEFT_DIGITAL_SENSOR); // dark
		middleDarkSensor = new DigitalInput(RobotMap.MIDDLE_DIGITAL_SENSOR); // dark
		rightDarkSensor = new DigitalInput(RobotMap.RIGHT_DIGITAL_SENSOR); // dark

		leftLightSensor = new AnalogInput(RobotMap.LEFT_ANALOG_SENSOR); // light
		middleLightSensor = new AnalogInput(RobotMap.MIDDLE_ANALOG_SENSOR); // light
		rightLightSensor = new AnalogInput(RobotMap.RIGHT_ANALOG_SENSOR); /// light

		digitalSensorList.addAll(Arrays.asList(leftDarkSensor, middleDarkSensor, rightDarkSensor));
		analogSensorList.addAll(Arrays.asList(leftLightSensor, middleLightSensor, rightLightSensor));
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean getDigitalSensorValue(int i) {
		return digitalSensorList.get(i).get();
	}

	public double getAnalogSensorValue(int i) {
		return analogSensorList.get(i).getValue();
	}

	public void drive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public void stop() {
		differentialDrive.arcadeDrive(0.1, 0);
		differentialDrive.arcadeDrive(0, 0);
	}

	@Override
	protected double returnPIDInput() {
		return analogSensorList.get(RobotMap.MIDDLE_ANALOG_SENSOR).pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		pidValue = output;
	}

	public double getPIDValue() {
		return pidValue;
	}

	public void setTargetValue(double value) {
		setSetpoint(value);
		this.disable();
		this.enable();
	}
}
