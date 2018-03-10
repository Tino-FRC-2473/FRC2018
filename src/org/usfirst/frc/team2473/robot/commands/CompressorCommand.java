package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;

public class CompressorCommand extends Command {

	Compressor c = new Compressor();
	
	@Override
	public void execute() {
		c.start();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void end() {
		c.stop();
	}
	
	@Override
	public void interrupted() {
		c.stop();
	}
}
