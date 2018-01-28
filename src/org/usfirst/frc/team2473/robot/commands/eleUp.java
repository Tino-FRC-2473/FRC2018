package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.RobotMap;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Devices;


/**
 *
 */
public class eleUp extends Command {
	private BoxSystem sub;
	private boolean b;
	
    public eleUp() {
    	sub = (BoxSystem) Robot.getSubsystem(BoxSystem.class);
    	requires(sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().controlButton.get()) {
    		b=true;
    		int currPos = sub.getCurPos();
    		System.out.println("elevator going up");
    		if(currPos!=4) {
    			sub.upPos();
    			System.out.println("elevator up");
    		}
    	}else{
    		//Devices.getInstance().getTalon(RobotMap.elevatorMotor).set(sub.POWER);
    		System.out.println("manual going up");
    		
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(!b) {
    		Devices.getInstance().getTalon(RobotMap.elevatorMotor).stopMotor();
    		System.out.println("manual up stopped");
    	}
    }
}
