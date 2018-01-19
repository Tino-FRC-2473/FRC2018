
package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.command.Command;
import java.io.IOException;
import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;

import org.usfirst.frc.team2473.robot.commands.Type1AutoCommand;
import org.usfirst.frc.team2473.robot.subsystems.MotorSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TrackingRobot {
	@Override protected Thread jetsonThread() throws IOException { 
		return null;
		//return new DatabaseAndPingThread("10.24.73.56", 50007);
	}
	
	@Override
	protected String getProgramName() {
		return "Architecture Testing";
	}
	
	@Override
	protected TrackableSubsystem[] tSubsystems() {
		return new TrackableSubsystem[] {
				new MotorSubsystem()
		};
	}
	
	@Override
	protected Command getAutonomousCommand() {
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
