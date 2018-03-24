package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutonomousRoute extends CommandGroup {
	private static final double DRIVE_STRAIGHT_POWER = 0.5;
	private static final double LARGER_RIGHT_TURN_POWER = 0.7;
	private static final double LARGER_LEFT_TURN_POWER = 0.625;
	
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
		else if(angle < 0)
			addSequential(new PointTurn(angle, LARGER_LEFT_TURN_POWER));						
//		else 
//			addSequential(new PointTurn(angle, (Devices.getInstance().getNavXGyro().getYaw() < 0) ? LARGER_RIGHT_TURN_POWER : LARGER_LEFT_TURN_POWER));
		
		addSequential(new PointTurn(angle, LARGER_RIGHT_TURN_POWER));
		addSequential(new Wait(delay));
		if(dist != 0) {
			addSequential(new DriveStraight(angle, dist, DRIVE_STRAIGHT_POWER));
			addSequential(new Wait(delay));
		}
	}
	
	@Override
	protected void interrupted() {
		this.cancel();
	}

	private void driveStraight(double dist) {
		addSequential(new DriveStraight(lastAngle, dist, DRIVE_STRAIGHT_POWER*Math.signum(dist)));
	}

	public void configure(boolean switchSide, Auto.Route r) {
		lastAngle = 0;
//		if(r != Auto.Route.TESTING && r != Auto.Route.CV_TESTING) addParallel(new ChangeElevatorLevel(3));

		switch(r) {
		case LEFT:			
			driveStraight(Auto.INIT_DIST_TO_SWITCH ); // Auto.WALL_TO_SWITCH + Auto.HALF_SWITCH_LENGTH
			if(!switchSide) {
				addTurnAndGo(Auto.SIDE_TURN, Auto.SIDE_DIST_TO_SWITCH); // 12
				addSequential(new ToggleArms(true, 250));
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT:
			driveStraight(Auto.INIT_DIST_TO_SWITCH);
			if(switchSide) {
				addTurnAndGo(-Auto.SIDE_TURN, Auto.SIDE_DIST_TO_SWITCH);
				addSequential(new ToggleArms(true, 250));
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case CENTER:
			driveStraight(Auto.FIRST_CENTER_DIST);
			addTurnAndGo(switchSide ? Auto.CENTER_TURN: -Auto.CENTER_TURN, Auto.DIAGONAL_MOVEMENT_LENGTH); 
			addTurnAndGo(0, Auto.SECOND_CENTER_DIST); // re-center and pass baseline
			addSequential(new ToggleArms(true, 250));
			break;
		case REACH_RIGHT:
			driveStraight(Auto.INIT_DIST_TO_SWITCH);
			if(switchSide) {
				addTurnAndGo(-Auto.SIDE_TURN, Auto.SIDE_DIST_TO_SWITCH);
				addSequential(new ToggleArms(true, 250));
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case REACH_LEFT:
			driveStraight(Auto.FIRST_CENTER_DIST);
			addTurnAndGo(switchSide ? Auto.CENTER_TURN: -Auto.CENTER_TURN, Auto.DIAGONAL_MOVEMENT_LENGTH); 
			addTurnAndGo(0, Auto.SECOND_CENTER_DIST); // re-center and pass baseline
			addSequential(new ToggleArms(true, 250));

			if(!switchSide) {				
				//pull away and prep for pickup
				addSequential(new Wait(250));
				driveStraight(-Auto.ROBOT_LENGTH/2);
				addSequential(new ChangeElevatorLevel(1));				

				//turn into box and grab it
				addTurnAndGo(88, Auto.ROBOT_LENGTH);
				addSequential(new ToggleArms(false, 250));
				
				//drive away and prep for release
				driveStraight(-Auto.ROBOT_LENGTH);
				addSequential(new ChangeElevatorLevel(3));
				addTurnAndGo(0, Auto.ROBOT_LENGTH);
//				addSequenti(new PointTurn(0, LARGER_LEFT_TURN_POWER));

				//drive into switch and release box
//				driveStraight(Auto.ROBOT_LENGTH);
				addSequential(new ToggleArms(true, 250));				
			} else {
				addTurnAndGo(0, Auto.SECURE_BASELINE_LENGTH);
			}
			break;
		case TESTING:
			driveStraight(50);			
			addTurnAndGo(65, 60);
			addTurnAndGo(0, 25);
			break;
		case CV_TESTING:
			addSequential(new CVAuto());
			break;
		}
	}
	
}
