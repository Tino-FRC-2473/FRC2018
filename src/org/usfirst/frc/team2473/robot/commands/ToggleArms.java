package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleArms extends Command {
	private BoxSystem box;
	boolean open;
	long init, limit;


	public ToggleArms(boolean open) {
		box = Robot.getBox();
		requires(box);
		this.open = open;
		this.limit = 50;
	}
	
	public ToggleArms(boolean open, long limit) {
		box = Robot.getBox();
		requires(box);
		this.open = open;
		this.limit = limit;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		init = System.currentTimeMillis();
		if (open && BoxSystem.openState != BoxSystem.ArmState.OPEN) {
			box.setPistonF();
			BoxSystem.openState = BoxSystem.ArmState.OPEN;
		} else if (!open && BoxSystem.openState != BoxSystem.ArmState.CLOSED) {
			box.setPistonR();
			BoxSystem.openState = BoxSystem.ArmState.CLOSED;
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println("running grabber");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		System.out.println("DONE");
//		return (Robot.getControls().openArmsButton.get() && Robot.getControls().closeArmsButton.get())
//				|| (!Robot.getControls().openArmsButton.get() && !Robot.getControls().closeArmsButton.get());
		return System.currentTimeMillis() - init >= limit;
	}

	// Called once after isFinished returns true
	protected void end() {
		box.setPistonOff();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
