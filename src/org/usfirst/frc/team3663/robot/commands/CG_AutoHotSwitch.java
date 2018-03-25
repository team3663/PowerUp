package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoHotSwitch extends CommandGroup {

	public CG_AutoHotSwitch(boolean isRight) {
		if (isRight) {
			
			addSequential(new C_DriveForwardRelative(125, .6));
					addSequential(new C_TurnRelativeAngle(-90, .8));
//			addSequential(new C_SimpleTurnRelative(-85, .8));
			addParallel(C_MoveElevatorToPos.fromInches(30.0));
			addSequential(new C_DriveForwardRelative(30, .6));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
//			addSequential(new C_DriveForwardRelative(-30, .6)); //this does not go backwords
//			addSequential(new C_TurnRelativeAngle(90, .8));
//			addSequential(new C_SimpleTurnRelative(85, .8));
		} else {
			addSequential(new C_DriveForwardRelative(125, .6));
					addSequential(new C_TurnRelativeAngle(90, .8));
//			addSequential(new C_SimpleTurnRelative(-85, .8));
			addParallel(C_MoveElevatorToPos.fromInches(30.0));
			addSequential(new C_DriveForwardRelative(30, .6));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
//			addSequential(new C_DriveForwardRelative(-30, .6));
//			addSequential(new C_TurnRelativeAngle(-90, .8));
//			addSequential(new C_SimpleTurnRelative(85, .8));
			
		}
	}
}
