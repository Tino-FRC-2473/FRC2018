package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CPistonIn extends Command {
	private ClimbSystem sub;
    public CPistonIn() {
    	sub = (ClimbSystem) Robot.getSubsystem(ClimbSystem.class);
    	requires(sub);
       
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.getControls().cPistonInButton.get()) {
    		sub.setPistonR();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return !Robot.getControls().cPistonInButton.get();
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
