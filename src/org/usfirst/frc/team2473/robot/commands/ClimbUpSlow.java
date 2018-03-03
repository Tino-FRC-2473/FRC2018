package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbUpSlow extends Command {
	private ClimbSystem sub;
	
    public ClimbUpSlow() {
    	sub = Robot.getClimbSystem();
        requires(sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	sub.climbPrimarySlow();
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
    	sub.stopClimbMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	sub.stopClimbMotor();
    }
}
