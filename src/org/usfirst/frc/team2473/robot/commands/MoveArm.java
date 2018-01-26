package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.Robot;
import org.usfirst.frc.team2473.robot.subsystems.BoxSystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 */
public class MoveArm extends Command {
	private BoxSystem sub;
	private int buttonNum;
	
    public MoveArm(int b) {
    	sub = (BoxSystem) Robot.getSubsystem(BoxSystem.class);
    	requires(sub);                       
    	buttonNum=b;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(sub.getCurPos()>buttonNum) {
    		Devices.getInstance().getTalon(0).set(ControlMode.PercentOutput, sub.POWER);
    	}else {
    		Devices.getInstance().getTalon(0).set(ControlMode.PercentOutput, -sub.POWER);
    	}
    	if(Devices.getInstance().getDigitalInput(buttonNum).get()) {
    		Devices.getInstance().getTalon(0).stopMotor();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Devices.getInstance().getDigitalInput(buttonNum).get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Devices.getInstance().getTalon(0).stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
