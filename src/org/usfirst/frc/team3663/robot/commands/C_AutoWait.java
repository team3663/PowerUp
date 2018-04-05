package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_AutoWait extends Command {
int target;
    public C_AutoWait(int target) {
    	this.target = target;
    	requires(Robot.ss_autoSelect);
    }
	@Override
	protected void initialize() {
		Robot.ss_gyro.hardResetGyro();
		Robot.ss_gyro.fakeReset();
		//Timer.delay(1);
	}
    protected boolean isFinished() {
        return Robot.ss_autoSelect.waitForMessage(); //returns true when not null or empty
    }

    protected void end() {
    	new C_AutoSelect(target).start();
    }

}
