package org.usfirst.frc.team2473.robot;

import java.io.IOException;

import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.Auto.Route;
import org.usfirst.frc.team2473.robot.commands.AutonomousRoute;
import org.usfirst.frc.team2473.robot.commands.CVCommand;
import org.usfirst.frc.team2473.robot.commands.CompressorCommand;
import org.usfirst.frc.team2473.robot.commands.DiagCommand;
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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TrackingRobot {

	// CV TELEOP STUFF
	public CVCommand cvDrive;
	public static DriveCommand drive;

	// autonomous command-based stuff
	private CommandGroup autonomousCommand;
	private SendableChooser<Route> autoChooser;
	private SendableChooser<RunState> stateChooser;
	private Preferences pref;

	// robot modes
	public static boolean isNetwork = true;

	@Override
	protected Thread jetsonThread() throws IOException {
		return (isNetwork) ? new DatabaseAndPingThread(RobotMap.IP, RobotMap.PORT, false) : null;
	}

	@Override
	protected void innerRobotInit() {
		this.initStateChooser();

		// autonomous calibration
		this.initAutoChooser();
		this.initSensors();

		/* teleop command creation */
		cvDrive = new CVCommand();
		drive = new DriveCommand();

		TrackingRobot.getDriveTrain().enable();

		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture("Back", 0);
		camera0.setBrightness(0);
		camera0.setResolution(300, 300);

		UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("Front", 1);
		camera1.setBrightness(0);
		camera1.setResolution(300, 300);
	}

	public void initStateChooser() {
		stateChooser = new SendableChooser<RunState>();
		stateChooser.addDefault("Competition", RunState.COMPETITION);
		stateChooser.addObject("Diagnostic", RunState.DIAGNOSTIC);
		stateChooser.addObject("Reset", RunState.RESET);
		SmartDashboard.putData(stateChooser);
	}

	@Override
	protected void innerAutonomousInit() {
		getBox().zero();
		zeroYawIteratively();

		// Handle delay
		double delay = pref.getDouble("delay", 0);
		double origTime = System.currentTimeMillis() / 1000;
		while (System.currentTimeMillis() / 1000 < (delay + origTime))
			;
		SmartDashboard.putString("Delay Status", "Delay passed");

		System.out.println("Scheduler cleared...");

		((AutonomousRoute) autonomousCommand).configure(
				(DriverStation.getInstance().getGameSpecificMessage().toLowerCase().charAt(0) == 'r'),
				autoChooser.getSelected());
	}

	@Override
	protected void innerAutonomousPeriodic() {

	}

	protected void innerTeleopInit() {
		switch (getState()) {
		case COMPETITION:
			cvDrive.start();
			(new ElevatorCommand()).start();
			break;
		case DIAGNOSTIC:
			(new DiagCommand()).start();
			(new ElevatorCommand()).start();
			break;
		case RESET:
			(new CompressorCommand()).start();
			break;
		default:
			break;
		}
	}

	protected void innerTeleopPeriodic() {
	}

	protected void innerDisabledInit() {

	}

	@Override
	protected void innerDisabledPeriodic() {
		if (getState() != stateChooser.getSelected())
			changeStateTo(stateChooser.getSelected());
		setControls();
	}

	private void initAutoChooser() {
		System.out.println("Initializing chooser...");
		autoChooser = new SendableChooser<Route>();
		autoChooser.addObject("Left", Route.LEFT);
		autoChooser.addObject("Right", Route.RIGHT);
		autoChooser.addObject("Center", Route.CENTER);
		autoChooser.addObject("Reach Left", Route.REACH_LEFT);
		autoChooser.addObject("Reach Right", Route.REACH_RIGHT);
		autoChooser.addObject("CV Testing", Route.CV_TESTING);
		autoChooser.addDefault("Testing", Route.TESTING);
		SmartDashboard.putData("AutoChooser", autoChooser);
		Robot.addDevices();
		pref = Preferences.getInstance();
	}

	private void initSensors() {
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
		if (autonomousCommand == null)
			autonomousCommand = new AutonomousRoute();
		return autonomousCommand;
	}

	@Override
	protected PIDriveTrain driveTrain() {
		return new PIDriveTrain();
	}

	public static ClimbSystem getClimb() {
		return (ClimbSystem) Robot.getSubsystem(ClimbSystem.class);
	}

	public static BoxSystem getBox() {
		return (BoxSystem) Robot.getSubsystem(BoxSystem.class);
	}

	private void zeroYawIteratively() {
		Devices.getInstance().getNavXGyro().zeroYaw();
		while (Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 1)
			;
		System.out.println("Yaw zeroed.");
	}
}