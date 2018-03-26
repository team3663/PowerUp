package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Starts at either left or right, and goes to the opposite side of the scale
 */
public class CG_AutoFarScale extends CommandGroup {

    public CG_AutoFarScale(boolean startRight) {
    	if (startRight) {
	    	addSequential(new C_DriveForwardRelative(155, .7));
	        addSequential(new C_TurnRelativeAngle(-90, .7));
	        addSequential(new C_DriveForwardRelative(150, .7));
	        addSequential(new C_TurnRelativeAngle(90, .7));
	        addParallel(C_MoveElevatorToPos.fromInches(72));
	        addSequential(new C_DriveForwardRelative(18, .5)); 
	        addSequential(new C_TurnRelativeAngle(90, .7));
	        addSequential(new C_Wait(500));
	        addSequential(new C_SetGriffSpeed(1));
	        addSequential(new C_Wait(300));
	        addSequential(new C_SetGriffSpeed(0));
	        addParallel(C_MoveElevatorToPos.fromInches(0));
	    } else {
	    	addSequential(new C_DriveForwardRelative(155, .7));
	        addSequential(new C_TurnRelativeAngle(90, .7));
	        addSequential(new C_DriveForwardRelative(150, .7));
	        addSequential(new C_TurnRelativeAngle(-90, .7));
	        addParallel(C_MoveElevatorToPos.fromInches(72));
	        addSequential(new C_DriveForwardRelative(18, .5));
	        addSequential(new C_TurnRelativeAngle(-90, .7));
	        addSequential(new C_Wait(500));
	        addSequential(new C_SetGriffSpeed(1));
	        addSequential(new C_Wait(300));
	        addSequential(new C_SetGriffSpeed(0));
	        addParallel(C_MoveElevatorToPos.fromInches(0));
	    }
    }
}
