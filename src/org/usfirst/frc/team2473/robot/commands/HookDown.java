package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HookDown extends Command {
	private ClimbSystem sub;

	public HookDown() {
		sub = (ClimbSystem) Robot.getSubsystem(ClimbSystem.class);
		requires(sub);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput, sub.ARMPOWDOWN);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// if (Robot.getControls().armDownButton.get()) {
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput, sub.getSlowerSpeed(false));
		// } else {
		// Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput,
		// 0);
		// }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(
				Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).getSelectedSensorPosition(0)) <= sub.ENCCOUNT3;
	}

	// Called once after isFinished returns true
	protected void end() {
		sub.stopArmMotor();
		sub.updateToHung();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		sub.stopArmMotor();
	}
}
