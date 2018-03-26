package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.ElapsedTime;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

@Deprecated
public class C_DriveForwardByTime extends Command {
	
	private final ElapsedTime timer = new ElapsedTime();
	private final double seconds;
	private final double speed;
	
	public C_DriveForwardByTime(double seconds, double speed) {
		requires(Robot.ss_drivetrain);
		
		this.seconds = seconds;
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
	}
	
	@Override
	protected void execute() {
		Robot.ss_drivetrain.driveStraight(speed);
	}

	@Override
	protected boolean isFinished() {
		return timer.getElapsedSeconds() > seconds;
	}
	
	@Override
	protected void end() {
		Robot.ss_drivetrain.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}

}
