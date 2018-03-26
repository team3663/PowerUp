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

    protected void initialize() {
    	timer.reset();
    	Robot.ss_drivetrain.turn(speed);
    }

    protected boolean isFinished() {
    	if(timer.getElapsedSeconds() >= secs) {
    		return true;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    	Robot.ss_drivetrain.stop();
    }
}
