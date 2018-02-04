package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PIDTurn extends Command {

	// TODO put maxPow in RobotMap
	private final double maxPow = 0.5;
	// The maximum angle uncertainty IN DEGREES the command will tolerate
	private final double angleTolerance = 1;
	private double targetAngle;
	private double power;
	private boolean zeroYaw;

	public PIDTurn(double angle, double pow, boolean zeroYaw) {
		requires(Robot.piDriveTrain);
		targetAngle = angle;
		power = Math.min(maxPow, pow);
		
		this.zeroYaw = zeroYaw;
		
		// might need to cap
		System.out.println("PIDTurn constructor passed.");
	}

	@Override
	protected void initialize() {
		Robot.piDriveTrain.setTargetAngle(targetAngle);
		System.out.println("PIDTurn initiaized.");
	}

	@Override
	protected void execute() {
//		System.out.println("power: " + power);
		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
		
		double currentAngle = Devices.getInstance().getNavXGyro().getYaw();	
		System.out.print("PID Curr angle: " + currentAngle + " ");
		System.out.println("PID Target angle: " + targetAngle);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(Devices.getInstance().getNavXGyro()
				.getYaw() - targetAngle) <= angleTolerance;
	}

	@Override
	protected void end() {
		System.out.println("ENDED");
		
		Robot.piDriveTrain.stop();
	}

	@Override
	protected void interrupted() {
		Robot.piDriveTrain.disable();
	}

}