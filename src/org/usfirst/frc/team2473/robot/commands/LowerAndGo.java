package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowerAndGo extends CommandGroup {

	public LowerAndGo(double dist) {
		addSequential(new ChangeElevatorLevel(1));
		addSequential(new ToggleArms(true));
		addSequential(new Wait(100));
		addSequential(new CVDriveStraight(dist, 0.5));
		addSequential(new ToggleArms(false));
		addSequential(new Wait(100));
		addSequential(new ChangeElevatorLevel(3));		
	}
}
