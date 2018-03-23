package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns the robot set amount of degrees to the right
 */
public class C_TurnRelativeAngle extends Command {
	// Finish the command if the robot is within this margin of error (by
	// degrees)
	private static final double ANGLE_THRESHOLD = 5;

	private final double destination;
	private final PIDController controller;

	private double getError() {
		return destination - Robot.ss_gyro.get();
	}


	/**
	 * If degrees is negative, turn left. Assumes speed is positive
	 */
	public C_TurnRelativeAngle(double degrees, double speed) {
		requires(Robot.ss_gyro);
		requires(Robot.ss_drivetrain);

		destination = degrees + Robot.ss_gyro.get();
		controller = new PIDController(.8, .02, 0, -speed, speed);// 360 is the full cricle so it makes sense? or 135 cuz thats 2/3 of the wrong direction // 60,0,1
		//still need to figure out the way to find kD
	}

	@Override
	protected void initialize() {
		Robot.ss_gyro.fakeResetGyro();
		Timer.delay(.5);
	}
	@Override
	protected void execute() {
		Robot.ss_drivetrain.turn(controller.get(getError()));
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
