package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets whether the griff is squeezed
 */
public class C_GriffSqueeze extends Command {
	public boolean state;

	public C_GriffSqueeze(boolean state) {
		requires(Robot.ss_griff);

		this.state = state;
	}

	@Override
	protected void execute() {
		Robot.ss_griff.sqzGriff(state);
	}

	@Override
	protected boolean isFinished() {
		// It only runs once
		return true;
	}
}
