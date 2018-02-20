package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_SetGriffSpeed extends Command {
	private final double spd;

	public C_SetGriffSpeed(double spd1) {
		requires(Robot.ss_griff);
		spd = spd1;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.ss_griff.setGriffSpd(spd);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}


}
