package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoHotScale extends CommandGroup {

	public CG_AutoHotScale(boolean isRight) {
		if (isRight) {
			addSequential(C_DriveForwardSimple.fromInches(200, .5));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_TurnRelativeAngle(90, .7));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		} else {
			addSequential(new C_DriveForwardRelative(250, .5));
			addSequential(new C_TurnRelativeAngle(60, .7));
			addSequential(new C_TurnRelativeAngle(60, .7));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			//addSequential(new C_SimpleTurnRelative(-85, .7));
			//addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(30, .5));
			addSequential(new C_TurnRelativeAngle(-90, .7));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		}
	}
}
