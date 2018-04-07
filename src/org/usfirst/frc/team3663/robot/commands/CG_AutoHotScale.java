package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * unsafe to use in a match - untested
 */
public class CG_AutoHotScale extends CommandGroup {

	public CG_AutoHotScale(boolean isRight) {
		if (isRight) {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(-90, .7));
			//addSequential(new C_DriveForwardRelative(5, .5));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_TurnRelativeAngle(-180, .7));
			//addSequential(new C_DriveForwardRelative(30, .6));
			//addSequential(new C_DriveForwardRelative(30, .5));
//			addSequential(new C_TurnRelativeAngle(90, .7));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		} else {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(90, .7));
			addSequential(new C_DriveForwardRelative(-10, .5));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
		//	addSequential(new C_TurnRelativeAngle(-180, .8));
			//addSequential(new C_DriveForwardRelative(30, .6));
			//addSequential(new C_DriveForwardRelative(30, .5));
//			addSequential(new C_TurnRelativeAngle(-90, .9));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		}
	}
}
