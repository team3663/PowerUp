package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GyroPrint extends Command {

	public C_GyroPrint() {
		// requires(Robot.ss_gyro);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {

	}

	@Override
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Robot.ss_gyro.gyroReadY();
		// System.out.println(Robot.ss_gyro.gyroReadZ());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
