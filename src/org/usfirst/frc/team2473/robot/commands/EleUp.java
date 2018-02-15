package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Devices;


/**
 *
 */
public class EleUp extends Command {
	private BoxSystem sub;
	private boolean isControl;
	
    public EleUp() {
    	sub = (BoxSystem) Robot.getSubsystem(BoxSystem.class);
    	requires(sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().controlButton.get()) {
    		isControl=true;
    		int currPos = sub.getCurPos();
    		System.out.println("elevator going up");
    		if(currPos!=4) {
    			sub.upPos();
    			System.out.println("elevator up");
    		}
    	}else{
    		isControl=false;
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(ControlMode.PercentOutput, -sub.POWER);
    		System.out.println("manual going up");
    		
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
      //  return Devices.getInstance().getDigitalInput(RobotMap.eleTopLS).get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(!isControl) {
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
    		System.out.println("ELEVATOR LIMIT SWITCH HIT");
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(!isControl) {
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
    		System.out.println("manual up stopped");
    	}
    }
}
