package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.OI;

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
    
    public void setPow(double pow) 
    {
    	Devices.getInstance().getTalon(0).set(ControlMode.PercentOutput, pow);
    }
    
    public void setJoyPow() {
    	Devices.getInstance().getTalon(1).set(ControlMode.PercentOutput,OI.getY()*MAX_POW);
    }
}

