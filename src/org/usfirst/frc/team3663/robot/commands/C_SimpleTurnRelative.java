package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns the robot set amount of degrees to the right
 */

@Deprecated
public class C_SimpleTurnRelative extends Command {

	double speed;
	double degrees;
	/**
	 * If degrees is negative, turn left. Assumes speed is positive
	 */
	public C_SimpleTurnRelative (double degrees, double speed) {
		requires(Robot.ss_gyro);
		requires(Robot.ss_drivetrain);
		this.speed = speed;
		this.degrees = degrees;
	}

	@Override
	protected void initialize() {
		Robot.ss_gyro.fakeResetGyro();
		Timer.delay(.5);
	}
	@Override
	protected void execute() {
		Robot.ss_drivetrain.turn(Math.signum(degrees) * speed);
	}

	@Override
	protected boolean isFinished() {
		// Finish when within threshold
		if (speed < 0)
			return Robot.ss_gyro.get() < degrees;
		else
			return Robot.ss_gyro.get() > degrees;
	}

	@Override
	protected void end() {
		Robot.ss_drivetrain.stop();
	}

}
