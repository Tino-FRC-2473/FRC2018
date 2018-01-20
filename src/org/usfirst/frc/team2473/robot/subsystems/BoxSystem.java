package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.robot.Devices;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoxSystem extends Subsystem 
{
	public static final double POWER = 0.6;

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
     public void stopMotor() {
    	 Devices.getInstance().getTalon(1).stopMotor();
     }
     
    public void setPistonR() {
    	Devices.getInstance().getDoubleSolenoid(3,4).set(Value.kReverse);
    	Devices.getInstance().getDoubleSolenoid(5,6).set(Value.kReverse);
    }
    public void setPistonOff(){
    	Devices.getInstance().getDoubleSolenoid(3,4).set(Value.kOff);
    	Devices.getInstance().getDoubleSolenoid(5,6).set(Value.kOff);
    }
    
    public void setPistonF() {
    	Devices.getInstance().getDoubleSolenoid(3,4).set(Value.kForward);
    	Devices.getInstance().getDoubleSolenoid(5,6).set(Value.kForward);
    }
}

