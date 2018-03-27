package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;

public class PointTurn extends Command {
	public static final double SMALLER_RIGHT_TURN_POWER = 0.55;
	public static final double SMALLER_LEFT_TURN_POWER = 0.48;
	private static final double MAX_POW = 0.8;
	
	private final double TARGET_ANGLE;
	private double initAngleDiff;
	private final double POWER;
	private double difference;
	private double left;

	public PointTurn(double angle, double pow) {
		requires(TrackingRobot.getDriveTrain());
		TARGET_ANGLE = angle;
		POWER = Math.min(MAX_POW, pow);
	}

	@Override
	protected void initialize() {
//		if (zeroYaw) Robot.zeroYawIteratively();
//		Devices.getInstance().getNavXGyro().zeroYaw();
		System.out.println("PointTurn initialized.");
		System.out.println("current angle: " + Devices.getInstance().getNavXGyro().getYaw());
		System.out.println("target angle: " + TARGET_ANGLE);
		initAngleDiff = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
		left = Math.signum(initAngleDiff) * POWER;
	}

	@Override
	protected void execute() {
		difference = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
		System.out.println(difference/initAngleDiff);
	
		if(difference/initAngleDiff >= 0 && difference/initAngleDiff <= 0.5)
			if(TARGET_ANGLE >= 0)
				TrackingRobot.getDriveTrain().tankTurn(Math.signum(left)*SMALLER_RIGHT_TURN_POWER);				
			else
				TrackingRobot.getDriveTrain().tankTurn(Math.signum(left)*SMALLER_LEFT_TURN_POWER);								
		else
			TrackingRobot.getDriveTrain().tankTurn(left);
	}

	@Override
	protected boolean isFinished() {
//		return Math.abs(TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw()) <= 1.5;
		return difference/initAngleDiff <= 0.05;
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