package org.usfirst.frc.team2473.robot.subsystems;

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
public class BoxSystem extends Subsystem 
{
	public final double POWER = 0.6;
	int[] posArray = {1,2,3,4,5,6};
	
	DigitalInput x = new DigitalInput(0);
	
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
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidLFChannel,RobotMap.solenoidLRChannel).set(Value.kReverse);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidRFChannel,RobotMap.solenoidRRChannel).set(Value.kReverse);

    }
    public void setPistonOff(){
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidLFChannel,RobotMap.solenoidLRChannel).set(Value.kOff);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidRFChannel,RobotMap.solenoidRRChannel).set(Value.kOff);

    }
    
    public void setPistonF() {
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidLFChannel,RobotMap.solenoidLRChannel).set(Value.kForward);
    	Devices.getInstance().getDoubleSolenoid(RobotMap.solenoidRFChannel,RobotMap.solenoidRRChannel).set(Value.kForward);

    }
    
    public int getCurPos() {
    	//for(int i : posArray) {
    		//change .getTalon(i) to .getLimitSwitch(i)
    		if(x.get())
    			System.out.println("hello");
    		
    		return 1;
    	//}
    	//return 0;
    }
}

