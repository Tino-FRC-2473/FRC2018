package org.usfirst.frc.team2473.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;

import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	

	private DifferentialDrive differentialDrive;

	private WPI_TalonSRX talonFrontLeft;
	private WPI_TalonSRX talonBackLeft;
	private SpeedControllerGroup leftTalons;

	private WPI_TalonSRX talonFrontRight;
	private WPI_TalonSRX talonBackRight;
	private SpeedControllerGroup rightTalons;

	private DigitalInput leftDarkSensor, leftLightSensor, middleDarkSensor, middleLightSensor, rightDarkSensor,
			rightLightSensor;

	private ArrayList<DigitalInput> LightSensorList = new ArrayList<>();

	// Initialize your subsystem here
	public DriveTrain() {
		talonFrontLeft = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_LEFT);
		talonBackLeft = new WPI_TalonSRX(RobotMap.MOTOR_BACK_LEFT);
		leftTalons = new SpeedControllerGroup(talonFrontLeft, talonBackLeft);

		talonFrontRight = new WPI_TalonSRX(RobotMap.MOTOR_FRONT_RIGHT);
		talonBackRight = new WPI_TalonSRX(RobotMap.MOTOR_BACK_RIGHT);
		rightTalons = new SpeedControllerGroup(talonFrontRight, talonBackRight);

		differentialDrive = new DifferentialDrive(leftTalons, rightTalons);

		leftDarkSensor = new DigitalInput(RobotMap.LEFT_DARK_SENSOR); // dark
		leftLightSensor = new DigitalInput(RobotMap.LEFT_LIGHT_SENSOR); // light
		middleDarkSensor = new DigitalInput(RobotMap.MIDDLE_DARK_SENSOR); // dark
		middleLightSensor = new DigitalInput(RobotMap.MIDDLE_LIGHT_SENSOR); // light
		rightDarkSensor = new DigitalInput(RobotMap.RIGHT_DARK_SENSOR); // dark
		rightLightSensor = new DigitalInput(RobotMap.RIGHT_LIGHT_SENSOR); /// light

		LightSensorList.addAll(Arrays.asList(leftDarkSensor, leftLightSensor, middleDarkSensor, middleLightSensor,
				rightDarkSensor, rightLightSensor));
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean getSensorValue(int i) {
		return LightSensorList.get(i).get();
	}

	public void drive(double speed, double rotation) {
		differentialDrive.arcadeDrive(speed, rotation);
	}

	public void stop() {
		differentialDrive.arcadeDrive(0.1, 0);
		differentialDrive.arcadeDrive(0, 0);
	}
}
