package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_TriggerButton extends Command {

    public C_TriggerButton() {
    	requires(Robot.ss_griff);
    }

    protected void execute() {
    	if(Robot.oi.driveStick.getRawAxis(2) >= .15) 
    		Robot.ss_griff.sqzGriff(true);
    	else
    		Robot.ss_griff.sqzGriff(false);
    }

    protected boolean isFinished() {
        return false;
    }
}