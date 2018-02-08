package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackablePIDSubsystem;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
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
	private static final double KF = 0;

	private WPI_TalonSRX talon1;
	private WPI_TalonSRX talon2;
	private SpeedControllerGroup left;
	
	private WPI_TalonSRX talon3;
	private WPI_TalonSRX talon4;
	private SpeedControllerGroup right;
	
	private AnalogInput leftLightSensor, middleLightSensor, rightLightSensor;
	
	private double pidValue;

	// Initialize your subsystem here
	public LineFollowerSubsystem() {
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.

		super(KP, KI, KD, KF);
		talon1 = Devices.getInstance().getTalon(1);
		talon2 = Devices.getInstance().getTalon(2);
		left = new SpeedControllerGroup(talon1, talon2);
		
		talon3 = Devices.getInstance().getTalon(3);
		talon4 = Devices.getInstance().getTalon(4);
		right = new SpeedControllerGroup(talon3, talon4);
		differentialDrive = new DifferentialDrive(left, right);
		leftLightSensor = Devices.getInstance().getAnalogInput(0);
		middleLightSensor = Devices.getInstance().getAnalogInput(1);
		rightLightSensor = Devices.getInstance().getAnalogInput(2);
		
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
		return middleLightSensor.getValue();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		pidValue = output;
	}

	public double getSensorValue(int i) {
		return Devices.getInstance().getAnalogInput(i).getValue();
	}

	public void drive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}
}
