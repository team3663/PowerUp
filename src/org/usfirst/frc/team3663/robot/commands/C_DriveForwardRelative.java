package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveForwardRelative extends Command {
	private int THRESHOLD_TICKS = Robot.ss_drivetrain.inchesToTicks(3.0);

	private final int destination;
	private final PIDController controller;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	double start = 0;
	double targetTime = 0;
	double current= 0;
	
	public C_DriveForwardRelative(int inches, double speed) {
		requires(Robot.ss_drivetrain);

		targetTime = inches/(speed*27.25);
		System.out.println(targetTime);
		
		int ticks = Robot.ss_drivetrain.inchesToTicks(inches);
		destination = ticks;
		controller = new PIDController(.025, 0, 0, -speed, speed);
	}

	/**
	 * Use inches instead
	 */


	/**
	 * Returns distance from destination. Positive value means it is forward;
	 * negative means backward
	 */
	private int getError() {
		return destination - Robot.ss_drivetrain.get();
	}
	
	@Override
	protected void initialize() {
		this.start = Timer.getFPGATimestamp();
		Robot.ss_gyro.resetGyro();
		Robot.ss_drivetrain.reset();
		Timer.delay(.5);
	}
	
	@Override
	protected void execute() {
		// set speed from PID controller
		double error = getError();
		double speed = controller.get(getError());
		
		this.current = Timer.getFPGATimestamp();
		// debug info
		//System.out.println("\nDest: " + destination + "\tPos: " + Robot.ss_drivetrain.getLeft() + "\tErr: " + error + "\nSpd: " + speed);
		Robot.ss_drivetrain.driveStraight(speed);
	}

	@Override
	protected boolean isFinished() {
		// Finished if error within threshold
		if (targetTime <= current-start)
			return true;
		else
			return Math.abs(getError()) < THRESHOLD_TICKS;
	}

	@Override
	protected void end() {
		
		System.out.println(current-start);
		// Stop wheels
		Robot.ss_drivetrain.stop();
	}

}
