/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrain kExampleSubsystem
			= new DriveTrain();
	public static final ClimbSystem CLIMBER = new ClimbSystem();
	public static final BoxSystem BOX = new BoxSystem();
	public static boolean isBeamBroken = false;
	Command m_autonomousCommand;
	Command Climb;
	Command HookUp;
	Command HookDown;
	Command ClimbFaster;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	WPI_TalonSRX t = Devices.getInstance().getTalon(2);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

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
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		
		//methods to test (check once code is written)
		//stopMotor()
		//isAlive()
		//setExpiration()
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		isBeamBroken = Devices.getInstance().getDigitalInput(4).get();
			if(isBeamBroken) {
				System.out.println("Box BreakBeam is Broken");
			}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
	/*	if(Climb!=null) Climb.start();
		if(HookDown!=null) HookDown.start();
		if(HookUp!=null) HookUp.start();
		if(ClimbFaster!=null) ClimbFaster.start();*/
		
		Devices.getInstance().getTalon(2).set(ControlMode.PercentOutput, 0.5);
		long setTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - setTime < 2000) {
		}
			Devices.getInstance().getTalon(2).stopMotor();
			System.out.println("Motor stopped");
			while(System.currentTimeMillis() - setTime < 4000) {
			}
			Devices.getInstance().getTalon(2).set(ControlMode.PercentOutput, 0.5);
			
			Devices.getInstance().getTalon(2).setExpiration(5);
			System.out.println("Set expiration");


		//	Devices.getInstance().getTalon(2).set(ControlMode.PercentOutput,0.5);
				System.out.println("Adding Expiration");
			//Devices.getInstance().getTalon(2).setExpiration(10);
			//while(Devices.getInstance().getTalon(2).isAlive()) {
				//System.out.println("The motor is still alive!");
		//	}
			//System.out.println("The motor is dead.");
}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();	
		isBeamBroken = Devices.getInstance().getDigitalInput(4).get();
		if(isBeamBroken) {
			System.out.println("Box BreakBeam is Broken");
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void addDevices() {
		//Climber
		Devices.getInstance().addTalon(0);
		Devices.getInstance().addTalon(1);
		//Box
		Devices.getInstance().addTalon(2);
		Devices.getInstance().addDoubleSolenoid(3, 4);
		Devices.getInstance().addDoubleSolenoid(5, 6);
		Devices.getInstance().addDigitalInput(7);
	}	
		
}
