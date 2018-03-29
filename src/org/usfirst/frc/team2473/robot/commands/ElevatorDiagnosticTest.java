package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorDiagnosticTest extends CommandGroup {

	public ElevatorDiagnosticTest() {
		addSequential(new ZeroElevatorTest());
		addSequential(new ChangeElevatorLevel(1));
		addSequential(new ChangeElevatorLevel(2));
		addSequential(new ChangeElevatorLevel(3));
		addSequential(new ChangeElevatorLevel(2));
		addSequential(new ChangeElevatorLevel(1));
		addSequential(new ChangeElevatorLevel(0));
	}

	@Override
	public void end() {
		SmartDashboard.putString("Elevator Test Status", "Healthy");
	}

	@Override
	public void interrupted() {
		SmartDashboard.putString("Elevator Test Status", "Unhealthy");
	}
}