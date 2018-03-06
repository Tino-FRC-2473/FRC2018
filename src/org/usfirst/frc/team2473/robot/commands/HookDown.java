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
	private ClimbSystem climb;

	public HookDown() {
<<<<<<< HEAD
		sub = Robot.getClimbSystem();
		requires(sub);
=======
		climb = Robot.getClimb();
		requires(climb);
>>>>>>> e6239dc91ca179a0cca1e0478e1a9a979dfb4f23
	}

	protected void initialize() {
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput, climb.ARMPOWDOWN);
	}

	protected void execute() {
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput, climb.getSlowerSpeed(false));
	}

	protected boolean isFinished() {
		return Math.abs(Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR)
				.getSelectedSensorPosition(0)) <= climb.ENCCOUNT3;
	}

	protected void end() {
		climb.stopArmMotor();
		climb.updateToHung();
	}

	protected void interrupted() {
		climb.stopArmMotor();
	}
}
