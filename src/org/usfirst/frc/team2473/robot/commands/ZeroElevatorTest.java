package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ZeroElevatorTest extends Command {

	public void initialize() {
		SmartDashboard.putString("Elevator Test Status", "Zeroing...");
		Robot.getBox().setPow(0.3);
	}
	
	@Override
	public boolean isFinished() {
		return Devices.getInstance().getDigitalInput(RobotMap.LIMIT_ZERO).get();
	}
	
	@Override
	public void end() {
		Robot.getBox().zero();
		Robot.getBox().setPow(0);
		SmartDashboard.putString("Elevator Test Status", "Zeroed");
	}

	@Override
	public void interrupted() {
		SmartDashboard.putString("Elevator Test Status", "Zero failed");		
		Robot.getBox().setPow(0);		
	}
}
