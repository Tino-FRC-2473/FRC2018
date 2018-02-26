
package org.usfirst.frc.team2473.framework;

import java.io.IOException;
import java.util.stream.IntStream;

import org.usfirst.frc.team2473.robot.Controls;
import org.usfirst.frc.team2473.robot.subsystems.PIDriveTrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * TrackingRobot provides a convenient intermediary between Robot and the TimedRobot framework.
 *
 * <p>The TrackingRobot class is intended to be subclassed by a user creating a robot program.
 */
public abstract class TrackingRobot extends IterativeRobot {
	private static PIDriveTrain driveTrain;
	private static TrackableSubsystem[] subsystems; //array of TrackableSubsystems used in this robot program
	private static Controls controls; //contains all button mapping and joystick controls
	private Thread jetsonThread; //thread for communcation with the jetson
	private Command autoCmd; //command to be run during auto
	
	/**
	 * This method must include the Thread used for networking and database-ing, or null if not used.
	 * @return Such a Thread.
	 * @throws IOException
	 */
	protected abstract Thread jetsonThread() throws IOException;
	
	/**
	 * Printed to console in robotInit. This allows someone to know what code is currently on the roboRIO.
	 * @return The name of the program.
	 */
	protected abstract String getProgramName();
	
	/**
	 * This method must include the PIDSubsystem, usually a drivetrain, that the program uses,
	 * or null if there is none.
	 * @return The PIDSubsystem
	 */
	protected abstract PIDriveTrain driveTrain();
	
	/**
	 * This method must include all TrackableSubsystems used in this Robot program.
	 * @return The array of TrackableSubsystems.
	 * @return null if there are no subsystems to add
	 */
	protected abstract TrackableSubsystem[] tSubsystems();
	
	/**
	 * The method must include the Command to be run during autonomous, or null if no autonomous command is used.
	 * @return Such a Command.
	 */
	protected abstract Command getAutonomousCommand();
	
	/** Anything to be run at the end of each call of robotInit */
	protected void innerRobotInit(){}
	/** Anything to be run at the end of each call of autoInit */
	protected void innerAutonomousInit(){}
	/** Anything to be run at the end of each call of autoPeriodic */
	protected void innerAutonomousPeriodic(){}
	/** Anything to be run at the end of each call of teleopInit */
	protected void innerTeleopInit(){}
	/** Anything to be run at the end of each call of teleopPeriodic */
	protected void innerTeleopPeriodic(){}
	/** Anything to be run at the end of each call of disabledInit */
	protected void innerDisabledInit(){}
	/** Anything to be run at the end of each call of disabledPeriodic */
	protected void innerDisabledPeriodic(){}
	/** Anything to be run at the end of each call of testInit */
	protected void innerTestInit(){}
	/** Anything to be run at the end of each call of testPeriodic */
	protected void innerTestPeriodic(){}
	
	/**
	 * Prints to the console each TrackableSubsystem's current state as overridden by the getState() method.
	 */
	public static void logCurrentState() {
		if(driveTrain != null)
			System.out.println("\tPosition: " + driveTrain.getPosition() + ", Setpoint: " + driveTrain.getSetpoint());
		if(subsystems != null) 
			for(TrackableSubsystem i : subsystems) 
				System.out.println("\t" + i.getClass().getSimpleName() + ": " + i.getState());
	}
	
	/**
	 * 
	 * @return
	 */
	public static PIDriveTrain getDriveTrain() {
		return driveTrain;
	}
	
	/**
	 * Returns the TrackableSubsystem matching the parameter class. This TrackableSubsystem must have been initialized
	 * by overriding the getTSubsystems() method. Returns null if no matching TrackableSubsystem was found.
	 * EX: To obtain a MotorSubsystem, pass in parameter MotorSubsystem.class
	 * @param cls Class type of the TrackableSubsystem to be returned.
	 * @return The TrackableSubsystem indicated by the parameter class.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TrackableSubsystem> T getSubsystem(Class<T> cls) {
		for(int i = 0; i < subsystems.length; i++) {
			if(subsystems[i].getClass().equals(cls)) {
				return (T)subsystems[i];
			}
		}
		System.out.println("ERROR: SUBSYSTEM CLASS " + cls.getSimpleName() + " NOT FOUND");
		return null;
	}
	
	/**
	 * Returns an instance of the controls class, which handles joystick and button information
	 * @return Such an instance
	 */
	public static Controls getControls() {
		return controls;
	}
	
	/**
	 * Initializes the jetson thread, all the TrackableSubsystems, and the autonomous command. Prints to the
	 * console what is being run. Starts the jetson thread.
	 */
	@Override
	public void robotInit() {
		System.out.println("[robot init - start]");
		try {
			driveTrain = driveTrain();
			subsystems = tSubsystems();
			autoCmd = getAutonomousCommand();
			jetsonThread = jetsonThread();
			controls = new Controls();
			
			System.out.println("Running: " + getProgramName());
			System.out.println("Autonomous: " + ((autoCmd==null) ? "None" : getAutonomousCommand().getClass().getSimpleName()));
			System.out.println("\tJetson Networking: " + (jetsonThread != null));
			IntStream.range(0, 44).forEach(e -> System.out.print("*")); //tribute to pramukh naduthota
			System.out.println("");
			
			
			
		} catch (Exception e) {
			System.out.println("ERROR IN TRACKINGROBOT STARTUP:");
			System.out.println("GONNA DO IT AGAIN LMFAO");
			e.printStackTrace();
			
			try {
				jetsonThread = jetsonThread();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(jetsonThread != null) jetsonThread.start();
		
		innerRobotInit();
		
		System.out.println("[robot init - end]");
	}
	
	/**
	 * This function is called once when the robot enters a disabled state
	 */
	@Override
	public void disabledInit() {
		System.out.println("[disabled init]");
		logCurrentState();
		innerDisabledInit();
	}
	/**
	 * This function is called periodically when the robot is in a disable state
	 */
	@Override
	public void disabledPeriodic() {
		for(WPI_TalonSRX talon : Devices.getInstance().getTalons()) talon.set(0);
		Scheduler.getInstance().run();
		innerDisabledPeriodic();
	}
	/**
	 * This function is called once when autonomous starts
	 */
	@Override
	public void autonomousInit() {
		System.out.println("[auto init]");
		innerAutonomousInit();
		if (autoCmd != null) autoCmd.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		innerAutonomousPeriodic();
	}
	/**
	 * This function is called once when operator control starts
	 */
	@Override
	public void teleopInit() {
		System.out.println("[teleop init]");
		if (autoCmd != null) {
			autoCmd.cancel();
		}
		innerTeleopInit();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		innerTeleopPeriodic();
	}
	/**
	 * This function is called once when test mode starts
	 */
	@Override
	public void testInit() {
		System.out.println("[test init]");
		innerTestInit();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
		innerTestPeriodic();
	}
}
