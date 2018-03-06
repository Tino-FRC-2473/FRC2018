package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem.PistonDir;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
<<<<<<< HEAD:src/org/usfirst/frc/team2473/robot/commands/CPistonIn.java
public class CPistonIn extends Command {
	private ClimbSystem sub;
    public CPistonIn() {
    	sub = Robot.getClimbSystem();;
    	requires(sub);
       
=======
public class CPiston extends Command {
	private ClimbSystem climb;
	
	private PistonDir direction;
	
    public CPiston(PistonDir dir) {
    	climb = Robot.getClimb();
    	requires(climb);
>>>>>>> e6239dc91ca179a0cca1e0478e1a9a979dfb4f23:src/org/usfirst/frc/team2473/robot/commands/CPiston.java
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.getControls().cPistonInButton.get()) {
    		if (direction == PistonDir.IN)
    			climb.setPistonR();
    		else 
    			climb.setPistonF();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (direction == PistonDir.IN)
    		return !Robot.getControls().cPistonInButton.get();
    	else 
    		return !Robot.getControls().cPistonOutButton.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	climb.setPistonOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
