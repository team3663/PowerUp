package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Elevator extends Command {

    public C_Elevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ss_elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_elevator.enableBreakMode(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.ss_elevator.set(Robot.oi.driveStick.getRawAxis(5));
    	System.out.println("ELEVATOR  :  "  + Robot.ss_elevator.get());
    	
    	// Makes sure elevator is in working order
    	Robot.ss_elevator.checkElevator();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
}
