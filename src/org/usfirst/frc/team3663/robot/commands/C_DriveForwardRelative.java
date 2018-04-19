package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
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
	boolean neg = false;
	double TEA = 27.25;
	
	public C_DriveForwardRelative(double d /* inches */, double speed) {
		requires(Robot.ss_drivetrain);

		if (d < 0) {
			neg = true;
			d = d*-1;
		}
		
		targetTime = d/(speed*TEA); //TODO: it was instatnly timing out because this was set to a negetive value
		//System.out.println(targetTime);
		
		int ticks = Robot.ss_drivetrain.inchesToTicks(d);
		destination = ticks;
		controller = new PIDController(.025, 0, 0, -speed, speed);
	}


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
		Robot.ss_gyro.fakeResetGyro();
		Robot.ss_drivetrain.reset();
	}
	
	@Override
	protected void execute() {
		//System.out.println("DRIVE FORWARD RELATIVE");
		// set speed from PID controller
		double error = getError();
		double speed = controller.get(error);
		
		this.current = Timer.getFPGATimestamp();
		// debug info
		//System.out.println("\nDest: " + destination + "\tPos: " + Robot.ss_drivetrain.getLeft() + "\tErr: " + error + "\nSpd: " + speed);
		if(neg)
			Robot.ss_drivetrain.driveStraight(-speed);
		else
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
		
		//System.out.println(current-start);
		// Stop wheels
		Robot.ss_drivetrain.stop();
	}

}
