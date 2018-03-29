package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabberTest extends CommandGroup {

	@Override
	public void initialize() {
		SmartDashboard.putString("Grabber Status", "Testing");
	}
	
	public GrabberTest() {
		addSequential(new ToggleArms(true));
		addSequential(new ToggleArms(false));
	}
	
	@Override
	public void end() {
		SmartDashboard.putString("Grabber Status", "Healthy");
	}
	
	@Override
	public void interrupted() {
		SmartDashboard.putString("Grabber Status", "Unhealthy");
	}
}
