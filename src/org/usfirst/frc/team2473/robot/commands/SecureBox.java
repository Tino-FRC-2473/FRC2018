package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SecureBox extends CommandGroup {
	public SecureBox() {
		addSequential(new GrabBox(), 1000);
		addSequential(new ChangeElevatorLevel(3));
	}
}
