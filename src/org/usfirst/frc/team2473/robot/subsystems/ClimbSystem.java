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
	public final double ARMPOWUP1 = -0.3;
	public final double ARMPOWUP2 = -0.25;
	public final double ARMPOWDOWN1 = 0.25;
	public final double ARMPOWDOWN2 = 0.1;
	public final double WINCH1POW = 0.9;
	public final double WINCH2POW = 0.65;
	public final double ENCCOUNT1 = 638;
	public final double ENCCOUNT2 = 2000;
	public final double ENCCOUNT3 = 1950;
	public final double ENCCOUNT4 = 2313;
	public final double JUMPPOW1 = 0.2;
	public final double JUMPPOW2 = -0.1;
	//2052
    
	public ClimbSystem() {

	}
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmPow() 
    {
    	//Devices.getInstance().getTalon(RobotMap.climbArmMotor).set(ControlMode.PercentOutput, ARMPOWUP);
    }
    
    public void stopArmMotor() {
    	Devices.getInstance().getTalon(RobotMap.climbArmMotor).stopMotor();
    }
 
    
    public void climbUp1() 
    {
    	Devices.getInstance().getTalon(RobotMap.climbMotorR).set(ControlMode.PercentOutput, WINCH1POW);
    	Devices.getInstance().getTalon(RobotMap.climbMotorR2).set(ControlMode.PercentOutput, WINCH1POW);
    	System.out.println("climbing up");
    }
    
    public void climbUp2() {
    	Devices.getInstance().getTalon(RobotMap.climbMotorL).set(ControlMode.PercentOutput, -WINCH2POW);
    	Devices.getInstance().getTalon(RobotMap.climbMotorL2).set(ControlMode.PercentOutput, -WINCH2POW);

    }
   
    public void stopClimbMotor() {
    	Devices.getInstance().getTalon(RobotMap.climbMotorR).stopMotor();
    	Devices.getInstance().getTalon(RobotMap.climbMotorL2).stopMotor();
    	Devices.getInstance().getTalon(RobotMap.climbMotorR2).stopMotor();
    	Devices.getInstance().getTalon(RobotMap.climbMotorL).stopMotor();
    	System.out.println("stopped");
    }

	@Override
	public void stop() 
	{
		
	}

	@Override
	public String getState() {
		return "" + Devices.getInstance().getTalon(RobotMap.climbMotorL).get() + " " +
					Devices.getInstance().getTalon(RobotMap.climbMotorR).get() + " " +
					Devices.getInstance().getTalon(RobotMap.climbArmMotor).get();
	}
	public void setPistonR() {
		Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidClimbF,RobotMap.solenoidClimbR).set(Value.kReverse);

    }
    public void setPistonOff(){
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidClimbF,RobotMap.solenoidClimbR).set(Value.kOff);

    }
    
    public void setPistonF() {
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidClimbF,RobotMap.solenoidClimbR).set(Value.kForward);

    }
}

