package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineFollow extends Command {

	public LineFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.LINE_FOLLOWER_SUBSYSTEM);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.LINE_FOLLOWER_SUBSYSTEM.getPIDController().isEnabled()) {
			Robot.LINE_FOLLOWER_SUBSYSTEM.disable();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (int i = 0; i < 3; i++)
			SmartDashboard.putNumber("Light Sensor Value " + i, Robot.LINE_FOLLOWER_SUBSYSTEM.getSensorValue(i));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.LINE_FOLLOWER_SUBSYSTEM.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.LINE_FOLLOWER_SUBSYSTEM.disable();
	}
}
