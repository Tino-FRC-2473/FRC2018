package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {

	private int initEncR;
	private int initEncL;
	private double maxEncoder;
	private double targetAngle;
	private double power;

	public DriveStraight(double angle, double maxInch, double power) {
		requires(TrackingRobot.getDriveTrain());
		this.maxEncoder = convertInchToEncoder(maxInch);
		this.power = cap(power);
		targetAngle = angle;
	}

	private static double convertInchToEncoder(double inches) {
		return inches * RobotMap.ENC_PER_INCH;
	}

	private double cap(double power) {
		return Math.min(Auto.MAX_POW, Math.max(Auto.MIN_POW, power));
	}

	@Override
	protected void initialize() {
		initEncR = Devices.getInstance().getTalon(RobotMap.BR)
				.getSelectedSensorPosition(0);
		initEncL = Devices.getInstance().getTalon(RobotMap.BL)
				.getSelectedSensorPosition(0);
		
		TrackingRobot.getDriveTrain().setTargetAngle(targetAngle);
	}

	@Override
	protected void execute() {
		TrackingRobot.getDriveTrain().drive(power, TrackingRobot.getDriveTrain().getAngleRate());
	}

	private int getAverageEnc(int enc1, int enc2) {
		return (Math.abs(enc1) + Math.abs(enc2)) / 2;
	}

	@Override
	protected boolean isFinished() {
		return getAverageEnc(initEncL - Devices.getInstance().getTalon(RobotMap.BL)
				.getSelectedSensorPosition(0),
				initEncR - Devices.getInstance().getTalon(RobotMap.BR)
				.getSelectedSensorPosition(0)) >= maxEncoder;
	}

	@Override
	protected void end() {
		TrackingRobot.getDriveTrain().stop();
	}

	@Override
	protected void interrupted() {
		TrackingRobot.getDriveTrain().disable();
	}
}