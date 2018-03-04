package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveForwardRelative extends Command {
	private final int THRESHOLD_TICKS = Robot.ss_drivetrain.inchesToTicks(3);

	private final int destination;
	private final PIDController controller;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	public C_DriveForwardRelative(int ticks, double speed) {
		requires(Robot.ss_drivetrain);

		destination = ticks;
		controller = new PIDController(.05, 0, 0, -speed, speed);
	}

	/**
	 * Use inches instead
	 */
	public static C_DriveForwardRelative fromInches(double inches, double speed) {
		return new C_DriveForwardRelative(Robot.ss_drivetrain.inchesToTicks(inches), speed);
	}

	/**
	 * Returns distance from destination. Positive value means it is forward;
	 * negative means backward
	 */
	private int getError() {
		return destination - Robot.ss_drivetrain.getLeft();
	}
	
	@Override
	protected void initialize() {
		Robot.ss_drivetrain.reset();
	}

	@Override
	protected void execute() {
		// set speed from PID controller
		double error = getError();
		double speed = controller.get(getError());
		
		// debug info
		System.out.println("\nDest: " + destination + "\tPos: " + Robot.ss_drivetrain.getLeft() + "\tErr: " + error + "\nSpd: " + speed);
		Robot.ss_drivetrain.driveForward(speed);
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
