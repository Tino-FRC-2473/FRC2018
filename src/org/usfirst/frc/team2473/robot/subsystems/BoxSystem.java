package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.Devices;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2473.robot.RobotMap;

/**
 *
 */
public class BoxSystem extends TrackableSubsystem 
{
	public final double POWER = 0.2;
	public static final int POS0 = 0;
	public static final int POS1 = 1150;
	public static final int POS2 = 5600;
	public static final int POS3 = 11000;
	public int[] posArray = {POS0, POS1, POS2, POS3};
	private int currPos = 1;
	private int startPos = 0;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5); //do once at start to define encoder
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).setSelectedSensorPosition(0, 0, 10);
	}

	public void setPow(double pow)
	{
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, pow);
	}
	public void stopMotor() {
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
	}

	public void setPistonR() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kReverse);
	}
	public void setPistonOff(){
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kOff);
	}

	public void setPistonF() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kForward);
	}

	public int getCurPos() {
		return currPos;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getState() {
		return null;
	}


	public int getEncCount() {
		return Math.abs(Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0));
	}

	public void upPos(int x) {
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, -POWER);
		while(getEncCount()<posArray[x+1])
		{
			//System.out.println(getEncCount());
		}
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, 0);
		System.out.println("Elevator at POSITION: " + (x+1));
		System.out.println("Encoder value: " + getEncCount());
	}

	public void downPos(int x) {
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, +POWER);
		while(getEncCount()>posArray[x-1])
		{
			//System.out.println(getEncCount());
			if(Devices.getInstance().getDigitalInput(RobotMap.eleBottomLS).get()) 
			{
				//Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, 0);
				//System.out.println("BOTTOM ELEVATOR LIMIT SWITCH HIT");
				//Devices.getInstance().getTalon(RobotMap.elevatorMotor).setSelectedSensorPosition(0, 0, 10);
				//break;
			}
		}
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
		System.out.println("Elevator at POSITION: " + (x-1));
		System.out.println("Encoder value: " + getEncCount());
	}

	public void setCurrPos(int pos)
	{
		currPos = pos;
	}
	
	public int getCurrUpPos()
	{
		int enc = getEncCount();
		if(enc == 0) return 0;
		for(int i = 0;i<=posArray.length-1;i++)
		{
			if(enc<posArray[i]) {
				System.out.println("returned position: " + (i-1));
				return i-1;
			}
		}
		return 3;
	}
	
	public int getCurrDownPos()
	{
		int enc = getEncCount();
		if(enc == 0) return 0;
		for(int i = 0;i<=posArray.length-1;i++)
		{
			if(enc<posArray[i]) {
				System.out.println("returned position: " + (i));
				return i;
			}
		}
		return 3;	}
}

