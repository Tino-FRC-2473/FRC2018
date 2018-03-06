package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineFollow extends Command {
	private double offset = -0.160;

	String nextCommand = "";

	public LineFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// Robot.driveTrain.setSetpoint(Robot.driveTrain.getAnalogSensorValue(RobotMap.MIDDLE_ANALOG_SENSOR));
		Robot.driveTrain.disable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Robot.driveTrain.drive(-0.4, Robot.driveTrain.getPIDValue() + offset);
		if (Robot.driveTrain.getDigitalSensorValue(RobotMap.MIDDLE_DIGITAL_SENSOR)) {
			Robot.driveTrain.drive(-0.4, -0.221 + offset);
		} else {
			Robot.driveTrain.drive(-0.4, 0.221 + offset);
		}

		SmartDashboard.putNumber("Front Left Motor", Robot.driveTrain.getEncoderCount(RobotMap.MOTOR_FRONT_LEFT));
		SmartDashboard.putNumber("Front Right Motor", Robot.driveTrain.getEncoderCount(RobotMap.MOTOR_FRONT_RIGHT));
		SmartDashboard.putNumber("Back Left Motor", Robot.driveTrain.getEncoderCount(RobotMap.MOTOR_BACK_LEFT));
		SmartDashboard.putNumber("Back Right Motor", Robot.driveTrain.getEncoderCount(RobotMap.MOTOR_BACK_RIGHT));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.driveTrain.getDigitalSensorValue(RobotMap.RIGHT_DIGITAL_SENSOR)) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveTrain.disable();
		Robot.driveTrain.stop();

		// if (nextCommand.equals("left")) {
		// (new LeftFollow()).start();
		// } else if (nextCommand.equals("right")) {
		// (new RightFollow()).start();
		// }
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.disable();
		Robot.driveTrain.stop();
	}
}
