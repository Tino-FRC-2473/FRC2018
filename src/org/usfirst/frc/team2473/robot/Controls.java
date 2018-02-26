package org.usfirst.frc.team2473.robot;

import org.usfirst.frc.team2473.robot.commands.ChangeElevatorLevel;
import org.usfirst.frc.team2473.robot.commands.ClimbAssistDown;
import org.usfirst.frc.team2473.robot.commands.ClimbAssistUp;
import org.usfirst.frc.team2473.robot.commands.ClimbDown;
import org.usfirst.frc.team2473.robot.commands.ClimbUp;
import org.usfirst.frc.team2473.robot.commands.ClimbUpSlow;
import org.usfirst.frc.team2473.robot.commands.CompressorCommand;
import org.usfirst.frc.team2473.robot.commands.HookDown;
import org.usfirst.frc.team2473.robot.commands.HookUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class Controls {

	Joystick throttle, wheel;
	private Button buttonCV;
	public Button armDownButton;
	public Button armUpButton;
	public Button climbUp;
	public Button climbUpSlow;
	public Button climbDown;
	public Button climbAssistUp;
	public Button climbAssistDown;
	
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
		wheel = new Joystick(0);
		throttle = new Joystick(1);
		buttonCV = new JoystickButton(throttle, 7);

		armDownButton = new JoystickButton(throttle, RobotMap.armDownNum);
		armUpButton = new JoystickButton(throttle, RobotMap.armUpNum);
		climbUp = new JoystickButton(throttle, RobotMap.climbUp);
		climbUpSlow = new JoystickButton(wheel, RobotMap.climbUpSlow);
		climbDown = new JoystickButton(throttle, RobotMap.climbDown);
		
		climbAssistUp = new JoystickButton(wheel, 2);
		climbAssistDown = new JoystickButton(throttle, 12);
		
		openArmsButton = new JoystickButton(throttle, RobotMap.openArmsNum);
		closeArmsButton = new JoystickButton(throttle, RobotMap.closeArmsNum);
		controlButton = new JoystickButton(throttle, RobotMap.controlButtonNum);
		elevatorUp = new JoystickButton(throttle, RobotMap.elevatorUpNum);
		resetElevator = new JoystickButton(throttle, RobotMap.elevatorDownNum);
		// cPistonInButton = new JoystickButton(throttle, RobotMap.cPistonInNum);
		// cPistonOutButton = new JoystickButton(throttle, RobotMap.cPistonOutNum);
		slow = new JoystickButton(wheel, RobotMap.SLOW_BUTTON);
		compressorOn = new JoystickButton(wheel, RobotMap.COMPRESSOR_ON);
		compressorOff = new JoystickButton(wheel, RobotMap.COMPRESSOR_OFF);

		compressorOn.whenPressed(new CompressorCommand());
		compressorOff.whenPressed(new CompressorCommand());

		levelUpButton = new JoystickButton(throttle, RobotMap.levelUp);
		levelDownButton = new JoystickButton(throttle, RobotMap.levelDown);
//		pickUpButton = new JoystickButton(throttle, RobotMap.pickUpLevel);

		levelUpButton.whenPressed(new ChangeElevatorLevel(true));
		levelDownButton.whenPressed(new ChangeElevatorLevel(false));

		// climb
		armDownButton.whenPressed(new HookDown());
		armUpButton.whenPressed(new HookUp());
		climbUp.whileHeld(new ClimbUp());
		climbUpSlow.whileHeld(new ClimbUpSlow());
		climbDown.whileHeld(new ClimbDown());
		climbAssistUp.whileHeld(new ClimbAssistUp());
		climbAssistDown.whileHeld(new ClimbAssistDown());

		// claw
		// openArmsButton.whenPressed(new OpenArms());
		// closeArmsButton.whenPressed(new CloseArms());

		// cPistonInButton.whileHeld(new CPistonIn());
		// cPistonOutButton.whileHeld(new CPistonOut());

		// elevator
		// elevatorUp.whileHeld(new ElevatorCommand());
		resetElevator.whenPressed(new ChangeElevatorLevel(0));
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
