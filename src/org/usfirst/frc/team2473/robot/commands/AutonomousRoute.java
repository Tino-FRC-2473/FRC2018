package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.RobotMap.Route;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRoute extends CommandGroup {

	private final long delay = 250;
	private double lastAngle;
	
	public AutonomousRoute(boolean switchSide, Route r) {
		lastAngle = 0;
		System.out.println("SWITCH SIDE: "+(switchSide ? "REIGHT" : "LEFETTO"));
		switch (r) {
		case LEFT:
			driveStraight(48); // RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH
			turnAndGo(-45, 21*1.414); // RobotMap.SIDE_START_TO_SWITCH
			turnAndGo(0, 9*12-13);
			if (!switchSide) {
				turnAndGo(90, 20); // 12
			} else {
				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT:
			driveStraight(48);
			turnAndGo(45, 21*1.414);
			turnAndGo(0, 9*12-13);
			if (!switchSide) {
				turnAndGo(-90, 20);
			} else {
				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case CENTER:
			driveStraight(60);
			turnAndGo(switchSide ? 60: -45, RobotMap.DIAGONAL_MOVEMENT_LENGTH); 
			turnAndGo(0, 96); // re-center and pass baseline
//			turnAndGo(0, RobotMap.AUTOLINE_TO_SWITCH);
			break;
//		case LEFT_CENTER:
//			driveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
//			turnAndGo(-70, RobotMap.DIAGONAL_MOVEMENT_LENGTH);
//			if (!switchSide) {
//				turnAndGo(0, RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
//				turnAndGo(90, RobotMap.SIDE_START_TO_SWITCH + 10);
//			} else {
//				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH);
//			}
//			break;
//		case RIGHT_CENTER:
//			driveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
//			turnAndGo(70, RobotMap.DIAGONAL_MOVEMENT_LENGTH);
//			if (switchSide) {
//				turnAndGo(0, RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
//				turnAndGo(-90, RobotMap.SIDE_START_TO_SWITCH + 10);
//			} else {
//				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH);
//			}
//			break;
		case DRIVESTRAIGHT:
			driveStraight(300);
			break;
		case TESTING:
//			turnAndGo(0, 200);
			turnAndGo(45, 0);
//			turnAndGo(0, 180);
//			turnAndGo(45, 150);
//			turnAndGo(0, 150);
//			turnAndGo(-90, 150);
			break;
		}
	}

	@Override
	protected void end() {
		Robot.piDriveTrain.disable();
		System.out.println("end of autonomous...");
	}
	
	private void turnAndGo(double angle, double dist) {
		lastAngle = angle;
		addSequential(new GyroTurn(angle, RobotMap.TURN_POWER));
		addSequential(new Wait(delay));
		if (dist != 0) {
			addSequential(new SimpleDriveStraight(angle, dist, RobotMap.DRIVE_STRAIGHT_POWER));
			addSequential(new Wait(delay));
		}
	}
	
	private void driveStraight(double dist) {
		addSequential(new SimpleDriveStraight(lastAngle, dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}
	
	
}
