package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Prints debug data from a lot of subsystems' sensors
 */
public class C_PrintSensors extends Command {
	
	public C_PrintSensors() {
		requires(Robot.ss_elevator);
		requires(Robot.ss_griff);
		requires(Robot.ss_drivetrain);
	}
	
	@Override
	protected void execute() {
		System.out.println(); // Extra line for spacing
		
		// ELEVATOR - top switch, bottom switch, encoder position
		System.out.printf("ELEVATOR\nTop %s\tBot %s\nPos %i\n",
				Robot.ss_elevator.atTop(), Robot.ss_elevator.atBottom(), Robot.ss_elevator.get());
		
		// GRIFF - optical limit switch, angle
		System.out.printf("GRIFF\nSwitch %s\tAngle %f\n", Robot.ss_griff.getSwitchState(),
				Robot.ss_griff.getAngle());
		
		// DRIVE TRAIN - left & right encoder position
		System.out.printf("DRIVE\nLeft %f\tRight %f\n", Robot.ss_drivetrain.getLeft(),
				Robot.ss_drivetrain.getRight());
	}

	@Override
	protected boolean isFinished() {		
		return true;
	}

}
