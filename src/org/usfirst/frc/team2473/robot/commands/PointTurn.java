package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class PointTurn extends Command {

	// TODO put maxPow in RobotMap
	private final double maxPow = 0.7;
	// The maximum angle uncertainty IN DEGREES the command will tolerate
	private final double angleTolerance = 0.5;
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
		Robot.piDriveTrain.stop();
		resetEncoders();
		Robot.piDriveTrain.setTargetAngle(targetAngle);
		System.out.println("PointTurn initiaized.");
	}

	public static void resetEncoders() {
		System.out.println("encoder reset.");
		resetOneEncoder(RobotMap.BL);
		resetOneEncoder(RobotMap.BR);
	}

	private static void resetOneEncoder(int talonId) {
		Devices.getInstance().getTalon(talonId).set(ControlMode.Position, 0);
	}

	@Override
	protected void execute() {
		System.out.println("power: " + power);
		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
	} 

	private void setRightPow(double pow) {
		Devices.getInstance().getTalon(RobotMap.FR).set(-pow);
		Devices.getInstance().getTalon(RobotMap.BR).set(-pow);
	}

	private void setLeftPow(double pow) {
		System.out.println("set left power: " + pow);
		Devices.getInstance().getTalon(RobotMap.FL).set(pow);
		Devices.getInstance().getTalon(RobotMap.BL).set(pow);
	}
	
	@Override
	protected boolean isFinished() {
		double currentAngle = Devices.getInstance().getNavXGyro().getYaw();
		System.out.print("Curr angle: " + currentAngle);
		System.out.println("Target angle: " + targetAngle);
		return Math.abs(currentAngle - targetAngle) <= angleTolerance;
	}

	@Override
	protected void end() {
		setLeftPow(0);
		setRightPow(0);
		Robot.piDriveTrain.disable();
		System.out.println("PointTurn ended.");
	}

	@Override
	protected void interrupted() {
		Robot.piDriveTrain.disable();
	}

}