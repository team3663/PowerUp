package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Print out debug information of all encoders
 *
 */
public class C_DisplayEncoders extends Command {
	
	public C_DisplayEncoders() {
		requires(Robot.ss_elevator);
		requires(Robot.ss_gearbox);
	}
	
	@Override
	protected void execute() {
		System.out.printf("Left: %d\tRight: %d\nElev: %d\n\n", Robot.ss_gearbox.getLeft(),
				Robot.ss_gearbox.getRight(), Robot.ss_elevator.get());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
