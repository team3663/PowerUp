package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.ElapsedTime;

import edu.wpi.first.wpilibj.command.Command;

public class C_Wait extends Command {
	private final ElapsedTime time = new ElapsedTime();
	private final int durationMillis;
	
	public C_Wait(int durationMillis) {
		this.durationMillis = durationMillis;
	}
	
	@Override
	protected void initialize() {
		time.reset();
	}
	
	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return time.getElapsedMillis() >= durationMillis;
	}

}
