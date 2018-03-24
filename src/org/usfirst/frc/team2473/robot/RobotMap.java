package org.usfirst.frc.team2473.robot;

public class RobotMap {
	//networking constants
	public static final String IP = "10.24.73.19";
	public static final int PORT = 5805;

	//sensor constants
	public static final int RELAY_PORT = 0;
	public static final int DIGITAL_PORT = 0;
	
	//motor constants
	public static final int FL = 2;
	public static final int BL = 3;
	public static final int FR = 6;
	public static final int BR = 7;
	
	public static final int CLIMB_L1_MOTOR = 4;
	public static final int CLIMB_L2_MOTOR = 5;
	
	public static final int CLIMB_R1_MOTOR = 9;
	public static final int CLIMB_R2_MOTOR = 8;
	
	public static final int CLIMB_ARM_MOTOR = 10;
	public static final int ELEVATOR_MOTOR = 11;

	//solenoid constants
	public static final int BCLF_SOLENOID = 0; //0 and 2 were switched
	public static final int BCRF_SOLENOID = 1;
	public static final int BCLR_SOLENOID = 2;
	public static final int BCRR_SOLENOID = 3;
	public static final int CLIMB_F_SOLENOID = 4;
	public static final int CLIMB_R_SOLENOID = 5;

	//digital input constants
	public static final int LIMIT_ONE = 0;
	public static final int LIMIT_ZERO = 1;
	public static final int HOOK_LS = 3;
	public static final int ARM_BB = 4;
	
	//driving constants
	public static final double ENC_PER_INCH = 71;
	
}