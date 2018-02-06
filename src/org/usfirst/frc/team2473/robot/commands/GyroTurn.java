package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTurn extends Command {

	private final double MAX_POW = 0.5;
	private final double ANGLE_TOLERANCE = 5;
	private final double TARGET_ANGLE;
	private final double POWER;
	private boolean zeroYaw;
	private double difference;

	public GyroTurn(double angle, double pow) {
		requires(Robot.piDriveTrain);
		TARGET_ANGLE = angle;
		POWER = Math.signum(pow) * Math.min(MAX_POW, Math.abs(pow));
		zeroYaw = true;
	}

	public GyroTurn(double angle, double pow, boolean zeroYaw) {
		requires(Robot.piDriveTrain);
		TARGET_ANGLE = angle;
		POWER = Math.signum(pow) * Math.min(MAX_POW, Math.abs(pow));
		this.zeroYaw = zeroYaw;
	}

	@Override
	protected void initialize() {
		if (zeroYaw) Robot.zeroYawIteratively();
		System.out.println("PointTurn initialized.");
	}

	@Override
	protected void execute() {
		Robot.piDriveTrain.tankTurn(POWER, TARGET_ANGLE > 0);
		difference = Math.abs(Devices.getInstance().getNavXGyro().getYaw() - TARGET_ANGLE);
		System.out.println("Degrees remaining: " + difference);
	}

	@Override
	protected boolean isFinished() {
		return difference <= ANGLE_TOLERANCE;
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