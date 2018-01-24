package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.OI;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSystem extends Subsystem 
{
	public static double power = 0.1;
	public static double fastPower = 0.5;
	private final double MAX_POW = 0.7;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmPow(double pow) 
    {
    	Devices.getInstance().getTalon(RobotMap.climbArmMotor).set(ControlMode.PercentOutput, pow);
    }
    
    public void setJoyPow() {
    	Devices.getInstance().getTalon(RobotMap.climbMotor).set(ControlMode.PercentOutput,OI.getY()*MAX_POW);
    }
    
    public void stopArmMotor() {
    	Devices.getInstance().getTalon(RobotMap.climbArmMotor).stopMotor();
    }
    
    public void stopClimbMotor() {
    	Devices.getInstance().getTalon(RobotMap.climbMotor).stopMotor();
    }
}

