package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.LineFollowerSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineFollow extends Command {
	private LineFollowerSubsystem subsystem;

	public LineFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		subsystem = (LineFollowerSubsystem) Robot.getSubsystem(LineFollowerSubsystem.class);
		requires(subsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		subsystem.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for(int i = 0; i < 3; i++) SmartDashboard.putNumber("Light Sensor Value " + i, subsystem.getSensorValue(i));
//		if (subsystem.getPIDController().isEnabled()) {
//			subsystem.drive(0.3, subsystem.getPidValue());
//		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		subsystem.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		subsystem.disable();
	}
}
