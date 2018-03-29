package org.usfirst.frc.team2473.robot;

public class Auto {
	public static final double MAX_POW = 0.7;
	public static final double MIN_POW = 0.3;
	// All specifications are in inches
	
	//UNCERTAIN
	public static final double WALL_TO_AUTOLINE = 120.08;
	public static final double HALF_SWITCH_LENGTH = 24;
	public static final double AUTOLINE_TO_SWITCH = 19.92;
	public static final double EXCHANGE_ZONE_LENGTH = 36;
	public static final double HALF_ROBOT_WIDTH = 16;
	public static final double ROBOT_LENGTH = 35.5;

	//SIDE CASE
	public static final double INIT_DIST_TO_SWITCH = (150 + ROBOT_LENGTH/2)/3;
	public static final double SIDE_DIST_TO_SWITCH = (56 - ROBOT_LENGTH/2)/3;
	public static final double SIDE_TURN = 90;
	public static final double SECURE_BASELINE_LENGTH = 36/3;
	
	//CENTER CASE
	public static final double DIAGONAL_MOVEMENT_LENGTH = 56;
	public static final double FIRST_CENTER_DIST = (36);
	public static final double SECOND_CENTER_DIST = 40;
	public static final double CENTER_TURN = 75;
	
	
	public enum Route {
		LEFT, RIGHT, CENTER, REACH_RIGHT, REACH_LEFT, TESTING, CV_TESTING
	}
}
