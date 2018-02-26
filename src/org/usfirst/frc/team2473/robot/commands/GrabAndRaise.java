package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabAndRaise extends CommandGroup {
	public GrabAndRaise() {
		addSequential(new GrabBox(), .75);
		addSequential(new ChangeElevatorLevel(3));
	}
	
	@Override
	public void end() {
		Robot.drive.start();
	}
}