package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReadyClaw extends CommandGroup {
	public ReadyClaw() {
		if(!(Robot.getBox()).clawReadied()) {
			addParallel(new ChangeElevatorLevel(1));
			addSequential(new ReleaseBox());
			(Robot.getBox()).clawStatusReady();
		}
	}
}
