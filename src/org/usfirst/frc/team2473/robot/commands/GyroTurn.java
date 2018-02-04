package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTurn extends Command {

	// TODO put maxPow in RobotMap
	private final double maxPow = 0.5;
	// The maximum angle uncertainty IN DEGREES the command will tolerate
	private final double angleTolerance = 5;
	private double targetAngle;
	private double power;
	private boolean zeroYaw;
	
	private double leftPow;
	private double rightPow;

	public GyroTurn(double angle, double pow, boolean zeroYaw) {
		requires(Robot.piDriveTrain);
		targetAngle = angle;
		power = Math.min(maxPow, pow);
		
		this.zeroYaw = zeroYaw;
		
		// might need to cap
		leftPow = Math.signum(angle) * power;
		rightPow = -leftPow;
		System.out.println("PointTurn constructor passed.");
	}

	@Override
	protected void initialize() {
		if (zeroYaw) {
			Robot.zeroYawIteratively();
		}
		System.out.println("PointTurn initiaized.");
	}

	@Override
	protected void execute() {
//		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
		double currentAngle = Devices.getInstance().getNavXGyro().getYaw();
	
		Devices.getInstance().getTalon(RobotMap.FL).set(ControlMode.PercentOutput, leftPow);
		Devices.getInstance().getTalon(RobotMap.BL).set(ControlMode.PercentOutput, leftPow);
		Devices.getInstance().getTalon(RobotMap.FR).set(ControlMode.PercentOutput, -rightPow);
		Devices.getInstance().getTalon(RobotMap.BR).set(ControlMode.PercentOutput, -rightPow);
		
		
		System.out.print("Curr angle: " + currentAngle + " ");
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

}