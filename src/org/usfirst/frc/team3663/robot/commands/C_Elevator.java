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

	private static boolean initialized = false;
	@Override
	protected void execute() {
		// Lower elevator until it's initialized
		if (!initialized) {
			Robot.ss_elevator.set(-.3);
			initialized = Robot.ss_elevator.atBottom();
			System.out.println("Lowering Elevator");
			return;
		}

		// Robot.ss_elevator.checkElevator();
		// Robot.ss_elevator.elevator1.set(-Robot.oi.driveStick.getRawAxis(5));
		Robot.ss_elevator.setSmoothing((-Robot.oi.driveStick.getRawAxis(5)) * .75);
	}

	@Override
	protected boolean isFinished() {
		return false; // Run indefinitely until interrupted
	}
}
