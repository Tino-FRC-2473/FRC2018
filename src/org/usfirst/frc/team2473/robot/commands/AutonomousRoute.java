package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutonomousRoute extends CommandGroup {
	private static final double DRIVE_STRAIGHT_POWER = 0.5;
	private static final double LARGER_RIGHT_TURN_POWER = 0.65 * 12.25;
	private static final double LARGER_LEFT_TURN_POWER = 0.5 * 12.25;
	
	private final long delay = 250;
	private double lastAngle;

//	public AutonomousRoute(boolean switchSide, Route r) {
//	}

	@Override
	protected void end() {
		TrackingRobot.getDriveTrain().disable();
		System.out.println("end of autonomous...");
	}

	private void addTurnAndGo(double angle, double dist) {
		lastAngle = angle;
		if(angle >= 0)
			addSequential(new PointTurn(angle, LARGER_RIGHT_TURN_POWER));			
		else
			addSequential(new PointTurn(angle, LARGER_LEFT_TURN_POWER));						
		
		addSequential(new PointTurn(angle, LARGER_RIGHT_TURN_POWER));
		addSequential(new Wait(delay));
		if(dist != 0) {
			addSequential(new DriveStraight(angle, dist, DRIVE_STRAIGHT_POWER));
			addSequential(new Wait(delay));
		}
	}
	
	@Override
	protected void interrupted() {
		Scheduler.getInstance().removeAll();
	}

	private void driveStraight(double dist) {
		addSequential(new DriveStraight(lastAngle, dist, DRIVE_STRAIGHT_POWER));
	}

	public void configure(boolean switchSide, Auto.Route r) {
		lastAngle = 0;
		addParallel(new PreSwitchBox());
		switch(r) {
		case LEFT:			
			driveStraight(Auto.INIT_DIST_TO_SWITCH ); // Auto.WALL_TO_SWITCH + Auto.HALF_SWITCH_LENGTH
			if(!switchSide) {
				addTurnAndGo(Auto.SIDE_TURN, Auto.SIDE_DIST_TO_SWITCH); // 12
				addSequential(new ReleaseBox());
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT:
			driveStraight(Auto.INIT_DIST_TO_SWITCH);
			if(switchSide) {
				addTurnAndGo(-Auto.SIDE_TURN, Auto.SIDE_DIST_TO_SWITCH);
				addSequential(new ReleaseBox());
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case CENTER:
			driveStraight(Auto.FIRST_CENTER_DIST);
			addTurnAndGo(switchSide ? Auto.CENTER_TURN: -Auto.CENTER_TURN, Auto.DIAGONAL_MOVEMENT_LENGTH); 
			addTurnAndGo(0, Auto.SECOND_CENTER_DIST); // re-center and pass baseline
			addSequential(new ReleaseBox());
			break;
		case TESTING:
			driveStraight(200);
			break;
		}
	}
}
