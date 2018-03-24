package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem.PistonDir;

import edu.wpi.first.wpilibj.command.Command;

public class CPiston extends Command {
	private ClimbSystem climb;

	public CPiston() {
		climb = Robot.getClimb();
		requires(climb);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Robot.getControls().cPistonInButton.get()) {
			if (ClimbSystem.pistonState == PistonDir.OUT) {
				System.out.println("moving to in");
				climb.setPistonR();
			} else {
				climb.setPistonOff();
			}
		} else if(Robot.getControls().cPistonOutButton.get()) {
			if(ClimbSystem.pistonState == PistonDir.IN) {
				System.out.println("moving to out");
				climb.setPistonF();
			} else {
				climb.setPistonOff();
			}
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !Robot.getControls().cPistonInButton.get() && !Robot.getControls().cPistonOutButton.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		climb.setPistonOff();
		if(ClimbSystem.pistonState == PistonDir.IN) 
			ClimbSystem.pistonState = PistonDir.OUT;
		else 
			ClimbSystem.pistonState = PistonDir.IN;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		climb.setPistonOff();
		if(ClimbSystem.pistonState == PistonDir.IN) 
			ClimbSystem.pistonState = PistonDir.OUT;
		else 
			ClimbSystem.pistonState = PistonDir.IN;
	
	}
}
