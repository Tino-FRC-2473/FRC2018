package org.usfirst.frc.team2473.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int FL = 2;
	public static final int FR = 6;
	public static final int BL = 3;
	public static final int BR = 7;
	public static final int TESTING_ENC = 2;

	public static final String FRONT_RIGHT_ENC = "FR_ENC";
	public static final String FRONT_LEFT_ENC = "FL_ENC";
	public static final String TESTING_ENC_KEY = "T_ENC";
	public static final String TESTING_ANALOG_GYRO_KEY = "A_G";

//	public static final int MOTOR = 6;
//	public static final int SERVO = 0;
//	public static final int GYRO = 0;
	

	public static final String NO_TRIALS = "NA";
	public static final double ENC_PER_INCH = 34.5; // chassis 74.19/2;

	// keys
//	public static final String MOTOR_ENCODER_KEY = "encoder key";
//	public static final String MOTOR_VOLTAGE_KEY = "voltage key";
//	public static final String MOTOR_CURRENT_KEY = "current key";
//	public static final String MOTOR_POWER_KEY = "power key";
//	public static final String MOTOR_SPEED_KEY = "speed key";
//	public static final String SERVO_POSITION_KEY = "servo position key";
//	public static final String SERVO_POWER_KEY = "servo power key";
//	public static final String GYRO_HEADING_KEY = "gyro key";

	public static final String PEG_DISTANCE = "peg distance";
	public static final String PEG_ANGLE = "peg angle";
	public static final String FUNCTION_TRIGGER = "function trigger";
	public static final String STOP_TRIGGER = "stop trigger";
	public static final String GYRO_YAW = "gyro yaw";
	public static final String GYRO_RATE = "gyro rate";

	// CV movement constants below
	public static final double INCH_OVER_ENCODER = 0.00157; // 0.00177
	// these values are likely not final
//	public static final double POWER_OVER_INCH = 0.01; // the power over the
//														// distance left in
//														// inches
//	public static final double MAX_POW_SEG = 0.7; // maximum power for Segment
//													// code
//	public static final double MIN_POW_SEG = 0.1; // minimum power for Segment
//													// code
//	// the ratio of power difference over power for CVSegments
//	public static final double DIFF_OVER_POW_SEG = 0.5;
//	// the turning power for CVSegmments, NEVER negative
//	public static final double TURN_POW_SEG = 0.5;
//	// at this distance (inches), the robot says "I've reached the target" and
//	// CVSegments cancels itself
//	public static final double MIN_DISTANCE_TOLERANCE = 5;
//	public static final double GYRO_CAMERA_DIST = 0;
//	public static final double TARGET_OVER_TOTAL = 0.2;
//	public static final double MIN_SEGMENT_TOLERANCE = 4;
	// The "Master" sensor position, should be used normally
	public static final int SENSOR_POS = 0;

	public static final double DRIVE_STRAIGHT_POWER = 0.5;
	public static final double TURN_POWER = 0.5;
	
	// All specifications are in inches
	public static final double WALL_TO_AUTOLINE = 120.08;
	public static final double WALL_TO_SWITCH = 140;
	public static final double HALF_SWITCH_LENGTH = 24;
	public static final double AUTOLINE_TO_SWITCH = 19.92;
	public static final double EXCHANGE_ZONE_LENGTH = 36;
	public static final double SIDE_START_TO_SWITCH = 55.56;

	public static final double HALF_ROBOT_WIDTH = 24.5/2;
	public static final double ROBOT_LENGTH = 28.3;

	public static final double DIAGONAL_MOVEMENT_LENGTH = 72; //351
	public static final double SECURE_BASELINE_LENGTH = 36;
	
	public enum Route {
		LEFT, RIGHT, CENTER, LEFT_CENTER, RIGHT_CENTER, DRIVESTRAIGHT, TESTING
	}
}