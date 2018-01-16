package org.usfirst.frc.team2473.robot;

public class Trig {
	public static void main(String args[]) {
		double[] dAa = getDistAndAngle(1, 1);
		System.out.println("Distance: " + dAa[0]);
		System.out.println("Angle: " + dAa[1]);
	}
	
	public static double[] getDistAndAngle(int x, int y) {
		double[] distAndAngle = new double[2];
		distAndAngle[0] = Math.sqrt(x*x + y*y);
		distAndAngle[1] = Math.atan(x / y) * 180 / Math.PI;
		return distAndAngle;
	}

}
