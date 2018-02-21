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

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_griff.griffon.set(spd);
		//Robot.ss_griff.setGriffSpd(spd);;
	}
	@Override
	protected boolean isFinished() {
		return true;
	}
}
