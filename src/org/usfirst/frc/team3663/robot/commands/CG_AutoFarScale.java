package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Starts at either left or right, and goes to the opposite side of the scale
 */
public class CG_AutoFarScale extends CommandGroup {

    public CG_AutoFarScale(boolean startRight) {
    	//this code is changed a bunch for elims for DC
    	if (startRight) {
    		addParallel(new C_SetGriffSpeed(.2));
	    	addSequential(new C_DriveForwardRelative(200, .8));
	        addSequential(new C_TurnRelativeAngle(90, .65));
	       addParallel(C_MoveElevatorToPos.fromInches(35));
	        addSequential(new C_DriveForwardRelative(144, .7));
	        addSequential(new C_TurnRelativeAngle(-90, .75));

	       
	        addSequential(C_MoveElevatorToPos.fromInches(85));   
	        addSequential(new C_DriveForwardRelative(35, .5));
	        addParallel(C_MoveElevatorToPos.fromInches(85));
	        addSequential(new C_SetGriffSpeed(-1));
	        addSequential(new C_Wait(200));
	        addSequential(new C_SetGriffSpeed(0));
	        addParallel(C_MoveElevatorToPos.fromInches(0));
	        addSequential(new C_DriveForwardRelative(-12, .5));
	        
	    } else {
	    	addParallel(new C_SetGriffSpeed(.2));
	    	addSequential(new C_DriveForwardRelative(200, .8));
	        addSequential(new C_TurnRelativeAngle(-90, .75));
	        addParallel(C_MoveElevatorToPos.fromInches(35));
	        addSequential(new C_DriveForwardRelative(144, .7));
	        addSequential(new C_TurnRelativeAngle(90, .75));


	        addSequential(C_MoveElevatorToPos.fromInches(85));   
	        addSequential(new C_DriveForwardRelative(35, .5));
	        addParallel(C_MoveElevatorToPos.fromInches(85));
	        addSequential(new C_SetGriffSpeed(-1));
	        addSequential(new C_Wait(200));
	        addSequential(new C_SetGriffSpeed(0));
	        addParallel(C_MoveElevatorToPos.fromInches(0));
	        addSequential(new C_DriveForwardRelative(-12, .5));
	        
	    }
    }
}
