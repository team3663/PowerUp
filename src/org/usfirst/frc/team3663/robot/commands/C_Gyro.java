package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Gyro extends Command {
public final int taco;
    public C_Gyro(int taco) {
    	requires(Robot.ss_gearbox);
    	requires(Robot.ss_gyro);
    	
    	this.taco = taco;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_gyro.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ss_gyro.turnGyro(taco);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_gearbox.stop();
    }
}
