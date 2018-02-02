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
	
	public static int solenoidBCLFChannel = 6;
	public static int solenoidBCLRChannel = 7;
	public static int solenoidBCRFChannel = 5;
	public static int solenoidBCRRChannel = 4;
	public static int solenoidClimbF = 8;
	public static int solenoidClimbR = 9;
	
	
	public static int joystickNum = 0;
	
	public static int armDownNum = 1;
	public static int armUpNum = 2;
	public static int openArmsNum = 11;
	public static int closeArmsNum = 12;
	public static int controlButtonNum = 8;
	public static int elevatorUpNum = 5;
	public static int elevatorDownNum = 6;
	public static int climbUp = 0;
	public static int climbDown = 0;
	public static int cPistonInNum = 7;
	public static int cPistonOutNum = 8;
}
