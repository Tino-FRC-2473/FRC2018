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
		boolean switchSide = (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R');
		switch (r) {
		case LEFT:
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			if (!switchSide) {
				addTurn(90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			}
			else{
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT:
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			if (switchSide) {
				addTurn(-90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			}
			else{
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(switchSide ? 70 : -70);
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(switchSide ? -70 : 70);
			addDriveStraight(RobotMap.AUTOLINE_TO_SWITCH);
		case LEFT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(-70);
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(70);
			if (!switchSide) {
				addDriveStraight(RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
				addTurn(90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH + 10);
			}
			else{
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
		case RIGHT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(70);
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(-70);
			if (switchSide) {
				addDriveStraight(RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
				addTurn(-90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH + 10);
			}
			else{
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
		}
	}
	
	private void addDriveStraight(double dist) {
		addSequential(new SimpleDriveStraight(dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}
	
	private void addTurn(double angle) {
		addSequential(new PointTurn(angle, RobotMap.TURN_POWER));
	}
}
