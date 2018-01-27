package org.usfirst.frc.team2473.robot;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.RobotMap.Route;
import org.usfirst.frc.team2473.robot.commands.AutonomousRoute;
import org.usfirst.frc.team2473.robot.commands.PointTurn;
import org.usfirst.frc.team2473.robot.commands.SimpleDriveStraight;
import org.usfirst.frc.team2473.robot.subsystems.PIDriveTrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	
	private Preferences pref;
	public static OP oi;
	
	public static boolean isNetwork = false;

	public static PIDriveTrain piDriveTrain;
	
	private static final double AUTO_ENCODER_LIMIT = 120;
	private static final double AUTO_POW = 0.45;
	private static final double TURN_POW = 0.3;
	private SendableChooser<CommandGroup> chooser;
	private double delay;
//	CommandGroup autonomousCommand;
	AutonomousRoute autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		piDriveTrain = new PIDriveTrain();
//		piDriveTrain.enable();
//		chooser = new SendableChooser<CommandGroup>();
//		chooser.addDefault("LeftDriveStraight", new AutonomousRoute(Route.LEFT));
//		chooser.addDefault("RightDriveStraight", new AutonomousRoute(Route.RIGHT));
//		SmartDashboard.putData("AutoChooser", chooser);
		Robot.addDevices();
//		pref = Preferences.getInstance();
		

		System.out.println("PIDrivetrain initialized");
		autonomousCommand = new AutonomousRoute(Route.LEFT);
//		autonomousCommand = new PointTurn(90, 0.3);
//		autonomousCommand = new SimpleDriveStraight(AUTO_ENCODER_LIMIT, AUTO_POW, false);
		System.out.println("auto command initialized");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		SimpleDriveStraight.resetEncoders();
	}


	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		while(Math.abs(Devices.getInstance().getNavXGyro().getYaw()) > 5) {
			Devices.getInstance().getNavXGyro().zeroYaw();
			System.out.println("resetting...");
		}
		Devices.getInstance().getTalon(RobotMap.BL).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		Devices.getInstance().getTalon(RobotMap.BR).configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 5);
		Devices.getInstance().getTalon(RobotMap.BL).setSelectedSensorPosition(0, 0, 5);
		Devices.getInstance().getTalon(RobotMap.BR).setSelectedSensorPosition(0, 0, 5);
		piDriveTrain.enable();
		if (autonomousCommand!=null)
			autonomousCommand.start();
		System.out.println("Autonomous Init started...");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("teleop init");
		// TODO
//		if (autonomousCommand != null) {
//			autonomousCommand.cancel();
//			piDriveTrain.disable();
//		}
//		teleopCommand.start();

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public int avg(int a, int b) {
		return (Math.abs(a) + Math.abs(b))/2;
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	private static void addDevices(){
		Devices.getInstance().addTalon(RobotMap.BR);
		Devices.getInstance().addTalon(RobotMap.BL);
		Devices.getInstance().addTalon(RobotMap.FL);
		Devices.getInstance().addTalon(RobotMap.FR);
	}
}