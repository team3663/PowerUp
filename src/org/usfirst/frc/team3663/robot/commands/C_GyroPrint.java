package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GyroPrint extends Command {

	public C_GyroPrint() {
		requires(Robot.ss_gyro);
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
}
