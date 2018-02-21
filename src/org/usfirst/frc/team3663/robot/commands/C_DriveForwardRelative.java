package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class C_DriveForwardRelative extends Command {
	private final int THRESHOLD_TICKS = SS_DriveTrain.inchesToTicks(3);
	
	private final int destination;
	private final PIDController controller;
	
	public C_DriveForwardRelative(int ticks, double speed) {
		requires(Robot.ss_drivetrain);
		
		destination = ticks;
		controller = new PIDController(1, 1, -speed, speed);
	}
	
	public static C_DriveForwardRelative fromInches(double inches, double speed) {
		return new C_DriveForwardRelative(SS_DriveTrain.inchesToTicks(inches), speed);
	}
	
	private int getError() {
		return destination - Robot.ss_drivetrain.getLeft();
	}
	
	@Override
	protected void execute() {
		// get speed from PID controller
		Robot.ss_drivetrain.driveForward(controller.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		// Finished if error within threshold
		return Math.abs(getError()) < THRESHOLD_TICKS;
	}
	
	@Override
	protected void end() {
		// Stop wheels
		Robot.ss_drivetrain.driveForward(0);
	}

}