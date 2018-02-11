package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;

import edu.wpi.first.wpilibj.command.Command;

public class PointTurn extends Command {

	private final double MAX_POW = 0.8;
	private final double TARGET_ANGLE;
	private double initAngleDiff;
	private final double POWER;
	private double difference;
	private double left;

	public PointTurn(double angle, double pow) {
		requires(TrackingRobot.getDriveTrain());
		TARGET_ANGLE = angle; // - Math.signum(angle)*10
//		System.out.println("Init yaw: " + Devices.getInstance().getNavXGyro().getYaw());
//		System.out.println("target angle: " + angle);
		POWER = Math.min(MAX_POW, pow);
	}

	@Override
	protected void initialize() {
//		if (zeroYaw) Robot.zeroYawIteratively();
		System.out.println("PointTurn initialized.");
		System.out.println("current angle: " + Devices.getInstance().getNavXGyro().getYaw());
		System.out.println("target angle: " + TARGET_ANGLE);
		initAngleDiff = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
		left = Math.signum(initAngleDiff) * POWER;
	}

	@Override
	protected void execute() {
//		System.out.println("Turning...");
		difference = TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw();
//		System.out.print("Current angle: " + Devices.getInstance().getNavXGyro().getYaw());
//		System.out.println(", Target Angle: " + TARGET_ANGLE);
	
		if (left > 0) {
//			System.out.println("clockwise");
		} else {
//			System.out.println("counterclockwise");
		}

		if (Math.abs(difference/initAngleDiff) <= 0.5) {
			TrackingRobot.getDriveTrain().tankTurn(Math.signum(left)*0.41);
		} else {
			TrackingRobot.getDriveTrain().tankTurn(left);
		}
	}

	@Override
	protected boolean isFinished() {
		return (TARGET_ANGLE - Devices.getInstance().getNavXGyro().getYaw()) / left < 0;
//		return difference <= ANGLE_TOLERANCE;
	}
//	
//	private double normalize(double angle) {
//		double a = angle % 360;
//		return (Math.abs(a) > 180) ? Math.signum(a) * (Math.abs(a) - 360) : a;
//	}
//	
//	double normalizeInit(double angle) {
//		System.out.println("angle difference: " + angle);
//		double a = angle % 360;
//		System.out.println("mod: " + a);		
//		return (Math.abs(a) > 180) ? Math.signum(a) * (Math.abs(a) - 360) : a;		
//	}

	@Override
	protected void end() {
		System.out.println("ENDED");
		TrackingRobot.getDriveTrain().stop();
//		Devices.getInstance().getNavXGyro().zeroYaw();
		System.out.println("current value: " + Devices.getInstance().getNavXGyro().getYaw());
	}

	@Override
	protected void interrupted() {
		TrackingRobot.getDriveTrain().disable();
	}
}