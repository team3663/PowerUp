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
		this.destination = SS_Elevator.clampTicks(SS_Elevator.inchesToTicks(inches));
	}
	
	public C_MoveElevatorToPos(int ticks) {
		requires(Robot.ss_elevator);
		this.destination = SS_Elevator.clampTicks(ticks);
	}
	
	@Override
	protected void initialize() {
		int originalPosition = Robot.ss_elevator.get();
		goingUp = destination > originalPosition;
	}
	
	@Override
	protected void execute() {
		Robot.ss_elevator.set(pidController.get(Robot.ss_elevator.get()));
	}

	@Override
	protected boolean isFinished() {
		boolean atDest;
		if (goingUp) {
			atDest = Robot.ss_elevator.get() >= destination || Robot.ss_elevator.atTop();
		} else {
			atDest = Robot.ss_elevator.get() <= destination || Robot.ss_elevator.atBottom();
		}
		
		if (atDest)
			Robot.ss_elevator.set(0);
		return atDest;
	}

}
