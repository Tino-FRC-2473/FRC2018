package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.components.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class SimpleDriveStraight extends Command {

	// TODO put maxPow in RobotMap
	private final double maxPow = 0.7;
	private final double minPow = 0.3;
	private double maxEncoder; // The maximum encoder count at which the robot
								// stops
	private double power;

	public SimpleDriveStraight(double maxInch, double power) {
		requires(Robot.piDriveTrain);
		this.maxEncoder = convertInchToEncoder(maxInch);
		this.power = cap(power);
		System.out.println("Simple drive straight constructor passed.");
	}
	
	private double convertInchToEncoder(double feet) {
		return feet / RobotMap.INCH_OVER_ENCODER;
	}

	private double cap(double power) {
		// TODO Auto-generated method stub
		if (Math.abs(power) > this.maxPow) {
			return maxPow * Math.signum(power);
		} else if (Math.abs(power) < this.minPow){
			return minPow * Math.signum(power);
		} else {
			return power;
		}
	}

	@Override
	protected void initialize() {
		System.out.println("initialize running..");
		Robot.piDriveTrain.stop();
		resetEncoders();
		Devices.getInstance().getNavXGyro().zeroYaw();
		Robot.piDriveTrain.setTargetAngle(Devices.getInstance().getNavXGyro().getAngle());
		System.out.println("SimpleDriveStraight initialized.");
	}

	public static void resetEncoders() {
		resetOneEncoder(RobotMap.FRONT_LEFT);
		resetOneEncoder(RobotMap.FRONT_RIGHT);
	}

	private static void resetOneEncoder(int talonId) {
		Devices.getInstance().getTalon(talonId).changeControlMode(TalonControlMode.Position);
		Devices.getInstance().getTalon(talonId).setPosition(0);
		Devices.getInstance().getTalon(talonId).changeControlMode(TalonControlMode.PercentVbus);
		System.out.println("encoder reset: " + Devices.getInstance().getTalon(talonId).getPosition());
	}

	@Override
	protected void execute() {
		System.out.println("power: " + power);
		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
	}

	private void setRightPow(double pow) {
		Devices.getInstance().getTalon(RobotMap.FRONT_RIGHT).set(-pow);
		Devices.getInstance().getTalon(RobotMap.BACK_RIGHT).set(-pow);
	}

	private void setLeftPow(double pow) {
		System.out.println("set left power: " + pow);
		Devices.getInstance().getTalon(RobotMap.FRONT_LEFT).set(pow);
		Devices.getInstance().getTalon(RobotMap.BACK_LEFT).set(pow);
	}

	private double getAverageEnc(int enc1, int enc2) {
		return Math.abs(Devices.getInstance().getTalon(enc1).getPosition())
				+ Math.abs(Devices.getInstance().getTalon(enc2).getPosition()) / 2;
	}

	@Override
	protected boolean isFinished() {
		System.out.println("Current Encoder: " + this.getAverageEnc(RobotMap.FRONT_LEFT, RobotMap.FRONT_RIGHT));
		System.out.println(maxEncoder);
		return this.getAverageEnc(RobotMap.FRONT_LEFT, RobotMap.FRONT_RIGHT) >= maxEncoder;
	}

	@Override
	protected void end() {
		setLeftPow(0);
		setRightPow(0);
		Robot.piDriveTrain.disable();
		System.out.println("DriveStraight ended.");
		resetEncoders();
	}

	@Override
	protected void interrupted() {
		Robot.piDriveTrain.disable();
	}

}