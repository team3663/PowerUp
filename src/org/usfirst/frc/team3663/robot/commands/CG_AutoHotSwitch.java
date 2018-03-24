package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoHotSwitch extends CommandGroup {

	public CG_AutoHotSwitch(boolean isRight) {
		if (isRight) {
			addParallel(C_MoveElevatorToPos.fromInches(30.0));
			addSequential(new C_DriveForwardRelative(125, .5));
			addSequential(new C_TurnRelativeAngle(-60, .7));
			addSequential(new C_TurnRelativeAngle(-60, .7));
//			addSequential(new C_SimpleTurnRelative(-85, .7));
			addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_TurnRelativeAngle(90, .7));
//			addSequential(new C_SimpleTurnRelative(85, .7));
		} else {
			addParallel(C_MoveElevatorToPos.fromInches(30.0));
			addSequential(new C_DriveForwardRelative(125, .5));
			addSequential(new C_TurnRelativeAngle(60, .7));
			addSequential(new C_TurnRelativeAngle(60, .7));
//			addSequential(new C_SimpleTurnRelative(-85, .7));
			addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_TurnRelativeAngle(-90, .7));
//			addSequential(new C_SimpleTurnRelative(85, .7));
			
		}
	}
}
