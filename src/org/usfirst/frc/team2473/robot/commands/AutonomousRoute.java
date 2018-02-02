package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
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
		System.out.println("SWITCH SIDE: "+(switchSide ? "REIGHT" : "LEFETTO"));
		switch (r) {
		case LEFT:
			resetGyro();
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			if (!switchSide) {
//				resetGyro();
				addTurn(90);
//				resetGyro();
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			} else {
//				resetGyro();
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT:
			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			if (switchSide) {
				addTurn(-90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH);
			} else {
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case CENTER:
			addDriveStraight(20);//RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(switchSide ? 70 : -70);
			addDriveStraight(10);//RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(switchSide ? -70 : 70);
			addDriveStraight(5);//RobotMap.AUTOLINE_TO_SWITCH);
			break;
		case LEFT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(-70);
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(70);
			if (!switchSide) {
				addDriveStraight(RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
				addTurn(90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH + 10);
			} else {
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case RIGHT_CENTER:
			addDriveStraight(RobotMap.EXCHANGE_ZONE_LENGTH + RobotMap.ROBOT_LENGTH);
			addTurn(70);
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);
			addTurn(-70);
			if (switchSide) {
				addDriveStraight(RobotMap.AUTOLINE_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
				addTurn(-90);
				addDriveStraight(RobotMap.SIDE_START_TO_SWITCH + 10);
			} else {
				addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			}
			break;
		case TESTING:
			resetGyro();
			addDriveStraight(RobotMap.SECURE_BASELINE_LENGTH);
			resetGyro();
			addTurn(switchSide ? 45 : -45);
//			addTurn(switchSide ? 90 : -90);
			resetGyro();			
			addDriveStraight(20);
			break;
		}
	}

	@Override
	protected void end() {
		Robot.piDriveTrain.disable();
		System.out.println("end of autonomous...");
	}

	void resetGyro() {
		while (Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 5) {
			Devices.getInstance().getNavXGyro().zeroYaw();
		}
		System.out.println("finished resetting...");
	}

	private void addDriveStraight(double dist) {
		addSequential(new SimpleDriveStraight(dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}

	private void addTurn(double angle) {
		addSequential(new PointTurn(angle, RobotMap.TURN_POWER));
	}
}
