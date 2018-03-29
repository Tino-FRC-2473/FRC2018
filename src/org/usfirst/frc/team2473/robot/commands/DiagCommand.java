package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DiagCommand extends CommandGroup {

	public DiagCommand() {
		/* drive train diagnostic */
		
		addSequential(new DriveStraight(0, 10, 0.5));

		addSequential(new GyroTest());

		addSequential(new RatchetTest());

		addSequential(new ArmTest());

		addSequential(new ElevatorDiagnosticTest());

		addSequential(new GrabberTest());

		addSequential(new NetworkingTest());

		addSequential(new CameraTest());		
	}
}
