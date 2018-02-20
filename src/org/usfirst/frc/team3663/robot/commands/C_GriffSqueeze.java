package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GriffSqueeze extends Command {
	public boolean state;

	public C_GriffSqueeze(boolean pState) {
		requires(Robot.ss_griff);
		state = pState;
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_griff.sqzGriff(state);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
}
