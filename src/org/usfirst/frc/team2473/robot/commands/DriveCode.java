package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.OP;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCode extends Command {

	public DriveCode() {
//		requires(Robot.piDriveTrain);
	}
	
	@Override
	protected void initialize() {
		Devices.getInstance().getNavXGyro().zeroYaw();
	}
	
	@Override
	protected void execute() {
		double turn = OP.wheel.getX();
		double throttle = -OP.throttle.getZ();
		
		Robot.piDriveTrain.drive(throttle, Math.signum(turn) * Math.sqrt(Math.abs(turn)));
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
