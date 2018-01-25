package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.commands.CloseArms;
import org.usfirst.frc.team2473.robot.commands.HookDown;
import org.usfirst.frc.team2473.robot.commands.HookUp;
import org.usfirst.frc.team2473.robot.commands.MoveArm;
import org.usfirst.frc.team2473.robot.commands.OpenArms;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

//// CREATING BUTTONS
// One type of button is a joystick button which is any button on a
//// joystick.
// You create one by telling it which joystick it's on and which button
// number it is.
// Joystick stick = new Joystick(port);
// Button button = new JoystickButton(stick, buttonNumber);

// There are a few additional built in buttons you can use. Additionally,
// by subclassing Button you can create custom triggers and bind those to
// commands the same as any other Button.

//// TRIGGERING COMMANDS WITH BUTTONS
// Once you have a button, it's trivial to bind it to a button in one of
// three ways:

// Start the command when the button is pressed and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenPressed(new ExampleCommand());

// Run the command while the button is being held down and interrupt it once
// the button is released.
// button.whileHeld(new ExampleCommand());

// Start the command when the button is released and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenReleased(new ExampleCommand());

public class Controls {
	public Joystick stick = new Joystick(RobotMap.joystickNum);
	public Button AscendButton = new JoystickButton(stick,RobotMap.ascendNum);
	public Button DescendButton = new JoystickButton(stick,RobotMap.descendNum);
	public Button OpenArmsButton = new JoystickButton(stick,RobotMap.openArmsNum);
	public Button CloseArmsButton = new JoystickButton(stick,RobotMap.closeArmsNum);
	public Button pos1 = new JoystickButton(stick,RobotMap.bottomElevatorNum);
	public Button pos2 = new JoystickButton(stick,RobotMap.lowerMidElevatorNum);
	public Button pos3 = new JoystickButton(stick,RobotMap.upperMidElevatorNum);
	public Button pos4 = new JoystickButton(stick,RobotMap.topElevatorNum);
	
	private static Controls theInstance;
	
	static {
		theInstance = new Controls();
	}
	
	private Controls() {
		AscendButton.whileHeld(new HookUp());
		DescendButton.whileHeld(new HookDown());		
		OpenArmsButton.whenPressed(new OpenArms());
		CloseArmsButton.whenPressed(new CloseArms());
		pos1.whenPressed(new MoveArm(1));
		pos2.whenPressed(new MoveArm(2));
		pos3.whenPressed(new MoveArm(3));
		pos4.whenPressed(new MoveArm(4));
	}
	
	public static Controls getInstance() {
		return theInstance;
	}
	
	public Joystick getJoy() {
		return stick;
	}
}
