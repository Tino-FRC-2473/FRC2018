package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class CVDriveStraight extends Command {

	private int initEncR, initEncL;

	// TODO put maxPow and minPow in RobotMap
	private final double maxPow = 0.7;
	private final double minPow = 0.3;
	private double maxEncoder; // The maximum encoder count at which the robot
								// stops
	private double power;

	public CVDriveStraight(double maxInch, double power) {
		requires(TrackingRobot.getDriveTrain());
		this.maxEncoder = convertInchToEncoder(maxInch);
		this.power = cap(power);
		System.out.println("POWER: " + power);
		System.out.println("Simple drive straight constructor passed.");
	}

	private static double convertInchToEncoder(double inches) {
		System.out.println("target: " + inches);
		return inches * RobotMap.ENC_PER_INCH;
	}

	private double cap(double power) {
//		return (power > maxPow) ? maxPow : (power < minPow ? minPow : power);
		return Math.min(maxPow, Math.max(minPow, power));
	}

	@Override
	protected void initialize() {
		System.out.println("initializing CVDriveStraight..");
		initEncR = Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0);
		initEncL = Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0);
		// resetEncoders();
		// Robot.zeroYawIteratively();
		Robot.drive.cancel();
		TrackingRobot.getDriveTrain().setTargetAngle(Devices.getInstance().getNavXGyro().getYaw());
		System.out.println("CVDriveStraight initialized.");
	}

	@Override
	protected void execute() {
		System.out.println(getAverageEnc(
				initEncL - Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0),
				initEncR - Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0)));
		TrackingRobot.getDriveTrain().drive(power, TrackingRobot.getDriveTrain().getAngleRate());
	}

	private int getAverageEnc(int enc1, int enc2) {
		return (Math.abs(enc1) + Math.abs(enc2)) / 2;
	}

	@Override
	protected boolean isFinished() {
		return getAverageEnc(
					initEncL - Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0),
					initEncR - Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0)
				) >= maxEncoder;
	}

	@Override
	protected void end() {
		System.out.println("drive straight ended");
		TrackingRobot.getDriveTrain().stop();
		(new GrabAndRaise()).start();
	}

	@Override
	protected void interrupted() {
		// TrackingRobot.getDriveTrain().disable();
		Robot.drive.start();
	}
}