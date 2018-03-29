package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.framework.TrackingRobot.RunState;
import org.usfirst.frc.team2473.robot.Auto;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command {

	private int initEncR;
	private int initEncL;
	private double maxEncoder;
	private double targetAngle;
	private double power;

	public DriveStraight(double angle, double maxInch, double power) {
		requires(TrackingRobot.getDriveTrain());
		this.maxEncoder = Math.abs(convertInchToEncoder(maxInch));
		System.out.println("encoder: " + this.maxEncoder);
		this.power = cap(power);
		System.out.println("power: " + this.power);
		targetAngle = angle;
		SmartDashboard.putString("Drive Train Encoder Status", "Not Yet");		
	}

	private static double convertInchToEncoder(double inches) {
		return inches * RobotMap.ENC_PER_INCH;
	}

	private double cap(double power) {
		return Math.signum(power) * Math.min(Auto.MAX_POW, Math.max(Auto.MIN_POW, Math.abs(power)));
	}

	@Override
	protected void initialize() {
		if(Robot.getState() == RunState.DIAGNOSTIC) {
			SmartDashboard.putString("Drive Train Encoder Status", "Testing");
		}
		initEncR = Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0);
		initEncL = Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0);

		TrackingRobot.getDriveTrain().setTargetAngle(targetAngle);
	}

	@Override
	protected void execute() {
		TrackingRobot.getDriveTrain().drive(power, TrackingRobot.getDriveTrain().getAngleRate());
//		System.out.println("Driving straight...");
	}

	private int getAverageEnc(int enc1, int enc2) {
		return (Math.abs(enc1) + Math.abs(enc2)) / 2;
	}

	@Override
	protected boolean isFinished() {
		return getAverageEnc(initEncL - Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0),
				initEncR - Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0)) >= maxEncoder;
	}

	@Override
	protected void end() {
		if(Robot.getState() == RunState.DIAGNOSTIC) {
			SmartDashboard.putString("Drive Train Encoder Status", "Healthy");
		}
		TrackingRobot.getDriveTrain().stop();
	}

	@Override
	protected void interrupted() {
		if(Robot.getState() == RunState.DIAGNOSTIC) {
			SmartDashboard.putString("Drive Train Encoder Status", "Unhealthy");
		}
		TrackingRobot.getDriveTrain().disable();
	}
}