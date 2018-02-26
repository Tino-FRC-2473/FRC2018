package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.RobotMap.Route;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class AutonomousRoute extends CommandGroup {

	private final long delay = 250;
	private double lastAngle;

//	public AutonomousRoute(boolean switchSide, Route r) {
//	}

	@Override
	protected void end() {
		TrackingRobot.getDriveTrain().disable();
		System.out.println("end of autonomous...");
	}

	private void turnAndGo(double angle, double dist) {
		lastAngle = angle;
		if(angle >= 0) {
			addSequential(new PointTurn(angle, RobotMap.LARGER_RIGHT_TURN_POWER));			
		} else {
			addSequential(new PointTurn(angle, RobotMap.LARGER_LEFT_TURN_POWER));						
		}
		addSequential(new PointTurn(angle, RobotMap.LARGER_RIGHT_TURN_POWER));
		addSequential(new Wait(delay));
		if (dist != 0) {
			addSequential(new AutoDriveStraight(angle, dist, RobotMap.DRIVE_STRAIGHT_POWER));
			addSequential(new Wait(delay));
		}
	}
	
	@Override
	protected void interrupted() {
		Scheduler.getInstance().removeAll();
	}

	private void driveStraight(double dist) {
		addSequential(new AutoDriveStraight(lastAngle, dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}

	public void configure(boolean switchSide, Route r) {
		lastAngle = 0;
		addParallel(new PreSwitchBox());
		switch (r) {
		case LEFT:			
			driveStraight(RobotMap.INIT_DIST_TO_SWITCH/3); // RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH
			if (!switchSide) {
				turnAndGo(RobotMap.SIDE_TURN, RobotMap.SIDE_DIST_TO_SWITCH/3); // 12
				addSequential(new ReleaseBox());
			} else {
				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH/3);
			}
			break;
		case RIGHT:
			driveStraight(RobotMap.INIT_DIST_TO_SWITCH/3);
			if (switchSide) {
				turnAndGo(-RobotMap.SIDE_TURN, RobotMap.SIDE_DIST_TO_SWITCH/3);
				addSequential(new ReleaseBox());
			} else {
				turnAndGo(0, RobotMap.SECURE_BASELINE_LENGTH/3);
			}
			break;
		case CENTER:
			driveStraight(RobotMap.FIRST_CENTER_DIST/3);
			turnAndGo(switchSide ? RobotMap.CENTER_TURN: -RobotMap.CENTER_TURN, RobotMap.DIAGONAL_MOVEMENT_LENGTH/3); 
			turnAndGo(0, RobotMap.SECOND_CENTER_DIST/3); // re-center and pass baseline
			addSequential(new ReleaseBox());
			break;
		case TESTING:
			driveStraight(200);
			break;
		}
	}
}
