package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.TrackingRobot.RunState;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {
	private BoxSystem sub;
//	private boolean isControl;
//	private double startingEnc;

	public ElevatorCommand() {
		sub = Robot.getBox();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println(Devices.getInstance().getDigitalInput(RobotMap.LIMIT_ZERO).get());
//		System.out.println(Devices.getInstance().getDigitalInput(RobotMap.LIMIT_ONE).get());
		double pow = 0;

		if (sub.limitSwitchZero()) {
//			sub.resetEnc();
			sub.zero();
		}

		if (sub.hasZeroed() && Robot.getState() == RunState.COMPETITION) {
			if (Robot.getControls().getPanel().getRawButton(6)) {
				pow = sub.getUpPower();
			} else if (Robot.getControls().getPanel().getRawButton(8)) {
				if (sub.limitSwitchZero()) {
//					System.out.println("zeroing..."); 
//					sub.resetEnc();
					sub.zero();
				} else {
					pow = sub.getDownPower();
				}
			} else {
				pow = 0;
			}
		} else {
			if (Robot.getControls().getPanel().getRawButton(6)) {
				pow = -0.3;
			} else if (Robot.getControls().getPanel().getRawButton(8)) {
				pow = 0.3;
			} else {
				pow = 0;
			}
		}
		sub.setPow(pow);
//		if(sub.hasZeroed())	sub.setLevel(sub.getCurrPos());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		sub.stop();
		System.out.println("ELEVATOR LIMIT SWITCH HIT");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		sub.stop();
	}
}