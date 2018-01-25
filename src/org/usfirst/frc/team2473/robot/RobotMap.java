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

	public static int elevatorMotor = 0;
	public static int climbArmMotor = 1;
	public static int climbMotor = 2;
	
	public static int solenoidLFChannel = 6;
	public static int solenoidLRChannel = 7;
	public static int solenoidRFChannel = 5;
	public static int solenoidRRChannel = 4;
	
	public static int joystickNum = 0;
	public static int ascendNum = 1;
	public static int descendNum = 2;
	public static int openArmsNum = 11;
	public static int closeArmsNum = 12;
	public static int bottomElevatorNum = 5;
	public static int lowerMidElevatorNum = 6;
	public static int upperMidElevatorNum = 7;
	public static int topElevatorNum = 8;
}
