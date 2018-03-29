package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RatchetTest extends Command {

	SendableChooser<Boolean> ratchetChooser;
	
	public RatchetTest() {
		ratchetChooser = new SendableChooser<Boolean>();		
		ratchetChooser.addDefault("The ratchet is still being looked at", false);
		ratchetChooser.addObject("Completed ratchet check", true);
		SmartDashboard.putData(ratchetChooser);
	}
	
	@Override
	public void execute() {
		SmartDashboard.putString("Ratchet check status", "In progress");
	}
	
	@Override
	public boolean isFinished() {
		return ratchetChooser.getSelected();
	}
	
	@Override
	public void end() {
		SmartDashboard.putString("Ratchet check status", "Healthy");		
	}
	
	@Override
	public void interrupted() {
		SmartDashboard.putString("Ratchet check status", "Unhealthy");		
	}
}
