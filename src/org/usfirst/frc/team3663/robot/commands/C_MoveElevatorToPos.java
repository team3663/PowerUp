package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class C_MoveElevatorToPos extends Command {
	
	private static final double ELEVATOR_SPEED = 0.3;
	
	private boolean goingUp;
	private final int destination;
	
	private PIDController pidController = new PIDController(1, 1, -ELEVATOR_SPEED, ELEVATOR_SPEED);
	
	public C_MoveElevatorToPos(double inches) {
		requires(Robot.ss_elevator);
		this.destination = Math.floorDiv(SS_Elevator.inchesToTicks(inches), SS_Elevator.ELEVATOR_MAX);
	}
	
	@Override
	protected void initialize() {
		int originalPosition = Robot.ss_elevator.getPos();
		goingUp = destination > originalPosition;
	}
	
	@Override
	protected void execute() {
		Robot.ss_elevator.set(pidController.get(Robot.ss_elevator.getPos()));
	}

	@Override
	protected boolean isFinished() {
		if (goingUp) {
			return Robot.ss_elevator.getPos() >= destination || Robot.ss_elevator.atTop();
		} else {
			return Robot.ss_elevator.getPos() <= destination;
		}
	}

}
