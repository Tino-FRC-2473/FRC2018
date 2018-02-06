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
	
	public AutonomousRoute(Route r) {
//		boolean switchSide = (DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R');
		boolean switchSide = false;
		System.out.println("SWITCH SIDE: "+(switchSide ? "REIGHT" : "LEFETTO"));
		switch (r) {
		case LEFT:
//			addDriveStraight(RobotMap.WALL_TO_SWITCH + RobotMap.HALF_SWITCH_LENGTH);
			addDriveStraight(48); //
			addSequential(new Wait(delay));
			addTurn(-45);
			addSequential(new Wait(delay));
			addDriveStraight(12*Math.sqrt(2));
			addSequential(new Wait(delay));
			addTurn(45);
			addSequential(new Wait(delay));
			addDriveStraight(9*12-13);
			addSequential(new Wait(delay));
			if (!switchSide) {
				addTurn(90);
				addSequential(new Wait(delay));
				addDriveStraight(12); // Side start to switch
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
			addDriveStraight(RobotMap.DIAGONAL_MOVEMENT_LENGTH);//RobotMap.DIAGONAL_MOVEMENT_LENGTH);
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
			addDriveStraight(36);
			addTurn(45);
			addDriveStraight(12);
			addTurn(-90);
//			resetGyro();
//			addTurn(switchSide ? 45 : -45);
////			addTurn(switchSide ? 90 : -90);
//			resetGyro();			
//			addDriveStraight(20);
			break;
		}
	}

	@Override
	protected void end() {
		Robot.piDriveTrain.disable();
		System.out.println("end of autonomous...");
	}

	private void addDriveStraight(double dist) {
		addSequential(new SimpleDriveStraight(dist, RobotMap.DRIVE_STRAIGHT_POWER));
	}

	private void addTurn(double angle) {
		addSequential(new GyroTurn(angle, RobotMap.TURN_POWER));
//		addSequential(new PIDTurn(angle, 0.5, false));
	}
}
