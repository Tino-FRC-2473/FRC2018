package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Database;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NetworkingTest extends Command {
	SendableChooser<Boolean> networkingTestStatus;

	public NetworkingTest() {
		networkingTestStatus = new SendableChooser<Boolean>();
		networkingTestStatus.addDefault("Checking networking values", false);
		networkingTestStatus.addObject("Completed networking test", true);
		SmartDashboard.putData(networkingTestStatus);
	}

	public void initialize() {
		SmartDashboard.putString("Networking Status", "Testing");
	}

	public void execute() {
		SmartDashboard.putNumber("Reported Distance", Database.getInstance().getNumeric("dist"));
		SmartDashboard.putNumber("Reported Angle", Database.getInstance().getNumeric("ang"));
	}
	
	public boolean isFinished() {
		return networkingTestStatus.getSelected();
	}

	public void end() {
		SmartDashboard.putString("Networking Status", "Healthy");
	}

	public void interrupted() {
		SmartDashboard.putString("Networking Status", "Unhealthy");
	}
}