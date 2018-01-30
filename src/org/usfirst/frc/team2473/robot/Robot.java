package org.usfirst.frc.team2473.robot;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.command.Command;
import java.io.IOException;
import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;

import org.usfirst.frc.team2473.robot.commands.Type1AutoCommand;
import org.usfirst.frc.team2473.robot.subsystems.LineFollowerSubsystem;
import org.usfirst.frc.team2473.robot.subsystems.MotorSubsystem;

public class Robot extends TrackingRobot {
	@Override protected Thread jetsonThread() throws IOException { 
		return new DatabaseAndPingThread("10.24.73.19", 5805);
	}
	
	@Override
	protected String getProgramName() {
		return "LineFollowing Testing";
	}
	
	@Override
	protected TrackableSubsystem[] tSubsystems() {
		return new TrackableSubsystem[] {
				new MotorSubsystem(),
				new LineFollowerSubsystem()
		};
	}
	
	@Override
	protected Command getAutonomousCommand() {
		//return null;
		return new Type1AutoCommand();
	}
	
	@Override protected void innerRobotInit(){}
	@Override protected void innerAutonomousInit(){}
	
	@Override protected void innerAutonomousPeriodic() {
		Robot.logCurrentState();
	}
	
	@Override protected void innerTeleopInit(){}
	@Override protected void innerTeleopPeriodic(){}
	@Override protected void innerDisabledInit(){}
	@Override protected void innerDisabledPeriodic(){}
	@Override protected void innerTestInit(){}
	@Override protected void innerTestPeriodic(){}
}
