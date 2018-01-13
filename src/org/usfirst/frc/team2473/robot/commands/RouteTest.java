package org.usfirst.frc.team2473.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RouteTest extends CommandGroup {

    public RouteTest(double straightPower, double turnPower) {
        addSequential(new SimpleDriveStraight(60000, straightPower));
        addSequential(new PointTurn(45, turnPower));
        addSequential(new SimpleDriveStraight(70000, straightPower));
    }
}
