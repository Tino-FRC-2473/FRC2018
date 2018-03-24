package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Database;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.CV;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CVAuto extends Command {

	private boolean finished;
	private final boolean TIMING_DEBUGGING = false; // Prints when you've reached different parts in the code
	private final boolean DEBUG = true; // General debugging

	@Override
	protected void initialize() {
		if (TIMING_DEBUGGING)
			System.out.println("beginning CV drive code...");
		finished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double distance;
		double bearing;

		if (Database.getInstance().getNumeric("dist") != CV.NO_BOX) {
			if (TIMING_DEBUGGING)
				System.out.println("beginning the set");

			distance = Database.getInstance().getNumeric("dist");
			bearing = Database.getInstance().getNumeric("ang");

			if (distance < 30) {

				SmartDashboard.putString("CV Sight", "Box in sight: Final drive forward");

				if (DEBUG)
					System.out.println("Distance is less than 20");

				if (DEBUG)
					System.out.println("drive train not yet enabled");

				TrackingRobot.getDriveTrain().enable();
				if (DEBUG)
					System.out.println("drive train enabled");

				new LowerAndGo(distance).start();
				finished = true;

				if (DEBUG)
					System.out.println("DRIVE STRAIGHT STARTED");

				// Add claw commands for picking up box
				TrackingRobot.getDriveTrain().disable();
				SmartDashboard.putString("CV Sight", "Finished picking up!");
				if (DEBUG)
					System.out.println("Drive finished! woot");

			} else {
//				if (distance < CV.CLAW_DIST) {
//					(new ToggleArms(true)).start();
//					(new ChangeElevatorLevel2(1)).start();
//				} else {
//					(new ChangeElevatorLevel2(2)).start();
//				}

				if (Math.abs(bearing) > CV.BEARING_THRESHOLD) {
					SmartDashboard.putString("CV Sight", "Box in sight : Turning " + (bearing > 0 ? "Right" : "Left"));
					double sign = Math.signum(bearing);
					if (distance < 30)
						TrackingRobot.getDriveTrain().drive(CV.POWER, sign * CV.BIG_TURN);
					else
						TrackingRobot.getDriveTrain().drive(CV.POWER, sign * CV.SMALL_TURN);
				} else {
					SmartDashboard.putString("CV Sight", "Box in sight: Driving Straight");
					TrackingRobot.getDriveTrain().drive(CV.POWER, 0.0);
				}
			}
		} else {
			if (DEBUG)
				System.out.println("No box in sight!");
			SmartDashboard.putString("CV Sight", "No box in sight");
			TrackingRobot.getDriveTrain().stop();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		System.out.println("finished tracking to box");
		return finished;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		TrackingRobot.getDriveTrain().drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.drive.start();
	}
}