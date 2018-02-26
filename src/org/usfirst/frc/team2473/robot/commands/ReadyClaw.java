package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReadyClaw extends CommandGroup {
	public ReadyClaw() {
		if(!((BoxSystem)Robot.getSubsystem(BoxSystem.class)).clawReadied()) {
			addParallel(new ChangeElevatorLevel(1));
			addSequential(new ReleaseBox());
			((BoxSystem) Robot.getSubsystem(BoxSystem.class)).clawStatusReady();
		}
	}
}
