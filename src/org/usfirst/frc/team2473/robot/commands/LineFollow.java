package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineFollow extends Command {

	// private final double offset = -0.2;

	// private int leftLightSensor = RobotMap.LEFT_LIGHT_SENSOR;
	// private int rightLightSensor = RobotMap.RIGHT_LIGHT_SENSOR;
	// private int middleLightSensor = RobotMap.MIDDLE_LIGHT_SENSOR;

	// private String state = "";

	public LineFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		for (int i = 0; i < 3; i++) {
			SmartDashboard.putBoolean("Dark Sensor Value " + i, Robot.driveTrain.getDigitalSensorValue(i));
			SmartDashboard.putNumber("Light Sensor Value" + i, Robot.driveTrain.getAnalogSensorValue(i));
		}

		// if
		// (Robot.LINE_FOLLOWER_SUBSYSTEM.getSensorValue(RobotMap.MIDDLE_LIGHT_SENSOR))
		// {
		// Robot.LINE_FOLLOWER_SUBSYSTEM.drive(-0.4, 0.2);
		// } else {
		// Robot.LINE_FOLLOWER_SUBSYSTEM.drive(-0.4, -0.2);
		// }

		// if (!Robot.driveTrain.getDigitalSensorValue(middleLightSensor) &&
		// !Robot.driveTrain.getDigitalSensorValue(leftLightSensor)
		// && !Robot.driveTrain.getDigitalSensorValue(rightLightSensor)) {
		// state = "no sensor";
		// } else if (Robot.driveTrain.getDigitalSensorValue(leftLightSensor)
		// && !Robot.driveTrain.getDigitalSensorValue(middleLightSensor)
		// && !Robot.driveTrain.getDigitalSensorValue(rightLightSensor)) {
		//
		// } else if (Robot.driveTrain.getDigitalSensorValue(rightLightSensor)
		// && !Robot.driveTrain.getDigitalSensorValue(middleLightSensor)
		// && !Robot.driveTrain.getDigitalSensorValue(leftLightSensor)) {
		//
		// } else if (Robot.driveTrain.getDigitalSensorValue(middleLightSensor)
		// && Robot.driveTrain.getDigitalSensorValue(leftLightSensor)
		// && Robot.driveTrain.getDigitalSensorValue(rightLightSensor)) {
		// }
		//
		// switch (state) {
		// case "no sensor":
		// break;
		// case "left sensor:":
		// }
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
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.driveTrain.stop();
	}
}
