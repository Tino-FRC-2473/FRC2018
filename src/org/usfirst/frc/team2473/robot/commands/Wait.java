package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
	private long delay;
	private long startTime;
	
	public Wait(long d) {
		delay = d;
	}
	
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
	}
	
	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - startTime > delay;
	}

}
