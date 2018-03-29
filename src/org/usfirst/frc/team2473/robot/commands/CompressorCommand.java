package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompressorCommand extends Command {
	private Compressor c;
	SendableChooser<Boolean> compressorSwitch;

	public CompressorCommand() {
		c = new Compressor();
		compressorSwitch = new SendableChooser<Boolean>();
		compressorSwitch.addDefault("Run Compressor", true);
		compressorSwitch.addDefault("Stop Compressor", false);
		SmartDashboard.putData(compressorSwitch);
	}
	
	@Override
	public void execute() {
		if(Robot.getControls().compressorOn.get() || compressorSwitch.getSelected()) {
			c.start();
		} else if(Robot.getControls().compressorOff.get() || !compressorSwitch.getSelected()) {
			c.stop();
		} else {
			c.free();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return !compressorSwitch.getSelected();
	}

	@Override
	public void interrupted() {
		c.free();
	}
}
