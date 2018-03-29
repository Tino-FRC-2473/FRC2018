package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.framework.TrackingRobot.RunState;
import org.usfirst.frc.team2473.robot.commands.CPiston;
import org.usfirst.frc.team2473.robot.commands.ChangeElevatorLevel;
import org.usfirst.frc.team2473.robot.commands.Climb;
import org.usfirst.frc.team2473.robot.commands.CompressorCommand;
import org.usfirst.frc.team2473.robot.commands.HookDown;
import org.usfirst.frc.team2473.robot.commands.HookUp;
import org.usfirst.frc.team2473.robot.commands.ToggleArms;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem.ClimbMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls {
	private Joystick throttle, wheel, panel;

	private Button buttonCV;
	private Button armDownButton;
	private Button armUpButton;
	private Button climbUp;
	private Button climbAssistUp;

	private Button eleLevel0;
	private Button eleLevel1;
	private Button eleLevel2;
	private Button eleLevel3;

	public Button openArmsButton;
	public Button closeArmsButton;
	public Button controlButton;
	public Button elevatorUp;
	public Button resetElevator;
	public Button cPistonInButton;
	public Button cPistonOutButton;
	public Button slow;
	public Button compressorOn;
	public Button compressorOff;
	public Button levelUpButton;
	public Button levelDownButton;
	public Button pickUpButton;

	public Controls() {
		/*-----------------
		   | JOYSTICKS |
		-----------------*/
		wheel = new Joystick(0);
		throttle = new Joystick(2);
		panel = new Joystick(3);

		if (Robot.getState() == RunState.COMPETITION) {

			/*------------------------
			   | ELEVATOR BUTTONS |
			------------------------*/
			eleLevel0 = new JoystickButton(panel, 7);
			eleLevel1 = new JoystickButton(panel, 5);
			eleLevel2 = new JoystickButton(panel, 3);
			eleLevel3 = new JoystickButton(panel, 1);

			eleLevel0.whenPressed(new ChangeElevatorLevel(0));
			eleLevel1.whenPressed(new ChangeElevatorLevel(1));
			eleLevel2.whenPressed(new ChangeElevatorLevel(2));
			eleLevel3.whenPressed(new ChangeElevatorLevel(3));

			/*-------------------
			   | ARM BUTTONS |
			-------------------*/
			openArmsButton = new JoystickButton(panel, 2);
			closeArmsButton = new JoystickButton(panel, 4);

			openArmsButton.whenPressed(new ToggleArms(true));
			closeArmsButton.whenPressed(new ToggleArms(false));

			/*---------------------
			   | DRIVE BUTTONS |
			---------------------*/
			slow = new JoystickButton(wheel, 6);
			buttonCV = new JoystickButton(wheel, 5);

			/*---------------------
			   | CLIMB BUTTONS |
			---------------------*/
			climbUp = new JoystickButton(throttle, 8);
			armUpButton = new JoystickButton(wheel, 4);
			armDownButton = new JoystickButton(wheel, 2);
			climbAssistUp = new JoystickButton(throttle, 7);

			climbUp.whileHeld(new Climb(ClimbMode.UP));
			armUpButton.whenPressed(new HookUp());
			armDownButton.whenPressed(new HookDown());
			climbAssistUp.whileHeld(new Climb(ClimbMode.ASSIST_UP));

			/*----------------------
			   | PISTON BUTTONS |
			----------------------*/
			cPistonInButton = new JoystickButton(wheel, 3);
			cPistonOutButton = new JoystickButton(wheel, 1);

			cPistonInButton.whenPressed(new CPiston());
			cPistonOutButton.whenPressed(new CPiston());
		} else if (Robot.getState() == RunState.RESET) {
			compressorOn = new JoystickButton(wheel, 2);
			compressorOff = new JoystickButton(wheel, 4);
		}
	}

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getWheel() {
		return wheel;
	}

	public Joystick getPanel() {
		return panel;
	}

	public Button getCVButton() {
		return buttonCV;
	}

}