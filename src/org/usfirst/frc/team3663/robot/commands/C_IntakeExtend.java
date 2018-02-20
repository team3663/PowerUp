package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_IntakeExtend extends Command {
	public boolean state;

	public C_IntakeExtend(boolean pState) {
		requires(Robot.ss_cubeIntake);
		state = pState;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_cubeIntake.extendIntake(state);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
}
