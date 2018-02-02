package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.Devices;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
	public final double POWER = 0.6;
	int[] posArray = {1,2,3,4};
	private int currPos = 1;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPow(double pow)
    {
    	Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, pow);
    }
     public void stopMotor() {
    	 Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
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

	public void upPos() {
		currPos++;
	/*	Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, POWER);
		while(!Devices.getInstance().getDigitalInput(currPos).get()) {
			
		}*/
		System.out.println("Elevator at POSITION: " + currPos);
		//Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
		System.out.println("auto up stopped");

	}
	
	public void downPos() {
		currPos--;
		/*Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, -POWER);
		while(!Devices.getInstance().getDigitalInput(currPos).get()) {
			
		}*/
		System.out.println("Elevator at POSITION: " + currPos);
	//	Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
		System.out.println("auto down stopped");
	}
}

