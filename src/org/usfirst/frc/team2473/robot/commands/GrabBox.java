package org.usfirst.frc.team2473.robot.commands;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class GrabBox extends Command {

	BoxSystem boxSystem;
	long start;

	public GrabBox() {
		boxSystem = Robot.getBox();
	}
	
	@Override
	public void initialize() {
		start = System.currentTimeMillis();
		if(!boxSystem.pistonInR()) {
			System.out.println("closing box");
			boxSystem.setPistonR();
		}
		System.out.println("running grab box...");
	}

	@Override
	public void execute() {
		System.out.println(boxSystem.pistonInR());
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void end() {
		boxSystem.setPistonOff();
		System.out.println("command ended...");
	}
}
