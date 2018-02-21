package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_GriffSensor extends Command {

	public C_GriffSensor() {
		requires(Robot.ss_griff);
	}
	
	@Override
	protected void execute() {
		System.out.println(Robot.ss_griff.getSwitchState());
	}

	@Override
	protected boolean isFinished() {
		return Robot.ss_griff.getSwitchState();
	}

}
