package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveForwardSimple extends Command {
	private final int THRESHOLD_TICKS = SS_DriveTrain.inchesToTicks(3);
	private final double speed = .5;
	private final int destination;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	public C_DriveForwardSimple(int ticks) {
		requires(Robot.ss_drivetrain);
		destination = ticks + Robot.ss_drivetrain.getLeft();
	}

	/**
	 * Use inches instead
	 */
	public static C_DriveForwardSimple fromInches(double inches) {
		return new C_DriveForwardSimple(SS_DriveTrain.inchesToTicks(inches));
	}

	/**
	 * Returns distance from destination. Positive value means it is forward;
	 * negative means backward
	 */
	private int getError() {
		return destination - Robot.ss_drivetrain.getLeft();
	}

	@Override
	protected void execute() {
		// set speed from PID controller
		Robot.ss_drivetrain.driveForward(Math.signum(getError()) * speed);
	}

	@Override
	protected boolean isFinished() {
		// Finished if error within threshold
		return Math.abs(getError()) < THRESHOLD_TICKS;
	}

	@Override
	protected void end() {
		// Stop wheels
		Robot.ss_drivetrain.stop();
	}

}
