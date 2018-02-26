package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseArms extends Command {
	private BoxSystem sub;
	
    public CloseArms() {
    	sub = (BoxSystem) Robot.getSubsystem(BoxSystem.class);
    	requires(sub);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().closeArmsButton.get()) {
    		sub.setPistonR();
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.BOX.setPistonF();
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.getControls().closeArmsButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	sub.setPistonOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
