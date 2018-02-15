package org.usfirst.frc.team2473.robot;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int MOTOR_FRONT_LEFT = 2;
	public static final int MOTOR_BACK_LEFT = 3;
	public static final int MOTOR_FRONT_RIGHT = 4;
	public static final int MOTOR_BACK_RIGHT = 5;

	public static final int LEFT_DIGITAL_SENSOR = 1;
	public static final int LEFT_ANALOG_SENSOR = 0;
	public static final int MIDDLE_DIGITAL_SENSOR = 0;
	public static final int MIDDLE_ANALOG_SENSOR = 2;
	public static final int RIGHT_DIGITAL_SENSOR = 2;
	public static final int RIGHT_ANALOG_SENSOR = 1;
}
