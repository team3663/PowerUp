package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the climber winch speed and/or the pneumatic
 * piston on the elevator for picking up the hook. 
 */
public class C_SetClimber extends Command {

	boolean state;
	double spd;

	public C_SetClimber(boolean state, double spd) {
		requires(Robot.ss_climber);
		this.state = state;
		this.spd = spd;
	}
	
	public C_SetClimber(boolean state) {
		this(state, 0);
	}
	
	public C_SetClimber(double spd) {
		this(false, spd);
	}

	@Override
	protected void execute() {
		Robot.ss_climber.setClimber(state);
		Robot.ss_climber.setClimberSpd(spd);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
