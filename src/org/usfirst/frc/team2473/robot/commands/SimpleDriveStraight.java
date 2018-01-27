package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SimpleDriveStraight extends Command {

	// TODO put maxPow in RobotMap
	private int r_init, l_init;
	
	private final double maxPow = 0.7;
	private final double minPow = 0.3;
	private double maxEncoder; // The maximum encoder count at which the robot
								// stops
	private double power;

	public SimpleDriveStraight(double maxInch, double power) {
		requires(Robot.piDriveTrain);
		this.maxEncoder = convertInchToEncoder(maxInch);
		this.power = cap(power);
		System.out.println("POWER: " + power);
		System.out.println("Simple drive straight constructor passed.");
	}

	private double convertInchToEncoder(double inches) {
		return inches * RobotMap.ENC_PER_INCH;
	}

	private double cap(double power) {
		return (power > maxPow) ? maxPow : (power < minPow ? minPow : power);
	}

	@Override
	protected void initialize() {
		System.out.println("initialize running..");
		r_init = Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0);
		l_init = Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0);
// 		resetEncoders();
		Robot.piDriveTrain.setTargetAngle(Devices.getInstance().getNavXGyro().getYaw());
		System.out.println("SimpleDriveStraight initialized.");
	}

	public static void resetEncoders() {
		resetOneEncoder(RobotMap.BL);
		resetOneEncoder(RobotMap.BR);
		System.out.println("Encoders reset.");
	}

	private static void resetOneEncoder(int talonId) {
		Devices.getInstance().getTalon(talonId).setSelectedSensorPosition(0, 0, 5);
		System.out.println(
				"Updated " + talonId + " " + Devices.getInstance().getTalon(talonId).getSelectedSensorPosition(0));
	}

	@Override
	protected void execute() {
		System.out.println("Angle: " + Devices.getInstance().getNavXGyro().getYaw());
		Robot.piDriveTrain.drive(power, Robot.piDriveTrain.getAngleRate());
	}

	private int getAverageEnc(int enc1, int enc2) {
		return (Math.abs(enc1) + Math.abs(enc2)) / 2;
	}

	@Override
	protected boolean isFinished() {
		return getAverageEnc(l_init - Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0),
				r_init - Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0)) >= maxEncoder;
	}

	@Override
	protected void end() {
		Robot.piDriveTrain.stop();
	}

	@Override
	protected void interrupted() {
		Robot.piDriveTrain.disable();
	}
}