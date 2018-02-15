						package org.usfirst.frc.team2473.robot.subsystems;

import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.robot.Devices;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

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
	public final double POWER = 0.3;
	public static final int POS1 = 0;
	public static final int POS2 = 1000;
	public static final int POS3 = 5600;
	public static final int POS4 = 40000;
	int[] posArray = {POS1, POS2, POS3, POS4};
	private int currPos = 1;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	Devices.getInstance().getTalon(RobotMap.elevatorMotor).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5); //do once at start to define encoder
    	Devices.getInstance().getTalon(RobotMap.elevatorMotor).setSelectedSensorPosition(0, 0, 10);
    }
    
    public void setPow(double pow)
    {
    	Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, pow);
    }
     public void stopMotor() {
    	 Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
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
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, -POWER);
		while(Math.abs(getEncCount())<posArray[currPos-1]) {
			}
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
		System.out.println("Elevator at POSITION: " + currPos);
		System.out.println("Encoder value: " + getEncCount());
		
		/*while(!Devices.getInstance().getDigitalInput(currPos).get()) {
		
		}*/

	}
	
	
	private int getEncCount() {
		return Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0);
	}

	public void downPos() {
		currPos--;
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, +POWER);
		while(Math.abs(getEncCount())>posArray[currPos-1]) {
			if(Devices.getInstance().getDigitalInput(RobotMap.eleBottomLS).get()) {
				Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
				System.out.println("BOTTOM ELEVATOR LIMIT SWITCH HIT");
			}
		}
		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
		System.out.println("Elevator at POSITION: " + currPos);
		System.out.println("Encoder value: " + getEncCount());
		
		/*while(!Devices.getInstance().getDigitalInput(currPos).get()) {
		
		}*/
	}
}

