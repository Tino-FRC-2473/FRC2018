package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NoPIDDrivestraight extends Command {
	private int encoders;
	private double power;
    public NoPIDDrivestraight(int encoders, double power) {
        this.encoders = encoders;
        this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Devices.getInstance().getTalon((RobotMap.BL)).set(ControlMode.PercentOutput, power);
    	Devices.getInstance().getTalon((RobotMap.BR)).set(ControlMode.PercentOutput, power);
    }
    
	private int getAverageEnc(int enc1, int enc2) {
		return (Math.abs(enc1) + Math.abs(enc2)) / 2;
	}

	@Override
	protected boolean isFinished() {
		return getAverageEnc(Devices.getInstance().getTalon(RobotMap.BL).getSelectedSensorPosition(0),
				Devices.getInstance().getTalon(RobotMap.BR).getSelectedSensorPosition(0)) >= encoders;
	}

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
