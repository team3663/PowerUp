package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_SetIntakeSpeed extends Command {

	private final double spd;

	public C_SetIntakeSpeed(double spd) {
		requires(Robot.ss_cubeIntake);
		this.spd = spd;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_cubeIntake.spinIntake(spd);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return true;
	}
}
