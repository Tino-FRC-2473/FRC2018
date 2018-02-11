package org.usfirst.frc.team2473.robot;
import java.io.IOException;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.RobotMap.Route;
import org.usfirst.frc.team2473.robot.commands.AutonomousRoute;
import org.usfirst.frc.team2473.robot.subsystems.PIDriveTrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TrackingRobot {
	
	
	private Preferences pref;
	public static OI oi;
	
	public static boolean isNetwork = false;
	
	private SendableChooser<Route> chooser;

	CommandGroup autonomousCommand;
	
	@Override
	protected void innerRobotInit() {
		initChooser();
		initSensors();
		TrackingRobot.getDriveTrain().enable();
		// TODO this is here only for testing purposes. Prob should be in auto init
	}
	
	@Override
	protected void innerAutonomousInit() {
		zeroYawWithDelay();
		
		// Handle delay
		double delay = pref.getDouble("delay", 0);
		double origTime = System.currentTimeMillis() / 1000;
		while (System.currentTimeMillis() / 1000 < (delay + origTime));
		System.out.println("Delay passed");
		
		// Set up Autonomous command using route from chooser and
		// switch side from DriverStation
		Scheduler.getInstance().removeAll();
		System.out.println("Scheduler cleared...");
		((AutonomousRoute) autonomousCommand).configure((DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R'),
				chooser.getSelected());
		autonomousCommand.start();
//		new PointTurn(90, 0.5).start();		
	}
	
	protected void innerTeleopInit() {
		TrackingRobot.getDriveTrain().enable();
		System.out.println(Devices.getInstance().getNavXGyro().getYaw());
	}
	
	protected void innerDisabledInit() {
		Scheduler.getInstance().removeAll();
//		TrackingRobot.getDriveTrain().disable();
	}
	
	private void initChooser() {
		System.out.println("Initializing chooser...");
		chooser = new SendableChooser<Route>();
		chooser.addObject("Left", Route.LEFT);
		chooser.addObject("Right", Route.RIGHT);
		chooser.addObject("Center", Route.CENTER);
		chooser.addObject("Drivestraight", Route.DRIVESTRAIGHT);
//		chooser.addObject("LEFT_CENTER_DriveStraight", Route.LEFT_CENTER);
//		chooser.addObject("RIGHT_CENTER_DriveStraight", Route.RIGHT_CENTER);
		chooser.addDefault("Testing", Route.TESTING);
		SmartDashboard.putData("AutoChooser", chooser);
		Robot.addDevices();
		pref = Preferences.getInstance();
	}
	
	private void initSensors() {
		// NOTE that the last parameter of configSelectedFeedback should be nonzero, as
		// a delay is required.
		Devices.getInstance().getTalon(RobotMap.BL)
		.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		
		Devices.getInstance().getTalon(RobotMap.BR)
		.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		
		Devices.getInstance().getTalon(RobotMap.BL).setSelectedSensorPosition(0, 0, 5);
		Devices.getInstance().getTalon(RobotMap.BR).setSelectedSensorPosition(0, 0, 5);
	}
	
	public int avg(int a, int b) {
		return (Math.abs(a) + Math.abs(b))/2;
	}

	private static void addDevices(){
		Devices.getInstance().addTalon(RobotMap.BR);
		Devices.getInstance().addTalon(RobotMap.BL);
		Devices.getInstance().addTalon(RobotMap.FL);
		Devices.getInstance().addTalon(RobotMap.FR);
		Devices.getInstance().setNavXGyro();
	}

	@Override
	protected Thread jetsonThread() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("!! Jetson not integrated.");
		return null;
	}

	@Override
	protected String getProgramName() {
		return "Autonomous";
	}

	@Override
	protected TrackableSubsystem[] tSubsystems() {
		// TODO Auto-generated method stub
		System.out.println("!! Mechanisms not integrated.");
		return null;
	}

	@Override
	protected Command getAutonomousCommand() {
		if (autonomousCommand == null) {
			autonomousCommand = new AutonomousRoute();
		}
		return autonomousCommand;
	}

	@Override
	protected PIDriveTrain driveTrain() {
		return new PIDriveTrain();
	}
	
	// For shorthand
	public static double getYaw() {
		return Devices.getInstance().getNavXGyro().getYaw();
	}
	
	private void zeroYawWithDelay() {
		Devices.getInstance().getNavXGyro().zeroYaw();
		System.out.println("Zeroing yaw...");
		while (Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 1);
		System.out.println("Yaw zeroed.");
	}
}