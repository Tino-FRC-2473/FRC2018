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
    protected void initialize() {
    	if(Robot.getControls().controlButton.get()) {
    		isControl=true;
    		int currPos = sub.getCurPos();
    		System.out.println("elevator going down");
    		if(currPos!=1) {
    			sub.downPos();
    			System.out.println("elevator down");
    		}
    	}else{
    		isControl=false;
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, sub.POWER);
    		System.out.println("manual going down");
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0))<sub.POS2)
		{
			sub.setPow(0.3);
		}
    	if(Math.abs(Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0))>=sub.POS2)
		{
    		sub.setPow(0.5);		}
    	System.out.println(Devices.getInstance().getTalon(RobotMap.elevatorMotor).getSelectedSensorPosition(0));
    }
  

    // Make thios return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return (Devices.getInstance().getDigitalInput(RobotMap.eleBottomLS).get());
    }

    // Called once after isFinished returns true
    protected void end() {
    	//if(!isControl) {
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
    		System.out.println("ELEVATOR LIMIT SWITCH HIT");
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).setSelectedSensorPosition(0, 0, 10);
    //	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(!isControl) {
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
    		System.out.println("manual down stopped");

    	}
    }
}
