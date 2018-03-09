package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Devices;


/**
 *
 */
public class EleDown extends Command {
	private BoxSystem sub;
	private boolean isControl;

	public EleDown() {
		sub = (BoxSystem) Robot.getSubsystem(BoxSystem.class);
		requires(sub);
	}

	// Called just before this Command runs the first time
	protected void initialize() 
	{
		//sub.updateCurrDownPos();
		if(Robot.getControls().controlButton.get()) {
			
			isControl=true;
			System.out.println("going down to pickup position");
			//int currPos = sub.getCurrUpPos();
			//System.out.println("Position: " + currPos + " Enc: " + Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0));
			
				Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, sub.POWER);
				while(Devices.getInstance().getDigitalInput(0).get()) {
					
				}
				sub.stopMotor();
		}else{
			isControl=false;
			Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, sub.POWER);
			System.out.println("manual going down");
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() 
	{
	//	sub.updateCurrDownPos();
		//System.out.println("At position:" + sub.getCurPos()+" enc = "+sub.getEncCount());
		/*if(!isControl && Devices.getInstance().getTalon(RobotMap.elevatorMotor).getMotorOutputPercent()!=0)
		{
			if(Math.abs(sub.getEncCount())<sub.POS1)
			{
				sub.setPow(0.3);
			}else if(Math.abs(sub.getEncCount())>=sub.POS1)
			{
				sub.setPow(0.3);		

			}
		}*/
	}


	// Make thios return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Devices.getInstance().getDigitalInput(RobotMap.eleBottomLS).get());
	}

	// Called once after isFinished returns true
	protected void end() 
	{
		if(!isControl) {
			Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
			System.out.println("ELEVATOR LIMIT SWITCH HIT");
			Devices.getInstance().getTalon(RobotMap.elevatorMotor).setSelectedSensorPosition(0, 0, 10);
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		if(!isControl) {
			Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
			System.out.println("manual down stopped");
		//	sub.updateCurrDownPos();
		}
	}
}
