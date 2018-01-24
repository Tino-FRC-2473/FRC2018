package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.robot.Devices;
import org.usfirst.frc.team2473.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;



/**
 *
 */
public class MoveArm extends Command {
	private int buttonNum;
    public MoveArm(int b) {
       requires(Robot.BOX);                       
       buttonNum=b;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.BOX.getCurPos()>buttonNum) {
    		Devices.getInstance().getTalon(0).set(ControlMode.PercentOutput, Robot.BOX.POWER);
    	}else {
    		Devices.getInstance().getTalon(0).set(ControlMode.PercentOutput, -Robot.BOX.POWER);
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
