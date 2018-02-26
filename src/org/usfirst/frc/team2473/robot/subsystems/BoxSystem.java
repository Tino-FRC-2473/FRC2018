package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class BoxSystem extends TrackableSubsystem {
	private int currLevel = 0;
	public final double POWER = 0.3;
	public static final int POS0 = 0;
	public static final int POS1 = 1150;
	public static final int POS2 = 5600;
	public static final int POS3 = 10000;
	public static final int POS4 = 11000;
	public int[] posArray = { POS0, POS1, POS2, POS3, POS4 };
	private boolean hasZeroed = false;
	private boolean clawReadyStatus = false;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void setLevel(int level) {
		currLevel = level;
	}

	public int getLevel() {
		return currLevel;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
				0, 5); // do once at start to define encoder
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).setSelectedSensorPosition(0, 0, 10);
	}

	public boolean hasZeroed() {
		return hasZeroed;
	}

	public boolean clawReadied() {
		return clawReadyStatus;
	}

	public void clawStatusReady() {
		clawReadyStatus = true;
		System.out.println("claw setting in ready position");
	}

	public void clawStatusNotReady() {
		clawReadyStatus = false;
		System.out.println("claw setting to no longer ready");
	}

	public int getNextLevel(boolean up) {
		int currLevel = getCurrPos();
		int returner = -1;
		if (up) {
			if (currLevel > 3) {
				returner = 4;
			} else {
				returner = currLevel + 1;
			}
		} else {
			if (currLevel < 2) {
				returner = 0;
			} else {
				returner = currLevel - 1;
			}
		}
		return (returner == -1) ? returner : posArray[returner];
	}

	// testing only, not for real use
	public int getNextLevel(boolean up, int currEnc) {
		int currLevel = getCurrPos(currEnc);
		int returner = -1;
		if (up) {
			if (currLevel > 3) {
				returner = 4;
			} else {
				returner = currLevel + 1;
			}
		} else {
			if (currLevel < 2) {
				returner = 0;
			} else {
				returner = currLevel - 1;
			}
		}
		return (returner == -1) ? returner : posArray[returner];
	}

	public void zero() {
		hasZeroed = true;
	}

	public void setPow(double pow) {
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).set(ControlMode.PercentOutput, pow);
	}

	public void stopMotor() {
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).stopMotor();
	}

	public double getUpPower() {
		return (getCurrPos() >= 4 || getCurrPos() < 2) ? -RobotMap.ELE_SAFE_POWER : -RobotMap.ELE_NORMAL_POWER;
	}

	public double getDownPower() {
		return (getCurrPos() < 2) ? RobotMap.ELE_SAFE_POWER : RobotMap.ELE_NORMAL_POWER;
	}

	public double getDownAutomatedPower() {
		return (getCurrPos() < 2) ? RobotMap.ELE_SAFE_POWER : RobotMap.ELE_AUTOMATED_POWER;
	}

	public double getUpAutomatedPower() {
		return (getCurrPos() >= 4 || getCurrPos() < 2) ? -RobotMap.ELE_SAFE_POWER : -RobotMap.ELE_AUTOMATED_POWER;
	}

	public void setPistonR() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
				.set(Value.kReverse);
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
				.set(Value.kReverse);
	}

	public boolean pistonInR() {
		return Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
				.get() == (Value.kReverse)
				&& Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
						.get() == Value.kReverse;
	}

	public boolean pistonInF() {
		return Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
				.get() == Value.kForward
				&& Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
						.get() == Value.kForward;
	}

	public void setPistonOff() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
				.set(Value.kOff);
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
				.set(Value.kOff);
	}

	public void setPistonF() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
				.set(Value.kForward);
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
				.set(Value.kForward);
	}

	public void setRightClosed() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel, RobotMap.solenoidBCLRChannel)
		.set(Value.kReverse);		
	}
	
	public void setLeftClosed() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel, RobotMap.solenoidBCRRChannel)
		.set(Value.kReverse);
	}

	public boolean limitDown() {
		return Devices.getInstance().getDigitalInput(RobotMap.eleBottomLS).get();
	}

	public boolean limitUp() {
		return Devices.getInstance().getDigitalInput(RobotMap.eleTopLS).get();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		setPow(0);
	}

	@Override
	public String getState() {
		return null;
	}

	public int getEncCount() {
		return Math.abs(Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).getSelectedSensorPosition(0));
	}

	public void resetEnc() {
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).setSelectedSensorPosition(0, 0, 5);
	}

	public int getCurrPos() {
		int enc = getEncCount();
		if (enc <= 50)
			return 0;
		for (int i = 1; i < posArray.length; i++) {
			if (withinRange(enc, posArray[i], posArray[i - 1]))
				return i;
		}
		return 4;
	}

	// testing only, not for real use
	public int getCurrPos(int enc) {
		if (enc == 0)
			return 0;
		for (int i = 1; i < posArray.length; i++) {
			if (withinRange(enc, posArray[i], posArray[i - 1]))
				return i;
		}
		return 4;
	}

	public boolean withinRange(int num, int up, int down) {
		return num <= up && num >= down;
	}

}
