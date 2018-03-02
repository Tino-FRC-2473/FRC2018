package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightFollow extends Command {

	private boolean offTheLine = false;
	
	private double offset = 0;

	public RightFollow() {
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!Robot.driveTrain.getDigitalSensorValue(RobotMap.MIDDLE_DIGITAL_SENSOR)) {
			offTheLine = true;
		}
		Robot.driveTrain.drive(-0.4, -0.321 + offset);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (offTheLine && Robot.driveTrain.getDigitalSensorValue(RobotMap.MIDDLE_DIGITAL_SENSOR)) {
			(new LineFollow()).start();
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.stop();
	}
}
