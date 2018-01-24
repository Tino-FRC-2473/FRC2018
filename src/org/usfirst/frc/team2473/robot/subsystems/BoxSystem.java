package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Devices;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2473.robot.RobotMap;

/**
 *
 */
public class BoxSystem extends Subsystem 
{
	public final double POWER = 0.6;
	int[] posArray = {1,2,3,4,5,6};
	
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
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidFChannel,RobotMap.solenoidRChannel).set(Value.kReverse);
    }
    public void setPistonOff(){
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidFChannel,RobotMap.solenoidRChannel).set(Value.kOff);
    }
    
    public void setPistonF() {
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidFChannel,RobotMap.solenoidRChannel).set(Value.kForward);
    }
    
    public int getCurPos() {
    	for(int i : posArray) {
    		//change .getTalon(i) to .getLimitSwitch(i)
    		if(Devices.getInstance().getDigitalInput(i).get())
    			return i;
    	}
    	return 0;
    }
}

