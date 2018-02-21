package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class C_MoveElevatorToPos extends Command {
	public static final int TICK_THRESHOLD = SS_Elevator.inchesToTicks(3);
	public static final double ELEVATOR_SPEED = 0.3;
	
	private final int destination;
	private PIDController pidController = new PIDController(1, 1, -ELEVATOR_SPEED, ELEVATOR_SPEED);
	
	private boolean goingUp = false;
	
	public C_MoveElevatorToPos(double inches) {
		requires(Robot.ss_elevator);
		this.destination = SS_Elevator.clampTicks(SS_Elevator.inchesToTicks(inches));
	}
	
	public C_MoveElevatorToPos(int ticks) {
		requires(Robot.ss_elevator);
		this.destination = SS_Elevator.clampTicks(ticks);
	}
	
	private int getError() {
		return destination - Robot.ss_elevator.get();
	}
	
	@Override
	protected void initialize() {
		goingUp = destination > Robot.ss_elevator.get();
	}
	
	@Override
	protected void execute() {
		Robot.ss_elevator.set(pidController.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		if (goingUp && Robot.ss_elevator.getTop()) return true;
		if (!goingUp && Robot.ss_elevator.getBottom()) return true;
		if (getError() < TICK_THRESHOLD) return true;
		
		return false;
	}
	
	@Override
	protected void end() {
		Robot.ss_elevator.set(0);
	}

}
