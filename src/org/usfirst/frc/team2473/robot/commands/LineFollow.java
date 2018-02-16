package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineFollow extends Command {
	private final double OFFSET = 0;
	public LineFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveTrain.setSetpoint(Robot.driveTrain.getAnalogSensorValue(RobotMap.MIDDLE_ANALOG_SENSOR));
		Robot.driveTrain.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (int i = 0; i < 3; i++) {
			SmartDashboard.putBoolean("Dark Sensor Value " + i, Robot.driveTrain.getDigitalSensorValue(i));
			SmartDashboard.putNumber("Light Sensor Value" + i, Robot.driveTrain.getAnalogSensorValue(i));
		}
		Robot.driveTrain.drive(-0.4, Robot.driveTrain.getPIDValue()  + OFFSET);
		
		/*
		 *	When either left or right sensor is triggered, but not both
		 *	1) PID is disabled
		 *	2) Turn in the appropriate direction.
		 *	3) Re-enable PID to let nature take its course.
		 *		A) Keep PID as is if turning right.
		 *		B) Make PID negative if turning left.
		 */
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.disable();
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.disable();
		Robot.driveTrain.stop();
	}
}
