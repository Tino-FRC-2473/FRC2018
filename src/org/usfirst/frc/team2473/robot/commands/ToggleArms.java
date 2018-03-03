package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem.ArmState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleArms extends Command {
	private BoxSystem box;
	private ArmState state;

	public ToggleArms(ArmState state) {
    	box = Robot.getBox();
    	requires(box);
    	this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().openArmsButton.get()) {
    		if (state == ArmState.OPEN)
    			box.setPistonF();
    		else 
    			box.setPistonR();
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
    	if (state == ArmState.OPEN)
    		return !Robot.getControls().openArmsButton.get();
    	else
    		return !Robot.getControls().closeArmsButton.get();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	//System.out.println("goodbye");
    	box.setPistonOff();
    	//System.out.println("goodbye");
    //	Robot.BOX.setPistonOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
