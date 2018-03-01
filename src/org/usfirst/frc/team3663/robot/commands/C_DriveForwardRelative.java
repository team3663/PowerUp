package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveForwardRelative extends Command {
	private final int THRESHOLD_TICKS = SS_DriveTrain.inchesToTicks(3);

	private final int destination;
	private final double speed;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	public C_DriveForwardRelative(int ticks, double pSpeed) {
		requires(Robot.ss_drivetrain);

		destination = ticks;
		speed = pSpeed;
	}

	/**
	 * Use inches instead
	 */
	public static C_DriveForwardRelative fromInches(double inches, double speed) {
		return new C_DriveForwardRelative(SS_DriveTrain.inchesToTicks(inches), speed);
	}

	/**
	 * Returns distance from destination. Positive value means it is forward;
	 * negative means backward
	 */
	private int getError() {
		//This assumes that the encoder ticks are positive going forward
		return destination - Robot.ss_drivetrain.getLeft();
	}

	@Override
	protected void execute() {
		int direction = 0;
		if (destination != 0)
			direction = (int) (destination / Math.abs(destination));
		Robot.ss_drivetrain.driveForward(direction * speed);
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
