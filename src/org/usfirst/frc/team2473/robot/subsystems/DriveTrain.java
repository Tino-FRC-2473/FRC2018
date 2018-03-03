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
	private static final double KP = 0.0011;
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

	private DigitalInput leftDigitalSensor, middleDigitalSensor, rightDigitalSensor;
	private AnalogInput leftAnalogSensor, middleAnalogSensor, rightAnalogSensor;

	private ArrayList<DigitalInput> digitalSensorList = new ArrayList<>();
	private ArrayList<AnalogInput> analogSensorList = new ArrayList<>();

	// Initialize your subsystem here
	public DriveTrain() {
		super(KP, KI, KD);

		pidValue = 0;

		setInputRange(0, 1000);
		setOutputRange(-1.0, 1.0);
		this.getPIDController().setContinuous(true);

		talonFrontLeft = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_LEFT);
		talonBackLeft = new WPI_TalonSRX(RobotMap.MOTOR_BACK_LEFT);
		leftTalons = new SpeedControllerGroup(talonFrontLeft, talonBackLeft);

		talonFrontRight = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_RIGHT);
		talonBackRight = new WPI_TalonSRX(RobotMap.MOTOR_BACK_RIGHT);
		rightTalons = new SpeedControllerGroup(talonFrontRight, talonBackRight);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);

		leftDigitalSensor = new DigitalInput(RobotMap.LEFT_DIGITAL_SENSOR); // dark
		middleDigitalSensor = new DigitalInput(RobotMap.MIDDLE_DIGITAL_SENSOR); // dark
		rightDigitalSensor = new DigitalInput(RobotMap.RIGHT_DIGITAL_SENSOR); // dark

		leftAnalogSensor = new AnalogInput(RobotMap.LEFT_ANALOG_SENSOR); // light
		middleAnalogSensor = new AnalogInput(RobotMap.MIDDLE_ANALOG_SENSOR); // light
		rightAnalogSensor = new AnalogInput(RobotMap.RIGHT_ANALOG_SENSOR); /// light

		digitalSensorList.addAll(Arrays.asList(middleDigitalSensor, leftDigitalSensor, rightDigitalSensor));
		analogSensorList.addAll(Arrays.asList(leftAnalogSensor, rightAnalogSensor, middleAnalogSensor));
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public AnalogInput getAnalogSensor(int port) {
		if (port == RobotMap.LEFT_ANALOG_SENSOR) {
			return leftAnalogSensor;
		} else if (port == RobotMap.RIGHT_ANALOG_SENSOR) {
			return rightAnalogSensor;
		} else {
			return middleAnalogSensor;
		}
	}
	
	public DigitalInput getDigitalSensor(int port) {
		if (port == RobotMap.LEFT_DIGITAL_SENSOR) {
			return leftDigitalSensor;
		} else if (port == RobotMap.RIGHT_DIGITAL_SENSOR) {
			return rightDigitalSensor;
		} else {
			return middleDigitalSensor;
		}
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
		return middleAnalogSensor.getValue();
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
	
	public int getEncoderClick(int talon) {
		if (talon == RobotMap.MOTOR_FRONT_LEFT) {
			return talonFrontLeft.getSelectedSensorPosition(0);
		} else if (talon == RobotMap.MOTOR_FRONT_RIGHT) {
			return talonFrontRight.getSelectedSensorPosition(0);
		} else if (talon == RobotMap.MOTOR_BACK_LEFT) {
			return talonBackLeft.getSelectedSensorPosition(0);
		} else if (talon == RobotMap.MOTOR_BACK_RIGHT) {
			return talonBackRight.getSelectedSensorPosition(0);
		} else {
			return -1;
		}
	}
}
