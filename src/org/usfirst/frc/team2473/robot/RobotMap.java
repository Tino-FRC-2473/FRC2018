<<<<<<< HEAD
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
	public static int elevatorMotor = 11;
	public static int climbArmMotor = 10;
	public static int climbMotorL = 4;
	public static int climbMotorL2 = 5;
	public static int climbMotorR2 = 8;
	public static int climbMotorR = 9;
	
	//solenoids
	public static int solenoidBCLFChannel = 0;
	public static int solenoidBCLRChannel = 2;
	
	public static int solenoidBCRFChannel = 1;
	public static int solenoidBCRRChannel = 3;
	
	public static int solenoidClimbF = 6;
	public static int solenoidClimbR = 7;
	
	//joystick
	public static int joystickNum = 0;
	
	//button ports
	public static int armDownNum = 2;
	public static int armUpNum = 4;
	//public static int jumpNum = 3;
	public static int openArmsNum = 11;
	public static int closeArmsNum = 12;
	public static int controlButtonNum = 8;
	public static int elevatorUpNum = 5;
	public static int elevatorDownNum = 6;
	public static int climbUp1 = 1;
	public static int climbUp2 = 3;
	public static int cPistonInNum = 9;
	public static int cPistonOutNum = 10;

	
	//digitalInputs
	public static int eleTopLS = 2;
	public static int eleBottomLS = 1;
	public static int hookLS = 3;
	public static int armBB = 4;
}
