package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoHotSwitch extends CommandGroup {

	public CG_AutoHotSwitch(boolean isRight) {
		if (isRight) {
			addSequential(C_DriveForwardSimple.fromInches(50, .6));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(90, .5));
			addSequential(C_DriveForwardSimple.fromInches(15, .3));
		} else {
			addSequential(C_DriveForwardSimple.fromInches(50, .6));
			addSequential(new C_TurnRelativeAngle(90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			addSequential(C_DriveForwardSimple.fromInches(15, .3));
		}
	}
}
