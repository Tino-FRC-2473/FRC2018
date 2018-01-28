package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSystem extends TrackableSubsystem 
{
	public static double power = 0.1;
	public static double fastPower = 0.5;
	private final double MAX_POW = 0.7;
	
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
    
    public void setJoyPow() {
    	climb.set(ControlMode.PercentOutput, Robot.getControls().getJoy().getY()*MAX_POW);
    }
    
    public void stopArmMotor() {
    	climbArm.stopMotor();
    }
    
    public void stopClimbMotor() {
    	climb.stopMotor();
    }

	@Override
	public void stop() {
		
	}

	@Override
	public String getState() {
		return "" + climb.get() + " " + climbArm.get();
	}
}

