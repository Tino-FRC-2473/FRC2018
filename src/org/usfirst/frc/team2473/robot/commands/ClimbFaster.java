package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbFaster extends Command {

    public ClimbFaster() {
    	requires(Robot.CLIMBER);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.CLIMBER.setPow(Robot.CLIMBER.fastPower);
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
    	Robot.CLIMBER.setPow(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
