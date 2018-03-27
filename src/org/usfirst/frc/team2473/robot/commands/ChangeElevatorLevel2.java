package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeElevatorLevel2 extends Command {
	public static BoxSystem box = Robot.getBox();
	private boolean up;
	private int target;
	private int targetLvl;
	private double threshold = 50;
	private int extraClicksDown;
	private boolean finished;

	public ChangeElevatorLevel2(boolean up) {
		this.up = up;
		target = -1;
	}

	public ChangeElevatorLevel2(int level) {
		targetLvl = level;
		target = box.posArray[Math.max(0, Math.min(4, level))];
	}

	@Override
	public void initialize() {
		finished = box.getLevel() == targetLvl;
		up = (box.getLevel() < targetLvl);

		// if (target == -1) {
		// targetLvl = -1;
		// extraClicksDown = -1;
		// finished = false;
		// int oldLevel = box.getLevel();
		//
		//
		// targetLvl = oldLevel;
		//
		// if (up)
		// targetLvl++;
		// else
		// targetLvl--;
		//
		// if (targetLvl < 0) {
		// targetLvl = 0;
		// } /*
		// * else if (level == 2) { level = (up) ? 3 : 1; }
		// */else if (targetLvl > 3) {
		// targetLvl = 4;
		// }

		target = box.posArray[targetLvl];

		threshold = (target == 0) ? 10 : 50;

	}

	public void execute() {
		if (box.hasZeroed()) {
			if (targetLvl == 1) {
				if (box.limitSwitchOne()) {
					if (up || extraClicksDown == 0) {
						box.setPow(0);
						extraClicksDown = -1;
						finished = true;
					} else if (extraClicksDown == -1) {
						extraClicksDown = 3;
						box.setPow(box.getDownAutomatedPower());
					} else {
						extraClicksDown--;
						box.setPow(box.getDownAutomatedPower());
					}
				} else {
					if (!up)
						box.setPow(box.getDownAutomatedPower());
					else
						box.setPow(-0.1);
				}
			} else {
				if (Math.abs(diff(target)) <= threshold) {
					// box.setLevel(box.getCurrPos(target));
					box.setPow(0);
					finished = true;
				} else {
					if (!up)
						box.setPow(box.getDownAutomatedPower());
					else
						box.setPow(box.getUpAutomatedPower());
				}
			}
		} else {
			finished = true;
		}
	}

	public int diff(int num) {
		return box.getEncCount() - num;
	}

	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void end() {
		box.stop();
	}
}