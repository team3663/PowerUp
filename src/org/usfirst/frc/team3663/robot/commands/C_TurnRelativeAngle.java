package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class C_TurnRelativeAngle extends Command {
	// Finish the command if the robot is within this margin of error (by degrees)
	private static final double ANGLE_THRESHOLD = 10;
	
	private final double destination;
	private final PIDController controller;
	
	private double getError() {
		return destination - Robot.ss_gyro.gyroGetAngle();
	}
	
	public C_TurnRelativeAngle(double degrees, double speed) {
		requires(Robot.ss_gyro);
		requires(Robot.ss_drivetrain);
		
		this.destination = degrees + Robot.ss_gyro.gyroGetAngle();
		this.controller = new PIDController(1, 1, -speed, speed, destination);
	}
	
	@Override
	protected void execute() {
		Robot.ss_drivetrain.turn(controller.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(getError()) < ANGLE_THRESHOLD;
	}

}
