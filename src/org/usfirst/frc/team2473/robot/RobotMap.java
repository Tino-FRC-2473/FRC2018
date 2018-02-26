package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.RobotController;

public class RobotMap {
	//networking constants
	public static final String IP = "10.24.73.19";
	public static final int PORT = 5805;

	//sensor constants
	public static final int RELAY_PORT = 0;
	public static final int DIGITAL_PORT = 0;
	
	
	public static final int FL = 2;
	public static final int FR = 6;
	public static final int BL = 3;
	public static final int BR = 7;
	public static final int TESTING_ENC = 2;
	
	public static final int ELEVATOR_MOTOR = 11;
	public static final int CLIMB_ARM_MOTOR = 10;
	public static final int climbMotorL = 4;
	public static final int climbMotorL2 = 5;
	public static final int climbMotorR2 = 8;
	public static final int climbMotorR = 9;

	public static final int solenoidBCLFChannel = 0; //0 and 2 were switched
	public static final int solenoidBCLRChannel = 2;
	public static final int solenoidBCRFChannel = 1;
	public static final int solenoidBCRRChannel = 3;
	public static final int solenoidClimbF = 8;
	public static final int solenoidClimbR = 9;

	//digitalInputs
	public static int eleTopLS = 1;
	public static int eleBottomLS = 0;
	public static int hookLS = 3;
	public static int armBB = 4;
	
	//box system constants
	public static final double ELE_SAFE_POWER = 0.3;
	public static final double ELE_NORMAL_POWER = 1.0;
	public static final double ELE_AUTOMATED_POWER = 0.9;
	
	//button ports
	public static int armDownNum = 10;
	public static int armUpNum = 9;

	public static int openArmsNum = 2;
	public static int closeArmsNum = 1;

	public static int controlButtonNum = 8;
	public static int elevatorUpNum = 3; //5;
	public static int elevatorDownNum = 4; //6;

	public static int climbUpSlow = 4;
	public static int climbUp = 8;
	public static int climbDown = 11;
	public static int levelUp = 5;
	public static int levelDown = 6;
//	public static int pickUpLevel = 11;

	//	public static int cPistonInNum = -1;
//	public static int cPistonOutNum = -1;
	public static final int SLOW_BUTTON = 6;
	public static final int COMPRESSOR_ON = 1;
	public static final int COMPRESSOR_OFF = 5;
	
	public static final double ENC_PER_INCH = 71;

	public static final double DRIVE_STRAIGHT_POWER = 0.5;
	public static final double LARGER_RIGHT_TURN_POWER = 0.65*12.25/RobotController.getBatteryVoltage();
	public static final double SMALLER_RIGHT_TURN_POWER = (0.55*12.25)/RobotController.getBatteryVoltage();
	public static final double LARGER_LEFT_TURN_POWER = 0.5*12.25/RobotController.getBatteryVoltage();
	public static final double SMALLER_LEFT_TURN_POWER = (0.48*12.25)/RobotController.getBatteryVoltage();
	
	// All specifications are in inches
	
	//UNCERTAIN
	public static final double WALL_TO_AUTOLINE = 120.08;
	public static final double HALF_SWITCH_LENGTH = 24;
	public static final double AUTOLINE_TO_SWITCH = 19.92;
	public static final double EXCHANGE_ZONE_LENGTH = 36;
	public static final double HALF_ROBOT_WIDTH = 16;
	public static final double ROBOT_LENGTH = 35.5;

	//SIDE CASE
	public static final double INIT_DIST_TO_SWITCH = 150 + ROBOT_LENGTH/2;
	public static final double SIDE_DIST_TO_SWITCH = 56 - ROBOT_LENGTH/2;
	public static final double SIDE_TURN = 90;
	public static final double SECURE_BASELINE_LENGTH = 36;
	
	//CENTER CASE
	public static final double DIAGONAL_MOVEMENT_LENGTH = 56; //351
	public static final double FIRST_CENTER_DIST = 36 + ROBOT_LENGTH/2;
	public static final double SECOND_CENTER_DIST = 48;
	public static final double CENTER_TURN = 75;
	
	
	public enum Route {
		LEFT, RIGHT, CENTER, TESTING
	}
}