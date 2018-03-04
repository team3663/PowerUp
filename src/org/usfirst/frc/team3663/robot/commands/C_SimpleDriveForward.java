package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_SimpleDriveForward extends Command {
	private final int SLOW_RANGE = SS_DriveTrain.inchesToTicks(6);
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
	public C_SimpleDriveForward(int ticks, double pSpeed) {
		requires(Robot.ss_drivetrain);

		distance = Robot.ss_drivetrain.getLeft() + ticks;
		this.speed = pSpeed;
	}
	
	@Override
	protected void initialize()	{
		if (distance < 0)
			speed = -speed;
		Robot.ss_drivetrain.driveForward(speed);
	}
	
	@Override
	protected void execute() {
//		if ((distance - Robot.ss_drivetrain.getLeft()) <= SLOW_RANGE && 
//				Robot.ss_drivetrain.left1.get() > MIN_SPEED)
//		{
//			Robot.ss_drivetrain.driveForward(speed * 0.9);
//		}
		Robot.ss_drivetrain.driveForward(speed);
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
