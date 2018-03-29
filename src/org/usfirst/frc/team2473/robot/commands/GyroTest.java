package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroTest extends Command {

	SendableChooser<Boolean> gyroChooser;
	
	public GyroTest() {
		gyroChooser = new SendableChooser<Boolean>();
		gyroChooser.addDefault("Testing", false);
		gyroChooser.addObject("Complete", true);
		SmartDashboard.putString("Gyro Status", "Not Yet");
		SmartDashboard.putData(gyroChooser);
	}	
	
	@Override
	public void execute() {
		SmartDashboard.putString("Gyro Status", "Testing...");
		SmartDashboard.putNumber("Gyro Value", (double)Devices.getInstance().getNavXGyro().getYaw());
	}
	
	@Override
	public boolean isFinished() {
		return gyroChooser.getSelected();
	}
	
	@Override
	public void end() {
		SmartDashboard.putString("Gyro Status", "Healthy");
	}
	
	@Override
	public void interrupted() {
		SmartDashboard.putString("Gyro Status", "Unhealthy");
	}
}
