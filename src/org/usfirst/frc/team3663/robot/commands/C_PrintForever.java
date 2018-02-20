package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class C_PrintForever extends Command {
	
	private final String message;
	
	public C_PrintForever(String msg) {
		message = msg;
	}
	
	@Override
	protected void execute() {
		System.out.println(message);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
