package org.usfirst.frc.team2473.robot;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team2473.robot.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import java.io.IOException;
import org.usfirst.frc.team2473.framework.DatabaseAndPingThread;
import org.usfirst.frc.team2473.framework.TrackableSubsystem;
import org.usfirst.frc.team2473.framework.TrackingRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */


public class Robot extends TrackingRobot {
	public static boolean isBeamBroken = false;
	
	@Override protected Thread jetsonThread() throws IOException { 
		//return new DatabaseAndPingThread("10.24.73.19", 5805);
		return null;
	}
	
	@Override
	protected String getProgramName() {
		return "Architecture Testing";
	}
	
	@Override
	protected TrackableSubsystem[] tSubsystems() {
		return new TrackableSubsystem[] {
				new ClimbSystem(), new BoxSystem()//, new DriveTrain()
		};
	}
	
	@Override
	protected Command getAutonomousCommand() {
		//return null;
	//	return new Type1AutoCommand();
		//return m_autonomousCommand;
		//return Climb;
		//return HookUp;
		//return HookDown;
		//return ClimbFaster;
		//return CloseArms;
		//return OpenArms;
		return null;
	}
	
	@Override protected void innerRobotInit() {
		//Devices.getInstance().addDoubleSolenoid(3, 4);
		//Devices.getInstance().addDoubleSolenoid(5, 6);
		//Devices.getInstance().addDigitalInput(7);
	}
	@Override protected void innerAutonomousInit(){
		Devices.getInstance().getTalon(2).set(ControlMode.PercentOutput, 0.5);
		Devices.getInstance().getTalon(2).set(ControlMode.Current, 10);
	}
	
	@Override protected void innerAutonomousPeriodic() {
		Robot.logCurrentState();
		System.out.println(Devices.getInstance().getTalon(2).getOutputCurrent());
		/*isBeamBroken = Devices.getInstance().getDigitalInput(4).get();
		if(isBeamBroken) {
			System.out.println("Box BreakBeam is Broken");
		}*/
	}
	
	@Override protected void innerTeleopInit(){
		/*	if(Climb!=null) Climb.start();
		if(HookDown!=null) HookDown.start();
		if(HookUp!=null) HookUp.start();
		if(ClimbFaster!=null) ClimbFaster.start();*/
	}
	@Override protected void innerTeleopPeriodic(){
		/*isBeamBroken = Devices.getInstance().getDigitalInput(4).get();
		if(isBeamBroken) {
			System.out.println("Box BreakBeam is Broken");*/
	}
	@Override protected void innerDisabledInit(){}
	@Override protected void innerDisabledPeriodic(){}
	@Override protected void innerTestInit(){}
	@Override protected void innerTestPeriodic(){}
}
