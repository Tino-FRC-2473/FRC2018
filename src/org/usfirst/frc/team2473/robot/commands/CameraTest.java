package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraTest extends Command {
	SendableChooser<Boolean> cameraCheckStatus;

	public CameraTest() {
		cameraCheckStatus = new SendableChooser<Boolean>();
		cameraCheckStatus.addDefault("Checking on camera", false);
		cameraCheckStatus.addObject("Camera test complete", true);
		SmartDashboard.putData(cameraCheckStatus);
	}

	@Override
	public boolean isFinished() {
		return cameraCheckStatus.getSelected();
	}

	@Override
	public void end() {
		SmartDashboard.putString("Networking Status", "Healthy");		
	}
	
	@Override
	public void interrupted() {
		SmartDashboard.putString("Networking Status", "Unhealthy");
	}
}