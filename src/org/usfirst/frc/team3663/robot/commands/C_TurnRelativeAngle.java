package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns the robot set amount of degrees to the right
 */
public class C_TurnRelativeAngle extends Command {
	// Finish the command if the robot is within this margin of error (by
	// degrees)
	private static final double ANGLE_THRESHOLD = 10;

	private final double destination;
	
	private int direction;

	/**
	 * If degrees is negative, turn left. Assumes speed is positive
	 */
	public C_TurnRelativeAngle(double degrees, double speed) {
		requires(Robot.ss_gyro);
		requires(Robot.ss_drivetrain);

		destination = degrees + Robot.ss_gyro.gyroGetAngle();
	}

	private double getError() {
		return destination - Robot.ss_gyro.gyroGetAngle();
	}

	@Override
	protected void execute() {
		//calculate positive or negative direction
		if(destination != 0)
		{
			int direction = (int) (destination / Math.abs(destination));
		}
		Robot.ss_drivetrain.turn(direction * speed);
	}

	@Override
	protected boolean isFinished() {
		// Finish when within threshold
		return Math.abs(getError()) < ANGLE_THRESHOLD;
	}

	@Override
	protected void end() {
		// Stops drivetrain
		Robot.ss_drivetrain.stop();
	}

}
