package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoMidSameSide extends CommandGroup {

	public CG_AutoMidSameSide(boolean isRight) {
		if (isRight) {
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(40, .5));
			addSequential(new C_TurnRelativeAngle(50, .8));
			addSequential(new C_DriveForwardRelative(20, .7));
			addSequential(new C_TurnRelativeAngle(-90, .8));
			addSequential(new C_DriveForwardRelative(40, .5));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			/*addSequential(new C_TurnRelativeAngle(30, .7));
			addSequential(new C_DriveForwardRelative(65, .5));
			addSequential(new C_TurnRelativeAngle(-90, .7));
			addSequential(new C_DriveForwardRelative(36, .4));*/
		} else {
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(40, .5));
			addSequential(new C_TurnRelativeAngle(-50, .8));
			addSequential(new C_DriveForwardRelative(20, .7));
			addSequential(new C_TurnRelativeAngle(90, .8));
			addSequential(new C_DriveForwardRelative(40, .5));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			/*addSequential(new C_TurnRelativeAngle(-30, .7));
			addSequential(new C_DriveForwardRelative(65, .5));
			addSequential(new C_TurnRelativeAngle(90, .7));
			addSequential(new C_DriveForwardRelative(36, .5));*/
		}
	}
}
