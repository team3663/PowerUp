package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_IntakeSqz extends Command {

public boolean state;
//true = down 
//false = up
    public C_IntakeSqz(boolean pState) {
    	requires(Robot.ss_cubeIntake);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	state = pState;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ss_cubeIntake.sqzIntake(state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    		return true;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
