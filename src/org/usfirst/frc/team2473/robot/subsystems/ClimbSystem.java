package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Devices;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSystem extends Subsystem 
{
	public static double power = 0.1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPow(double pow) 
    {
    	Devices.getInstance().getTalon(0).set(pow);
    	Devices.getInstance().getTalon(1).set(pow);
    }
    
    public void changePower(double YShift)
    {
    	power += power*YShift;
    }
}

