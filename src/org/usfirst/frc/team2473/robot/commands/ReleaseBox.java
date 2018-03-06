package org.usfirst.frc.team2473.robot.commands;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseBox extends Command {

	BoxSystem boxSystem;
	long start;

	public ReleaseBox() {
		boxSystem = Robot.getBox();
	}
	
	@Override
	public void initialize() {
		start = System.currentTimeMillis();
		if(!boxSystem.pistonInF()) boxSystem.setPistonF();
	}

	@Override
	public void execute() {

	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis() - start >= BoxSystem.RELEASE_BOX_TIME;
	}

	@Override
	public void end() {
		boxSystem.setPistonOff();
	}
}
