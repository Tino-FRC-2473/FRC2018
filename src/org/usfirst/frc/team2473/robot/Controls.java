package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.commands.ChangeElevatorLevel;
import org.usfirst.frc.team2473.robot.commands.Climb;
import org.usfirst.frc.team2473.robot.commands.CompressorCommand;
import org.usfirst.frc.team2473.robot.commands.HookDown;
import org.usfirst.frc.team2473.robot.commands.HookUp;
import org.usfirst.frc.team2473.robot.subsystems.ClimbSystem.ClimbMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls {
	private Joystick throttle, wheel;
	
	private Button buttonCV;
	private Button armDownButton;
	private Button armUpButton;
	private Button climbUp;
	private Button climbUpSlow;
	private Button climbDown;
	private Button climbAssistUp;
	private Button climbAssistDown;
	
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
		throttle = new Joystick(1);

		
//		ask drivers for preferred button then group it with other climb buttons
		climbUpSlow = new JoystickButton(wheel, 4);
		climbUpSlow.whileHeld(new Climb(ClimbMode.UP_SLOW));
		
//		???
		//controlButton = new JoystickButton(throttle, 8);
		
		
		/*------------------------
		   | ELEVATOR BUTTONS |
		------------------------*/
		resetElevator = new JoystickButton(throttle, 4); //6
		levelUpButton = new JoystickButton(throttle, 5);
		levelDownButton = new JoystickButton(throttle, 6);
		//pickUpButton = new JoystickButton(throttle, 11);
		
		//i assume you just .get in the command
		//elevatorUp.whileHeld(new ElevatorCommand());
		resetElevator.whenPressed(new ChangeElevatorLevel(0));
		levelUpButton.whenPressed(new ChangeElevatorLevel(true));
		levelDownButton.whenPressed(new ChangeElevatorLevel(false));
		//pick up button? just .get in the command?
		
		/*-------------------
  		   | ARM BUTTONS |
		-------------------*/
		//closeArmsButton = new JoystickButton(throttle, 1);
		//openArmsButton = new JoystickButton(throttle, 2);
		
		//openArmsButton.whenPressed(new OpenArms());
		//closeArmsButton.whenPressed(new CloseArms());
		
		
		/*---------------------
		   | DRIVE BUTTONS |
		---------------------*/
		slow = new JoystickButton(wheel, 6);
		buttonCV = new JoystickButton(throttle, 7);
		//no .whenPressed? i assume you just .get from the drive train
		
		
		/*---------------------
		   | CLIMB BUTTONS |
		---------------------*/
		climbUp = new JoystickButton(throttle, 8);
		armUpButton = new JoystickButton(throttle, 9);
		armDownButton = new JoystickButton(throttle, 10);
		climbDown = new JoystickButton(throttle, 11);
		climbAssistUp = new JoystickButton(wheel, 2); //idk why one is wheel and other is throttle
		climbAssistDown = new JoystickButton(throttle, 12);
		
		climbUp.whileHeld(new Climb(ClimbMode.UP));
		armUpButton.whenPressed(new HookUp());
		armDownButton.whenPressed(new HookDown());
		climbDown.whileHeld(new Climb(ClimbMode.DOWN));
		climbAssistUp.whileHeld(new Climb(ClimbMode.ASSIST_UP));
		climbAssistDown.whileHeld(new Climb(ClimbMode.ASSIST_DOWN));
		

		/*----------------
		   | NOT SURE |
		----------------*/
		//cPistonInButton = new JoystickButton(throttle, -1);
		//cPistonOutButton = new JoystickButton(throttle, -1);

		//cPistonInButton.whileHeld(new CPistonIn());
		//cPistonOutButton.whileHeld(new CPistonOut());

		
		/*-------------------------
		   | COMPRESSOR BUTTONS |
		-------------------------*/
		compressorOn = new JoystickButton(wheel, 1);
		compressorOff = new JoystickButton(wheel, 5);
		
		compressorOn.whenPressed(new CompressorCommand());
		compressorOff.whenPressed(new CompressorCommand());
		
	}

	public Joystick getThrottle() {
		return throttle;
	}

	public Joystick getWheel() {
		return wheel;
	}

	public Button getCVButton() {
		return buttonCV;
	}

	public void runEle() {
		levelUpButton.whenPressed(new ChangeElevatorLevel(true));
		levelDownButton.whenPressed(new ChangeElevatorLevel(false));
	}
}
