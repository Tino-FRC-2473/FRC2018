package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import edu.wpi.first.wpilibj.command.Command;

public class ChangeElevatorLevel extends Command {

	public static BoxSystem box = Robot.getBox();
	private boolean up;
	int initPos, initLevel, targetLevel, target = -1;
	private double threshold = 50;

	public ChangeElevatorLevel(boolean up) {
		this.up = up;
		target = -1;
	}

	public ChangeElevatorLevel(int level) {
		if (level < 0) {
			level = 0;
		} else if (level > 4) {
			level = 4;
		}		
		target = box.posArray[level];
	}

	@Override
	public void initialize() {
		initPos = box.getEncCount();
		initLevel = box.getCurrPos();

		if (target == -1) {
			int level = box.getLevel();

			if (up)
				level++;
			else
				level--;

			if (level < 0) {
				level = 0;
			} else if (level == 2) {
				level = (up) ? 3 : 1;
			} else if (level > 4) {
				level = 4;
			}
			
			target = box.posArray[level];
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