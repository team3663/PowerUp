package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class C_MoveElevatorToPos extends Command {
	
	private static final int THRESHOLD = (int)(SS_Elevator.TICKS_PER_INCH * 3);
	private static final double ELEVATOR_SPEED = 0.3;
	
	private boolean goingUp;
	private final int destination;
	
	private PIDController pidController = new PIDController(1, 1, -ELEVATOR_SPEED, ELEVATOR_SPEED);
	
	public C_MoveElevatorToPos(int ticks) {
		requires(Robot.ss_elevator);
		this.destination = SS_Elevator.clampTicks(ticks);
	}
	
	public static C_MoveElevatorToPos fromInches(double inches) {
		return new C_MoveElevatorToPos(SS_Elevator.inchesToTicks(inches));
	}
	
	private int getError() {
		return destination - Robot.ss_elevator.getPos();
	}
	
	@Override
	protected void initialize() {
		goingUp = getError() >= 0;
	}
	
	@Override
	protected void execute() {
		Robot.ss_elevator.set(pidController.get(getError()));
	}

	@Override
	protected boolean isFinished() {
		if (Math.abs(getError()) < THRESHOLD) return true;
		if (goingUp && Robot.ss_elevator.atTop()) return true;
		if (!goingUp && Robot.ss_elevator.atBottom()) return true;
		
		return false;
	}
	
	@Override
	protected void end() {
		Robot.ss_elevator.set(0);
	}

}
