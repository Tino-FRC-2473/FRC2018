package org.usfirst.frc.team2473.robot;

import java.io.IOException;

import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto.Route;
import org.usfirst.frc.team2473.robot.commands.AutonomousRoute;
import org.usfirst.frc.team2473.robot.commands.CVCommand;
import org.usfirst.frc.team2473.robot.commands.ClawCommand;
import org.usfirst.frc.team2473.robot.commands.DriveCommand;
import org.usfirst.frc.team2473.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.PIDriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TrackingRobot {
	
	//CV TELEOP STUFF
	public CVCommand cvDrive;
	public static DriveCommand drive;

	//autonomous command-based stuff
	private CommandGroup autonomousCommand;
	private SendableChooser<Route> chooser;
	private Preferences pref;

	//robot modes
	public static boolean isNetwork = true;

	@Override
	protected Thread jetsonThread() throws IOException {
		return (isNetwork) ? new DatabaseAndPingThread(RobotMap.IP, RobotMap.PORT, false) : null;
	}

	@Override
	protected void innerRobotInit() {		
		//autonomous calibration
		this.initChooser();
		this.initSensors();

		/* teleop command creation */
		cvDrive = new CVCommand();
		drive = new DriveCommand();

		TrackingRobot.getDriveTrain().enable();
		// TODO this is here only for testing purposes. Prob should be in auto init

		 UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture("Camera 0", 0);
		 camera0.setBrightness(0);
		 camera0.setResolution(160, 120);
		
//		 UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Camera 1", 1);
//		 camera1.setBrightness(0);
//		 camera1.setResolution(160, 120);
	}

	@Override
	protected void innerAutonomousInit() {
		this.zeroYawIteratively();

		// Handle delay
		double delay = pref.getDouble("delay", 0);
		double origTime = System.currentTimeMillis() / 1000;
		while(System.currentTimeMillis() / 1000 < (delay + origTime))
			;
		SmartDashboard.putString("Delay Status", "Delay passed");
		
		System.out.println("Scheduler cleared...");
		((AutonomousRoute) autonomousCommand).configure(
				(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R'), chooser.getSelected()
		);
//		autonomousCommand.start(); //already done in trackingrobot
	}

	protected void innerTeleopInit() {
		Devices.getInstance().getTalon(11).set(ControlMode.PercentOutput, 0.2);
	/*	(new ElevatorCommand()).start();
		(new ClawCommand()).start();
		cvDrive.start();*/
	}

	protected void innerTeleopPeriodic() {
		System.out.println(Devices.getInstance().getDigitalInput(0).get());
		//((BoxSystem) getSubsystem(BoxSystem.class)).setLevel(((BoxSystem) getSubsystem(BoxSystem.class)).getCurrPos());
//		System.out.println("current level: " + ((BoxSystem) getSubsystem(BoxSystem.class)).getLevel());
	}

	protected void innerDisabledInit() {
	}
	
	@Override
	protected void innerDisabledPeriodic() {

	}

	private void initChooser() {
		System.out.println("Initializing chooser...");
		chooser = new SendableChooser<Route>();
		chooser.addObject("Left", Route.LEFT);
		chooser.addObject("Right", Route.RIGHT);
		chooser.addObject("Center", Route.CENTER);
		chooser.addDefault("Testing", Route.TESTING);
		SmartDashboard.putData("AutoChooser", chooser);
		Robot.addDevices();
		pref = Preferences.getInstance();
	}

	private void initSensors() {
		// NOTE that the last parameter of configSelectedFeedback should be nonzero, as
		// a delay is required.
		zeroYawIteratively();
		Devices.getInstance().getTalon(RobotMap.BL).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);

		Devices.getInstance().getTalon(RobotMap.BR).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
				0, 5);

		Devices.getInstance().getTalon(RobotMap.BL).setSelectedSensorPosition(0, 0, 5);
		Devices.getInstance().getTalon(RobotMap.BR).setSelectedSensorPosition(0, 0, 5);
	}

	private static void addDevices() {
		Devices.getInstance().addTalon(RobotMap.BR);
		Devices.getInstance().addTalon(RobotMap.BL);
		Devices.getInstance().addTalon(RobotMap.FL);
		Devices.getInstance().addTalon(RobotMap.FR);
		Devices.getInstance().setNavXGyro();
	}

	@Override
	protected String getProgramName() {
		return "Drive Code, Working on Autonomous";
	}

	@Override
	protected TrackableSubsystem[] tSubsystems() {
		return new TrackableSubsystem[] { new ClimbSystem(), new BoxSystem() };
	}

	@Override
	protected Command getAutonomousCommand() {
		if(autonomousCommand == null)
			autonomousCommand = new AutonomousRoute();
		return autonomousCommand;
	}

	@Override
	protected PIDriveTrain driveTrain() {
		return new PIDriveTrain();
	}

	//UNUSED, REMOVE?
//	public static double getYaw() {
//		return Devices.getInstance().getNavXGyro().getYaw();
//	}
	
	public static ClimbSystem getClimb() {
		return (ClimbSystem) Robot.getSubsystem(ClimbSystem.class);
	}
	
	public static BoxSystem getBox() {
		return (BoxSystem) Robot.getSubsystem(BoxSystem.class);
	}

	private void zeroYawIteratively() {
		Devices.getInstance().getNavXGyro().zeroYaw();
		System.out.println("Zeroing yaw...");
		while(Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 1)
			;
		System.out.println("Yaw zeroed.");
	}
}