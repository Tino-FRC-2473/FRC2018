package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.OI;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class HookDown extends Command {

    public HookDown() {
      requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.setPow(-Robot.climber.power);
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
    	Robot.climber.setPow(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
