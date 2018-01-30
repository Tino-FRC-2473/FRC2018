package org.usfirst.frc.team2473.framework;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public abstract class TrackablePIDSubsystem extends PIDSubsystem {
    public TrackablePIDSubsystem(double p, double i, double d, double f) {
		super(p, i, d, f);
		// TODO Auto-generated constructor stub
	}
    
	public abstract void stop();
    public abstract String getState();
    
    public void logCurrentState() {
		System.out.println("\t" + this.getClass().getSimpleName() + ": " + this.getState());
	}
}
