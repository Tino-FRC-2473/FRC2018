package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HookUp extends Command {
	private ClimbSystem climb;

	public HookUp() {
<<<<<<< HEAD
		sub = Robot.getClimbSystem();
		requires(sub);
=======
		climb = Robot.getClimb();
		requires(climb);
>>>>>>> e6239dc91ca179a0cca1e0478e1a9a979dfb4f23
	}

	protected void initialize() {
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR)
				.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).setSelectedSensorPosition(0, 0, 10);
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(ControlMode.PercentOutput, climb.FASTER_UP);
		
	}

	protected void execute() {
		double pow = (Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).getSelectedSensorPosition(0) <= climb.THRESHOLD
				? climb.getFasterSpeed(true)
				: climb.getSlowerSpeed(true));
		Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).set(pow);
	}

	protected boolean isFinished() {
		return Math.abs(Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR)
				.getSelectedSensorPosition(0)) >= climb.ENCCOUNT2;
	}

	protected void end() {
		climb.stopArmMotor();
		climb.notYet();
	}

	protected void interrupted() {
		climb.stopArmMotor();

	}
}
