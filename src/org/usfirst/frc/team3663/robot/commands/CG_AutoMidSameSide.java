package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoMidSameSide extends CommandGroup {

	public CG_AutoMidSameSide(boolean isRight) {
		if (isRight) {
			addSequential(C_DriveForwardSimple.fromInches(30, .5));
			addSequential(new C_TurnRelativeAngle(60, .3));
			addSequential(C_DriveForwardSimple.fromInches(10, .25));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(30, .3));
			addSequential(C_DriveForwardSimple.fromInches(21, .4));
			addSequential(new C_TurnRelativeAngle(-90, .4));
			addSequential(C_DriveForwardSimple.fromInches(12, .4));
		} else {
			addSequential(C_DriveForwardSimple.fromInches(30, .5));
			addSequential(new C_TurnRelativeAngle(-60, .3));
			addSequential(C_DriveForwardSimple.fromInches(10, .25));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-30, .3));
			addSequential(C_DriveForwardSimple.fromInches(21, .4));
			addSequential(new C_TurnRelativeAngle(90, .4));
			addSequential(C_DriveForwardSimple.fromInches(12, .4));
		}
	}
}
