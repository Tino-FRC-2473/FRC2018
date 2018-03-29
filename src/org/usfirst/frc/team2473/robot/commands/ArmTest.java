package org.usfirst.frc.team2473.robot.commands;

import org.usfirst.frc.team2473.framework.Devices;
import org.usfirst.frc.team2473.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmTest extends Command {

	SendableChooser<Boolean> armCompleteChooser;
	
    public ArmTest() {
    	SmartDashboard.putString("Arm Status", "Not yet");
    	armCompleteChooser = new SendableChooser<Boolean>();
    	armCompleteChooser.addDefault("Still testing arm", false);
    	armCompleteChooser.addObject("Completed arm testing", true);
    	SmartDashboard.putData(armCompleteChooser);
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putString("Arm Status", "Testing");
    	SmartDashboard.putNumber("Arm Position", Devices.getInstance().getTalon(RobotMap.CLIMB_ARM_MOTOR).getSelectedSensorPosition(0));
    }

    protected boolean isFinished() {
        return armCompleteChooser.getSelected();
    }

    protected void end() {
    	SmartDashboard.putString("Arm Status", "Healthy");    	
    }
    
    protected void interrupted() {
    	SmartDashboard.putString("Arm Status", "Unhealthy");
    }
}
