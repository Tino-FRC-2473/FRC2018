package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClawCommand extends Command {

	BoxSystem box = (BoxSystem) Robot.getSubsystem(BoxSystem.class);

	enum ClawState {
		RIGHT_CLOSED_ONLY, BOTH_OPEN, BOTH_CLOSED, LEFT_CLOSED_ONLY
	}

	ClawState state = ClawState.BOTH_OPEN;

	@Override
	public void execute() {
		updateState();
		updateHardware();
	}

	public void updateState() {
		if (state != ClawState.BOTH_CLOSED) {
			if (Robot.getControls().closeArmsButton.get()) {
				state = ClawState.BOTH_CLOSED;
			} else if (Robot.getControls().openArmsButton.get()) {
				state = ClawState.BOTH_OPEN;
			} else if (Robot.getControls().getThrottle().getX() < -0.5) {
				state = ClawState.LEFT_CLOSED_ONLY;
			} else if (Robot.getControls().getThrottle().getX() > 0.5) {
				state = ClawState.RIGHT_CLOSED_ONLY;
			} else {
				state = ClawState.BOTH_OPEN;				
			}
		} else {
			if (Robot.getControls().openArmsButton.get()) {
				state = ClawState.BOTH_OPEN;
			}		
		}
	}

	public void updateHardware() {
		switch (state) {
		case BOTH_OPEN:
			box.setPistonF();
			break;
		case BOTH_CLOSED:
			box.setPistonR();
			break;
		case LEFT_CLOSED_ONLY:
			box.setLeftClosed();
			break;
		case RIGHT_CLOSED_ONLY:
			box.setRightClosed();
			break;
		default:
			box.setPistonOff();
			break;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
