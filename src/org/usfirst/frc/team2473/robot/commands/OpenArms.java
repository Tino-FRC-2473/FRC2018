package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenArms extends Command {
	private BoxSystem sub;

	public OpenArms() {
    	sub = Robot.getBoxSystem();
    	requires(sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().openArmsButton.get()) {
    		sub.setPistonF();
    	} 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if(Robot.OI.OpenArmsButton.get()) {
    	//	Robot.BOX.setPistonR();
    		//System.out.println("hello");
    	//}
    	//System.out.println("hello");
    	//Robot.BOX.setPistonR();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.getControls().openArmsButton.get();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("goodbye");
    	sub.setPistonOff();
    	//System.out.println("goodbye");
    //	Robot.BOX.setPistonOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
