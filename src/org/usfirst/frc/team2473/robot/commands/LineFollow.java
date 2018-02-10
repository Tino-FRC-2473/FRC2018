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

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (int i = 0; i < 6; i++) {
			SmartDashboard.putBoolean("Light Sensor Value " + i, Robot.LINE_FOLLOWER_SUBSYSTEM.getSensorValue(i));
		}

		if (Robot.LINE_FOLLOWER_SUBSYSTEM.getSensorValue(3)) {
			Robot.LINE_FOLLOWER_SUBSYSTEM.drive(-0.4, 0.2);
		} else {
			Robot.LINE_FOLLOWER_SUBSYSTEM.drive(-0.4, -0.2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		// for (int i = 1; i <= 5; i += 2) {
		// if (Robot.LINE_FOLLOWER_SUBSYSTEM.getSensorValue(i)) {
		// SmartDashboard.putNumber("Deciding Sensor", i);
		// return true;
		// }
		// }
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.LINE_FOLLOWER_SUBSYSTEM.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.LINE_FOLLOWER_SUBSYSTEM.stop();
	}
}
