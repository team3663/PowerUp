package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class C_DriveForwardToPosition extends Command {
	
	private final int TICK_THRESHOLD = SS_DriveTrain.inchesToTicks(3);
	
	private final int dest;
	private final PIDController controller;
	
	public C_DriveForwardToPosition(double inches, double speed) {
		requires(Robot.ss_drivetrain);
		
		dest = Robot.ss_drivetrain.getLeft() + SS_DriveTrain.inchesToTicks(inches);
		controller = new PIDController(1, 1, -speed, speed);
	}
	
	private int getError() {
		return dest - Robot.ss_drivetrain.getLeft();
	}
	
	@Override
	protected void execute() {
		Robot.ss_drivetrain.driveForward(controller.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(getError()) < TICK_THRESHOLD;
	}
	
	@Override
	protected void end() {
		// Stop motor
		Robot.ss_drivetrain.driveForward(0);
	}

}
