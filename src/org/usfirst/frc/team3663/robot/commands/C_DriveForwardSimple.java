package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveForwardSimple extends Command {
	private final int SLOW_RANGE = Robot.ss_drivetrain.inchesToTicks(6);
	private final double MIN_SPEED = 0.3;

	private final int distance;
	private double speed;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	public C_DriveForwardSimple(int ticks, double pSpeed) {
		requires(Robot.ss_drivetrain);
		requires(Robot.ss_gyro);
		
		distance = Robot.ss_drivetrain.getLeft() + ticks;
		this.speed = pSpeed;
	}
	
	public static C_DriveForwardSimple fromInches(double inches, double pSpeed) {
		return new C_DriveForwardSimple(Robot.ss_drivetrain.inchesToTicks(inches), pSpeed);
	}
	
	@Override
	protected void initialize()	{
		Robot.ss_gyro.fakeResetGyro();
		if (distance < 0)
			speed = -speed;
		
	}
	
	@Override
	protected void execute() {
		if ((distance - Robot.ss_drivetrain.getLeft()) <= SLOW_RANGE && 
				Robot.ss_drivetrain.left1.get() > MIN_SPEED)
		{
			Robot.ss_drivetrain.driveStraight(speed * 0.9);
		}
		else {
			Robot.ss_drivetrain.driveStraight(speed);
		}

	}

	@Override
	protected boolean isFinished() {
		if (distance > 0)
			return Robot.ss_drivetrain.getLeft() >= distance;
		else
			return Robot.ss_drivetrain.getLeft() <= distance;
	}

	@Override
	protected void end() {
		// Stop wheels
		Robot.ss_drivetrain.stop();
	}

}
