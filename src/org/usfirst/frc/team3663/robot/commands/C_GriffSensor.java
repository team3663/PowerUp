package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Holds until the griff sensor detects a cube
 */
public class C_GriffSensor extends Command {

	public C_GriffSensor() {
		requires(Robot.ss_griff);
	}

	@Override
	protected boolean isFinished() {
		return Robot.ss_griff.getSwitchState();
	}
	
}
