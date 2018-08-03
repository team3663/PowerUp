package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2ScaleBlind extends CommandGroup {

    public CG_Auto2ScaleBlind(boolean isRight) {

    	if (isRight) {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(-90, .7));
			addSequential(new C_DriveForwardRelative(-4, .6));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			//second half ++++++
	
			//cube pickup
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-55, .75));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_ForwardToPickup(15));
			
			addSequential(C_MoveElevatorToPos.fromInches(35.0));
			addSequential(new C_DriveForwardRelative(5, .6));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_DriveForwardRelative(-10, .6));
			
		} else {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(90, .7));
			addSequential(new C_DriveForwardRelative(-10, .6));  //not sure if right side needs to backup too
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			
			//second half ++++++
			
			//cube pickup
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(55, .75));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_ForwardToPickup(15));
			
			addSequential(C_MoveElevatorToPos.fromInches(35.0));
			addSequential(new C_DriveForwardRelative(5, .6));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_DriveForwardRelative(-10, .6));
		}
    }
}
