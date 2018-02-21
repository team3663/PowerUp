package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class C_DebugPrint extends Command {
	
	private final String message;
	private final boolean loop;
	
	public C_DebugPrint(String msg, boolean loop) {
		message = msg;
		this.loop = loop;
	}
	
	@Override
	protected void execute() {
		System.out.println(message);
	}

	@Override
	protected boolean isFinished() {
		return !loop;
	}

}
