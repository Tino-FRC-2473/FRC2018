package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem.ClimbMode;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
private ClimbSystem sub;
private ClimbMode mode;
	
<<<<<<< HEAD:src/org/usfirst/frc/team2473/robot/commands/ClimbAssistUp.java
    public ClimbAssistUp() {
    	sub = Robot.getClimbSystem();
=======
    public Climb(ClimbMode mode) {
    	sub = Robot.getClimb();
>>>>>>> e6239dc91ca179a0cca1e0478e1a9a979dfb4f23:src/org/usfirst/frc/team2473/robot/commands/Climb.java
        requires(sub);
        this.mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	switch(mode) {
    	case ASSIST_DOWN:
    		sub.climbAssistDown();
    		break;
    	case ASSIST_UP:
    		sub.climbAssist();
    		break;
    	case DOWN:
    		sub.climbPrimaryReverse();
    		break;
    	case UP:
    		sub.climbPrimary();
    		break;
    	case UP_SLOW:
    		sub.climbPrimarySlow();
    		break;
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
    	sub.stopClimbMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	sub.stopClimbMotor();
    }
}
