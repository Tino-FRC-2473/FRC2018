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

	//motors
	public static int elevatorMotor = 2;
	public static int climbArmMotor = 7;
	public static int climbMotorL = 2;
	public static int climbMotorR = 2;
	
	//solenoids
	public static int solenoidBCLFChannel = 6;
	public static int solenoidBCLRChannel = 7;
	public static int solenoidBCRFChannel = 5;
	public static int solenoidBCRRChannel = 4;
	public static int solenoidClimbF = 8;
	public static int solenoidClimbR = 9;
	
	//joystick
	public static int joystickNum = 0;
	
	//button ports
	public static int armDownNum = 2;
	public static int armUpNum = 4;
	public static int openArmsNum = 11;
	public static int closeArmsNum = 12;
	public static int controlButtonNum = 8;
	public static int elevatorUpNum = 5;
	public static int elevatorDownNum = 6;
	public static int climbUp = 9;
	public static int climbDown = 9;
	public static int cPistonInNum = 7;
	public static int cPistonOutNum = 8;

	
	//digitalInputs
	public static int eleTopLS = 1;
	public static int eleBottomLS = 2;
	public static int hookLS = 3;
	public static int armBB = 4;
}
