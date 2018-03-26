package org.usfirst.frc.team2473.robot;

import java.io.IOException;

import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto.Route;
import org.usfirst.frc.team2473.robot.commands.AutonomousRoute;
import org.usfirst.frc.team2473.robot.commands.CVCommand;
import org.usfirst.frc.team2473.robot.commands.DriveCommand;
import org.usfirst.frc.team2473.robot.commands.ElevatorCommand;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.PIDriveTrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
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
	Relay r = new Relay(2);

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
		 camera0.setResolution(300, 300);

		 UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Camera 1", 1);
		 camera1.setBrightness(0);
		 camera1.setResolution(300, 300);

//		 UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Camera 1", 1);
//		 camera1.setBrightness(0);
//		 camera1.setResolution(160, 120);
	}

	@Override
	protected void innerAutonomousInit() {
		getBox().zero();
		this.zeroYawIteratively();

		// Handle delay
		double delay = pref.getDouble("delay", 0);
		double origTime = System.currentTimeMillis() / 1000;
		while(System.currentTimeMillis() / 1000 < (delay + origTime))
			;
		SmartDashboard.putString("Delay Status", "Delay passed");
		
		System.out.println("Scheduler cleared...");
		
		((AutonomousRoute) autonomousCommand).configure(
				(DriverStation.getInstance().getGameSpecificMessage().toLowerCase().charAt(0) == 'r')
				, chooser.getSelected());
//		autonomousCommand.start(); //already done in trackingrobot
	}

	protected void innerTeleopInit() {
		r.set(Value.kForward);
		(new ElevatorCommand()).start();
//		(new ClawCommand()).start(); 
		cvDrive.start();
	}

	protected void innerTeleopPeriodic() {
//		System.out.println("Distance: " + Database.getInstance().getNumeric("dist"));
//		System.out.println("Angle: " + Database.getInstance().getNumeric("ang"));
//		Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).set(0.2);
		//((BoxSystem) getSubsystem(BoxSystem.class)).setLevel(((BoxSystem) getSubsystem(BoxSystem.class)).getCurrPos());
//		System.out.println(getBox().getLevel());
//		System.out.println(Devices.getInstance().getTalon(RobotMap.ELEVATOR_MOTOR).getSelectedSensorPosition(0));
//		System.out.println("left enc: " + Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0));
//		System.out.println("right enc: " + Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0));
//		System.out.println("limit one: " + ((BoxSystem) getSubsystem(BoxSystem.class)).limitSwitchOne());
//		System.out.println("limit zero: " + ((BoxSystem) getSubsystem(BoxSystem.class)).limitSwitchZero());
//		System.out.println("current level: " + ((BoxSystem) getSubsystem(BoxSystem.class)).getLevel());
	}
	
	protected void innerDisabledInit() {
		r.set(Value.kReverse);
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
		chooser.addObject("Reach Left", Route.REACH_LEFT);
		chooser.addObject("Reach Right", Route.REACH_RIGHT);
		chooser.addObject("CV Testing", Route.CV_TESTING);
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
		System.out.println("zeroing...");
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
//		return new ChangeElevatorLevel2(2);
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