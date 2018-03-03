package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class PreSwitchBox extends Command {

	double initEnc;
	BoxSystem boxSystem;

	public PreSwitchBox() {
		boxSystem = Robot.getBoxSystem();;
	}
	
	@Override
	public void initialize() {
		initEnc = boxSystem.getEncCount();
	}

	@Override
	public void execute() {
		boxSystem.setPow(-0.4);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(initEnc - boxSystem.getEncCount()) >= BoxSystem.POS2;
	}

	@Override
	public void end() {
		boxSystem.setPow(0);
	}
}
