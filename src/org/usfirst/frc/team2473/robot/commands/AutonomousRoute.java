package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.RobotMap.Route;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRoute extends CommandGroup {

	public AutonomousRoute(Route r) {
		switch (r) {
		case LEFT:
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			addTurn(90);
			addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			break;
		case RIGHT:
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			addTurn(-90);
			addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			break;
		case CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH);
			addTurn(-70);
			addDriveStraight(100);
			addTurn(70);
			addDriveStraight(65);
		// These are not done
		case LEFT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH);
			addTurn(70);
		case RIGHT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH);
			addTurn(-70);
		}
	}
	
	private void addDriveStraight(double dist) {
		addSequential(new SimpleDriveStraight(dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}
	
	private void addTurn(double angle) {
		addSequential(new PointTurn(angle, RobotMap.TURN_POWER));
	}
}
