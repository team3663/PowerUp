package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_BlindCubePickup extends Command {

	boolean out = false;
	int outCounter = 0;
	
	int startPos = 0;
	int ticksTravled = 0;

	double DISTANCE_LIMIT = 800;
	
	Command reverse;
    public C_BlindCubePickup() {
    	 requires(Robot.ss_camera);
         requires(Robot.ss_griff);
         
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		startPos = Robot.ss_drivetrain.get();
    		Robot.ss_cubeIntake.spinIntake(1);
    		Robot.ss_griff.setGriffSpd(.5);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.ss_cubeIntake.extendIntake(true);
		Robot.ss_cubeIntake.sqzIntake(true);

    	Robot.ss_griff.sqzGriff(true);
    	
    	if(reverse == null) {
    		Robot.ss_drivetrain.driveStraight(.5);
    	}
    	
    	if( Robot.ss_griff.getSwitchStateBoth()) {
    		Robot.ss_griff.setGriffSpd(.2);
    	}
    	else if(Robot.ss_griff.getSwitchStateAny()) {
    		Robot.ss_cubeIntake.spinIntake(-.5);
    		out = true;
    		
    	}
    	
    	/*
		if (out && outCounter < 5) {
			outCounter++;
		}
		else {
			Robot.ss_cubeIntake.spinIntake(1);
			outCounter = 0;
			out = false;
		}*/
    	
    	if (out) {
    		outCounter++;
    		if (outCounter >= 5) {
    			Robot.ss_cubeIntake.spinIntake(1);
    			outCounter = 0;
    			out = false;
    		}
    	}
		
		
		ticksTravled = Robot.ss_drivetrain.get() - startPos;
		
		
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//
    	System.out.println(Robot.ss_griff.getSwitchStateBoth() + " " +  (ticksTravled >= DISTANCE_LIMIT));
    	
    	if(reverse == null && (Robot.ss_griff.getSwitchStateBoth() || ticksTravled >= DISTANCE_LIMIT)) {
    		reverse = new C_DriveForwardRelative(-5 /*Robot.ss_drivetrain.ticksToInches(ticksTravled)*/, .7);
    		reverse.start();
    	}
    	
    	if(reverse != null) {
    		
    		System.out.println(reverse.isCompleted());
    		return reverse.isCompleted();
    		//return false;
    	}
    	else {
    		return false;
    	}
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
    	end();
    }
}
