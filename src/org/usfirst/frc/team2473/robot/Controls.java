package org.usfirst.frc.team2473.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls {
	private Joystick stick;
	private Button button;
	
	private static Controls theInstance;
	
	static {
		theInstance = new Controls();
	}
	
	private Controls() {
		stick = new Joystick(0);
		button = new JoystickButton(stick, 0);
	}
	
	public static Controls getInstance() {
		return theInstance;
	}
	
	public Joystick getJoy() {
		return stick;
	}
	
	public Button getButton() {
		return button;
	}
}
