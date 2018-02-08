package org.usfirst.frc.team2473.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackablePIDSubsystem;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class LineFollowerSubsystem extends PIDSubsystem {

	private DifferentialDrive differentialDrive;

	private static final double KP = 0;
	private static final double KI = 0;
	private static final double KD = 0;

	private WPI_TalonSRX talon1;
	private WPI_TalonSRX talon2;
	private SpeedControllerGroup left;
	
	private WPI_TalonSRX talon3;
	private WPI_TalonSRX talon4;
	private SpeedControllerGroup right;
	
	private DigitalInput leftLightSensor, middleLightSensor, rightLightSensor, leftLightSensor2, middleLightSensor2, rightLightSensor2;
	ArrayList<DigitalInput> LightSensorList = new ArrayList();
	
	private double pidValue;

	// Initialize your subsystem here
	public LineFollowerSubsystem() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.

		super(KP, KI, KD);
		talon1 = new WPI_TalonSRX(1);
		talon2 = new WPI_TalonSRX(2);
		left = new SpeedControllerGroup(talon1, talon2);
		
		talon3 = new WPI_TalonSRX(3);
		talon4 = new WPI_TalonSRX(4);
		right = new SpeedControllerGroup(talon3, talon4);
		differentialDrive = new DifferentialDrive(left, right);
		leftLightSensor = new DigitalInput(0);
		middleLightSensor = new DigitalInput(1);
		rightLightSensor = new DigitalInput(2);
		leftLightSensor2 = new DigitalInput(3);
		middleLightSensor2 = new DigitalInput(4);
		rightLightSensor2 = new DigitalInput(5);
		LightSensorList.addAll(Arrays.asList(leftLightSensor, middleLightSensor, rightLightSensor, leftLightSensor2, middleLightSensor2, rightLightSensor2));
		
		pidValue = 0;
		
		setSetpoint(0);
		setOutputRange (-1.0, 1.0);
		getPIDController().setContinuous(true);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public double getPidValue() {
		return pidValue;
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		if(middleLightSensor.get()) return 1;
		return 0;
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		pidValue = output;
	}

	public double getSensorValue(int i) {
		if(LightSensorList.get(i).get()) return 1;
		return 0;
	}

	public void drive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}
}
