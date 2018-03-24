package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowerAndGo extends CommandGroup {

	public LowerAndGo(double dist) {
		addSequential(new ChangeElevatorLevel2(1));
		addSequential(new Wait(100));
		addSequential(new CVDriveStraight(dist, 0.5));
	}
}
