package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ElevatorHold extends Command {

	private int holdPos = 0;
	private boolean release;
    public C_ElevatorHold(boolean release) {
    	this.release = release;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	holdPos = Robot.ss_elevator.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double pickle = 10;
    	int currPos = holdPos - Robot.ss_elevator.get();
    	Robot.ss_elevator.set(currPos/pickle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return release;
    }

    protected void end() {
    	//if this breaks C_elevator than start it back up here
    }
}
