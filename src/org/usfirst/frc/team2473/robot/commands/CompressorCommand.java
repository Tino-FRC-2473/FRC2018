package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;

public class CompressorCommand extends Command {
	
	Compressor c;

	public CompressorCommand() {
		c = new Compressor();
	}
	@Override
	public void execute() {
		if(Robot.getControls().compressorOn.get()) {
			System.out.println("compressor on");
			c.start();
		} else if(Robot.getControls().compressorOff.get()) {
			System.out.println("compressor off");
			c.stop();
		} else {
			c.free();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void interrupted() {
		c.free();
	}
}
