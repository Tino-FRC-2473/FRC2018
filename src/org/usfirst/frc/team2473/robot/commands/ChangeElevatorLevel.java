package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeElevatorLevel extends Command {


	public static BoxSystem box = Robot.getBox();
	private boolean up;
	private int target = -1;
	private double threshold = 50;

	public ChangeElevatorLevel(boolean up) {
		this.up = up;
		target = -1;
	}

	public ChangeElevatorLevel(int level) {
		System.out.println(level);
//		if (level < 0) {
//			level = 0;
//		} else if (level > 5) {
//			level = 5;
//		}
		target = box.posArray[Math.max(0, Math.min(5, level))];
	}

	@Override
	public void initialize() {

		if (target == -1) {
			int level = box.getLevel();
			System.out.println("PREV LVL: " + level);

			if (up)
				level++;
			else
				level--;

			if (level < 0) {
				level = 0;
			} /*else if (level == 2) {
				level = (up) ? 3 : 1;
			} */else if (level > 4) {
				level = 4;
			}
			
			target = box.posArray[level];
			System.out.println("NEW LVL: " + level + ", NEW TARGET" + target + "\n");
		}
	}

	public void execute() {
		if (box.hasZeroed()) {
			if (Math.abs(diff(target)) <= threshold) {
				// box.setLevel(box.getCurrPos(target));
				box.setPow(0);
			} else {
				if (diff(target) > threshold) {
					box.setPow(box.getDownAutomatedPower());
				} else {
					box.setPow(box.getUpAutomatedPower());
				}
			}
		} else {
			System.out.println("HASN'T ZEROED YET U LITTLE SHITE");
		}
	}

	public int diff(int num) {
		return box.getEncCount() - num;
	}

	@Override
	public boolean isFinished() {
		return Math.abs(diff(target)) <= threshold;
	}

	@Override
	public void end() {
		box.stop();
		target = -1;
	}
}