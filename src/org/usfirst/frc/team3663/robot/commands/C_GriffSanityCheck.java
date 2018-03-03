package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_GriffSanityCheck extends Command {
	public static final int GRIFF_CHECK_THRESHOLD = 2500;

	public C_GriffSanityCheck() {
		requires(Robot.ss_griff);
	}

	@Override
	protected void execute() {
		if (Robot.ss_elevator.get() <= GRIFF_CHECK_THRESHOLD) {
		}
		// Robot.ss_griff.sqzGriff(true);
	}

	@Override
	protected boolean isFinished() {
		return false; // Run forever until interrupted
	}

}
