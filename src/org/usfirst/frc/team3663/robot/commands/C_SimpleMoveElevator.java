package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_SimpleMoveElevator extends Command {
	
	
	private final int destination;

    public C_SimpleMoveElevator(int ticks) {
    	requires(Robot.ss_elevator);
    	destination = SS_Elevator.clampTicks(ticks);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( Math.abs(Robot.oi.driveStick.getRawAxis(5)) > .1)			//if joystick moves leave move elevator
    	{
    		return true;
    	}
        return Robot.ss_elevator.MoveTo(destination);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
