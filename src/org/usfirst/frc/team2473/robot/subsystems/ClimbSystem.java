package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSystem extends TrackableSubsystem 
{
	public static double power = 0.1;
	public static double fastPower = 0.5;
	private final double MAX_POW = 0.7;
	private static double climbPow = 0.2;
	
	private WPI_TalonSRX climbArm;
	private WPI_TalonSRX climb;
    
	public ClimbSystem() {
		climbArm = Devices.getInstance().getTalon(RobotMap.climbArmMotor);
		climb = Devices.getInstance().getTalon(RobotMap.climbMotor);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmPow(double pow) 
    {
    	climbArm.set(ControlMode.PercentOutput, pow);
    }
    
    public void stopArmMotor() {
    	climbArm.stopMotor();
    }
 
    
    public void climbUp() 
    {
    	climb.set(ControlMode.PercentOutput, climbPow);
    }
    
    
    public void climbDown() 
    {
    	climb.set(ControlMode.PercentOutput, -climbPow);
    }
    
   
    public void stopClimbMotor() {
    	climb.stopMotor();
    }

	@Override
	public void stop() 
	{
		
	}

	@Override
	public String getState() {
		return "" + climb.get() + " " + climbArm.get();
	}
	public void setPistonR() {
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kReverse);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel,RobotMap.solenoidBCRRChannel).set(Value.kReverse);

    }
    public void setPistonOff(){
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kOff);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel,RobotMap.solenoidBCRRChannel).set(Value.kOff);

    }
    
    public void setPistonF() {
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCLFChannel,RobotMap.solenoidBCLRChannel).set(Value.kForward);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidBCRFChannel,RobotMap.solenoidBCRRChannel).set(Value.kForward);

    }
}

