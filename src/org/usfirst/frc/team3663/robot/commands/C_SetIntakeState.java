package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets whether intake is out/in and it's extended
 */
public class C_SetIntakeState extends Command {

	private final boolean squeeze;
	private final boolean extend;
	

	public C_SetIntakeState(boolean squeeze, boolean extend) {
		requires(Robot.ss_cubeIntake);

		this.squeeze = squeeze;
		this.extend = extend;
	}

	@Override
	protected void execute() {
		Robot.ss_cubeIntake.sqzIntake(squeeze); 
		Timer.delay(.25);
		Robot.ss_cubeIntake.extendIntake(extend);

	}

	@Override
	protected boolean isFinished() {
		if (squeeze && extend) {
			Robot.ss_drivetrain.slow = .75;
		}
		else if (!squeeze && !extend) {
			Robot.ss_drivetrain.slow = 1;
		
		}
		return true; // Runs only once
	}

}
