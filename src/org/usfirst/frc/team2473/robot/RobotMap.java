package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static int elevatorMotor = 0;
	public static int climbArmMotor = 1;
	public static int climbMotor = 2;
	
	public static int solenoidFChannel = 3;
	public static int solenoidRChannel = 4;
	
	public static int joystickNum = 0;
	public static int ascendNum = 1;
	public static int descendNum = 2;
	public static int openArmsNum = 3;
	public static int closeArmsNum = 4;
	public static int bottomElevatorNum = 5;
	public static int lowerMidElevatorNum = 6;
	public static int upperMidElevatorNum = 7;
	public static int topElevatorNum = 8;
}
