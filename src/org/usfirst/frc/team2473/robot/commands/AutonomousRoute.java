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
		case LEFT_DRIVESTRAIGHT:
			addSequential(new SimpleDriveStraight(10, RobotMap.DRIVE_STRAIGHT_POWER));
			addSequential(new PointTurn(90, RobotMap.TURN_POWER));
			addSequential(new SimpleDriveStraight(2, RobotMap.DRIVE_STRAIGHT_POWER));
			break;
		case RIGHT_DRIVESTRAIGHT:
			addSequential(new SimpleDriveStraight(10, RobotMap.DRIVE_STRAIGHT_POWER));
			addSequential(new PointTurn(-90, RobotMap.TURN_POWER));
			addSequential(new SimpleDriveStraight(2, RobotMap.DRIVE_STRAIGHT_POWER));
			break;
		}
	}
}
