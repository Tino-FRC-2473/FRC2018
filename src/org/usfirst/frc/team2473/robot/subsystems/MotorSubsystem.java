package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 *
 */
public class MotorSubsystem extends TrackableSubsystem {
	private WPI_TalonSRX talon1;
	
	public MotorSubsystem() {
		talon1 = Devices.getInstance().getTalon(2);
	}

	public void initDefaultCommand() {
	}
	
	public void run(double p) {
		talon1.set(ControlMode.PercentOutput, p);
	}

	@Override
	public void stop() {
		talon1.set(ControlMode.PercentOutput, 0);
	}

	@Override
	public String getState() {
		return "Current: " + talon1.getOutputCurrent();
	}
}
