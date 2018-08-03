package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_ForwardToPickup extends Command {

	boolean finished,seen = false;
	double traveled, travelLimit;
	int startPos;
	
	
    public C_ForwardToPickup(double travelLimit) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ss_griff);
    	requires(Robot.ss_cubeIntake);
    	this.travelLimit = travelLimit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_gyro.fakeResetGyro();
    	startPos = Robot.ss_drivetrain.get();
    	
    	Robot.ss_cubeIntake.spinIntake(1);
		Robot.ss_griff.setGriffSpd(.5);
		
		Robot.ss_cubeIntake.extendIntake(true);
		Robot.ss_cubeIntake.sqzIntake(true);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!seen)
    		Robot.ss_griff.setGriffSpd(.5);
    	else
    	Robot.ss_cubeIntake.spinIntake(1);

    	Robot.ss_griff.sqzGriff(true);
    	
    	traveled = Robot.ss_drivetrain.ticksToInches(Robot.ss_drivetrain.get() - startPos);
    	
    	if(traveled < travelLimit ) {
    		System.out.println(traveled);
	    	if(!seen)
	    		Robot.ss_drivetrain.driveStraight(.6);
	    	else
	    		Robot.ss_drivetrain.driveStraight(0);
	    	}
    	else {
    		Robot.ss_drivetrain.driveStraight(0);
    		Robot.ss_drivetrain.stop();
    	}
    	 	
    	if( Robot.ss_griff.getSwitchStateBoth()) {
    		Robot.ss_griff.setGriffSpd(.2);
    		finished = true; 
    	}
      	else if(Robot.ss_griff.getSwitchStateAny()) {
    		Robot.ss_cubeIntake.spinIntake(-.5);
    		seen=true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ss_cubeIntake.spinIntake(0);
    	Robot.ss_cubeIntake.extendIntake(false);
		Robot.ss_cubeIntake.sqzIntake(false);

    	Robot.ss_griff.sqzGriff(false);
    	Robot.ss_drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
