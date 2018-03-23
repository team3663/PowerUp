package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoMidDiffSide extends CommandGroup {

	public CG_AutoMidDiffSide(boolean switchRight) {
		if (switchRight) {
			addParallel(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(C_DriveForwardSimple.fromInches(90, .5));
			addSequential(new C_TurnRelativeAngle(60, .3));
			addSequential(C_DriveForwardSimple.fromInches(20, .25));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-175, .3));
			addSequential(C_DriveForwardSimple.fromInches(150, .4));
			addSequential(new C_TurnRelativeAngle(115, .4));
			addSequential(C_DriveForwardSimple.fromInches(50, .4));
		} else {
			addParallel(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(C_DriveForwardSimple.fromInches(90, .5));
			addSequential(new C_TurnRelativeAngle(-60, .3));
			addSequential(C_DriveForwardSimple.fromInches(20, .25));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(175, .3));
			addSequential(C_DriveForwardSimple.fromInches(150, .4));
			addSequential(new C_TurnRelativeAngle(-115, .4));
			addSequential(C_DriveForwardSimple.fromInches(50, .4));
		}
	}
}
