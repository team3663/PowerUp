package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_IntakeSanityCheck extends Command {
	
	public static final int INTAKE_CHECK_THRESHOLD = 2500;

    public C_IntakeSanityCheck() {
    	requires(Robot.ss_cubeIntake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (Robot.ss_elevator.get() <= INTAKE_CHECK_THRESHOLD) {}
			//Robot.ss_cubeIntake.sqzIntake(true);						
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

}
