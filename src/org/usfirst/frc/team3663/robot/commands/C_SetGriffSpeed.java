package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_SetGriffSpeed extends Command {
	private final double spd;

	public C_SetGriffSpeed(double spd) {
		requires(Robot.ss_griff);
		this.spd = spd;
	}

	@Override
	protected void execute() {
		Robot.ss_griff.setGriffSpd(spd);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
