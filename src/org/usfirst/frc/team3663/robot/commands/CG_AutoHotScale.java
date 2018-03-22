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
			addParallel(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			//addSequential(C_DriveForwardSimple.fromInches(15, .3));
		} else {
			addSequential(C_DriveForwardSimple.fromInches(200, .5));
			addSequential(new C_TurnRelativeAngle(90, .5));
			addParallel(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(90, .5));
			//addSequential(C_DriveForwardSimple.fromInches(15, .3));
		}
	}
}
