package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackablePIDSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class LineFollowerSubsystem extends TrackablePIDSubsystem {
	
	private static final double KP = 0;
	private static final double KI = 0;
	private static final double KD = 0;
	private static final double KF = 0;
	
	private WPI_TalonSRX talon1;
	
	private AnalogInput lightSensor;
	
    // Initialize your subsystem here
    public LineFollowerSubsystem() {
    	super(KP, KI, KD, KF);
    	talon1 = Devices.getInstance().getTalon(1);
    	lightSensor = new AnalogInput(0);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return lightSensor.getValue();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }

	@Override
	public void stop() {
		talon1.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double getSensorValue() {
		return lightSensor.getValue();
	}
}
