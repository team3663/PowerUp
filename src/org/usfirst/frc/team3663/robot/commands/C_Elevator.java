package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Default Elevator command; controls elevator by joystick
 */
public class C_Elevator extends Command {

	public C_Elevator() {
		requires(Robot.ss_elevator);
	}

	@Override
	protected void initialize() {
		Robot.ss_elevator.enableBreakMode(true);
	}

	@Override
	protected void execute() {
		Robot.ss_elevator.checkElevator();
		Robot.ss_elevator.elevator1.set(-Robot.oi.driveStick.getRawAxis(5));
	}

	@Override
	protected boolean isFinished() {
		return false; // Run indefinitely until interrupted
	}
}
