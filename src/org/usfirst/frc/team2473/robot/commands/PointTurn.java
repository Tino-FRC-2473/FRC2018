package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PointTurn extends Command {

	// TODO put maxPow in RobotMap
	private final double maxPow = 0.7;
	// The maximum angle uncertainty IN DEGREES the command will tolerate
	private final double angleTolerance = 5;
	private double targetAngle;
	private double power;

	public PointTurn(double angle, double power) {
		requires(Robot.piDriveTrain);
		this.targetAngle = angle;
		// cap power
		if (Math.abs(power) > this.maxPow) {
			this.power = maxPow * Math.signum(power);
		} else {
			this.power = power;
		}
		System.out.println("PointTurn constructor passed.");
	}

	@Override
	protected void initialize() {
		zeroYawIteratively();
		Robot.piDriveTrain.setTargetAngle(targetAngle);
		System.out.println("PointTurn initiaized.");
	}

	@Override
	protected void execute() {
//		System.out.println("power: " + power);
		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
		
		double currentAngle = Devices.getInstance().getNavXGyro().getYaw();
		System.out.print("Curr angle: " + currentAngle);
		System.out.println("Target angle: " + targetAngle);
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
	
	private void zeroYawIteratively() {
		while (Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 5) {
			Devices.getInstance().getNavXGyro().zeroYaw();
//			System.out.println("resetting...");
		}
	}
	

}