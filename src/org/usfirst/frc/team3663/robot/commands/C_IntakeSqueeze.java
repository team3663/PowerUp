package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_IntakeSqueeze extends Command {
	// false = down; true = up
	private boolean state;

	public C_IntakeSqueeze(boolean state1) {
		requires(Robot.ss_cubeIntake);

		state = state1;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_cubeIntake.sqzIntake(state);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
}
