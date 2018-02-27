package org.usfirst.frc.team2473.robot;

public class Auto {
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
