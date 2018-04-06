package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_InsanityCheck extends Command {

    public C_InsanityCheck() {
    }

    protected void initialize() {
    	if(Robot.ss_elevator.get() < 150) {
    		new CG_CubeCycle().start();
    	}
    }
    protected boolean isFinished() {
        return true;
    }
}
