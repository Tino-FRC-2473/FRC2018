package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class PointTurn extends Command {
	public static final double SMALLER_RIGHT_TURN_POWER = 0.55;
	public static final double SMALLER_LEFT_TURN_POWER = 0.48;
	private static double intercept, fin, slope; 
	private static final double THRESHOLD = 0.05;

	private final double TARGET_ANGLE;
	private double initAngleDiff;
	private double difference;

	public PointTurn(double angle) {
		requires(TrackingRobot.getDriveTrain());
		TARGET_ANGLE = angle;
	}

	@Override
	protected void initialize() {
		System.out.println("PointTurn initialized.");
		System.out.println("current angle: " + Devices.getInstance().getNavXGyro().getYaw());
		System.out.println("target angle: " + TARGET_ANGLE);
		initAngleDiff = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
//		intercept = (initAngleDiff > 0) ? 0.675 : -0.6;
//		fin = (initAngleDiff > 0) ? 0.55 : -0.48;
		intercept = (initAngleDiff > 0) ? 0.6125 : -0.55;
		fin = (initAngleDiff > 0) ? 0.55 : -0.48;

		//		intercept = (initAngleDiff > 0) ? 0.48 : -0.55;
//		fin = (initAngleDiff > 0) ? 0.4 : -0.5;
		slope = intercept - fin;
	}

	@Override
	protected void execute() {
		difference = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
		TrackingRobot.getDriveTrain().tankTurn(intercept + slope * (difference / initAngleDiff));
		System.out.println(intercept + slope * (difference / initAngleDiff));
	}

	@Override
	protected boolean isFinished() {
		return difference / initAngleDiff <= THRESHOLD;
	}

	@Override
	protected void end() {
		System.out.println("ENDED");
		TrackingRobot.getDriveTrain().stop();
		System.out.println("current value: " + Devices.getInstance().getNavXGyro().getYaw());
	}

	@Override
	protected void interrupted() {
		Robot.getDriveTrain().disable();
	}
}