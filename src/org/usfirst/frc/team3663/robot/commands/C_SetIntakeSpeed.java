package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets speed of intake wheels.
 */
public class C_SetIntakeSpeed extends Command {

	private final double spd;

	public C_SetIntakeSpeed(double spd) {
		requires(Robot.ss_cubeIntake);

		this.spd = spd;
	}

	@Override
	protected void execute() {
		Robot.ss_cubeIntake.spinIntake(spd);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
