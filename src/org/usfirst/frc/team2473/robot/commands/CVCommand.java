package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Database;
import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.framework.TrackingRobot;
import org.usfirst.frc.team2473.robot.CV;
import org.usfirst.frc.team2473.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CVCommand extends Command {
	private boolean runningCV;
	private boolean finished;

	private final boolean TIMING_DEBUGGING = false; // Prints when you've reached different parts in the code
	private final boolean DEBUG = false; // General debugging

	public CVCommand() {
		runningCV = false;
		finished = false;
	}

	@Override
	protected void initialize() {
		SmartDashboard.putString("CV Sight", "Press Button 8");
		if (TIMING_DEBUGGING)
			System.out.println("beginning CV drive code...");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (!runningCV || finished) {
			if (Robot.getControls().getCVButton().get()) {
				if (Robot.drive.isRunning()) {
					Robot.drive.cancel();
				}
				runningCV = true;
			} else {
				if (Robot.drive.isRunning() == false) {
					// TrackingRobot.getDriveTrain().enable();
					Robot.drive.start();
					// System.out.println("wooho11o");
					finished = false;
				}

				runningCV = false;
			}
		}
		if (runningCV && !Robot.getControls().getCVButton().get()) {
			// System.out.println("diff finished");
			runningCV = false;
		}

		if (TIMING_DEBUGGING)
			System.out.println("at cvcommand");

		double distance;
		double bearing;

		// if(!Robot.getControls().getCVButton().get()) {
		// finished = true;
		// System.out.println("ret");
		// return;
		// }

		if (runningCV && !finished && Robot.getControls().getCVButton().get()) { //
			Robot.drive.cancel();
			if (Database.getInstance().getNumeric("dist") != CV.NO_BOX) {
				if (TIMING_DEBUGGING)
					System.out.println("beginning the set");

				distance = Database.getInstance().getNumeric("dist");
				bearing = Database.getInstance().getNumeric("ang");

				if (distance < CV.DIST_THRESHOLD) {

					SmartDashboard.putString("CV Sight", "Box in sight: Final drive forward");

					if (DEBUG)
						System.out.println("Distance is less than 20");

					if (DEBUG)
						System.out.println("drive train not yet enabled");

					TrackingRobot.getDriveTrain().enable();
					if (DEBUG)
						System.out.println("drive train enabled");

					System.out.println("running lower and go");

					(new LowerAndGo(distance - 8)).start();
					// new DriveStraight(Devices.getInstance().getNavXGyro().getYaw(), distance - 3,
					// 0.5).start();

					if (DEBUG)
						System.out.println("DRIVE STRAIGHT STARTED");

					// Add claw commands for picking up box
//					TrackingRobot.getDriveTrain().disable();
					SmartDashboard.putString("CV Sight", "Finished picking up!");
					if (DEBUG)
						System.out.println("Drive finished! woot");

					finished = true;
					runningCV = false;
				} else {
					if (distance < CV.CLAW_DIST) {
//						(new ToggleArms(true)).start();
//						(new ChangeElevatorLevel2(1)).start();
					} else {
						System.out.println("calling change to 3");
						(new ChangeElevatorLevel(3)).start();
					}

					if (Math.abs(bearing) > CV.BEARING_THRESHOLD) {
						SmartDashboard.putString("CV Sight",
								"Box in sight : Turning " + (bearing > 0 ? "Right" : "Left"));
						double sign = Math.signum(bearing);
						if (distance < CV.CLOSE_DIST)
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
				runningCV = false;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// System.out.println("end");
//		TrackingRobot.getDriveTrain().enable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		// System.out.println("int");
		Robot.drive.start();
	}
}