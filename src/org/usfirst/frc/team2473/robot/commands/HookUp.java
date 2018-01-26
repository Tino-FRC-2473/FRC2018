package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HookUp extends Command {
	private ClimbSystem sub;

    public HookUp() {
    	sub = (ClimbSystem) Robot.getSubsystem(ClimbSystem.class);
    	requires(sub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	sub.setArmPow(sub.power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	sub.stopArmMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
