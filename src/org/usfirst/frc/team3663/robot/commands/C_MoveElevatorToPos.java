package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves elevator to absolute position.
 *
 */
public class C_MoveElevatorToPos extends Command {

	// Finish when elevator within 3 inches of destination
	private static final int THRESHOLD_TICKS = SS_Elevator.inchesToTicks(3);

	private static final double ELEVATOR_SPEED = 0.3;

	private boolean goingUp;
	private final int destination;

	private final PIDController pidController = new PIDController(1, 1, 1, -ELEVATOR_SPEED, ELEVATOR_SPEED);

	/**
	 * @param ticks
	 *            Destination in ticks
	 */
	public C_MoveElevatorToPos(int ticks) {
		requires(Robot.ss_elevator);
		destination = SS_Elevator.clampTicks(ticks);
	}

	/**
	 * Use inches instead.
	 */
	public static C_MoveElevatorToPos fromInches(double inches) {
		return new C_MoveElevatorToPos(SS_Elevator.inchesToTicks(inches));
	}

	private int getError() {
		return destination - Robot.ss_elevator.get();
	}

	@Override
	protected void initialize() {
		// Elevator is going up if the distance to destination is positive
		goingUp = (getError() >= 0);
	}

	@Override
	protected void execute() {
		// Uses speed from PID Controller
		Robot.ss_elevator.set(pidController.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		// Finish when within threshold
		if (Math.abs(getError()) < THRESHOLD_TICKS) {
			return true;
		}

		// Stop anyways if one of the limit switches is reached
		if (goingUp && Robot.ss_elevator.atTop()) {
			return true;
		}
		if (!goingUp && Robot.ss_elevator.atBottom()) {
			return true;
		}

		return false;
	}

	@Override
	protected void end() {
		Robot.ss_elevator.set(0);
	}

}
