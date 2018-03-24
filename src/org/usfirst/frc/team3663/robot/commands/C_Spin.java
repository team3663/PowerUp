package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.ElapsedTime;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Spin extends Command {
	
	double speed = .5;
	int secs;
	private final ElapsedTime timer = new ElapsedTime();

    public C_Spin(int secs) {
        requires (Robot.ss_drivetrain);
        this.secs = secs;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	Robot.ss_drivetrain.turn(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timer.getElapsedSeconds() >= secs) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_drivetrain.stop();
    }
}
