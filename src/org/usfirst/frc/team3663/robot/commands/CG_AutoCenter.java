package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoCenter extends CommandGroup {

	public CG_AutoCenter(boolean isRight) {
		if (isRight) {
			addSequential(new C_DriveForwardRelative(24, .6));
			addSequential(new C_TurnRelativeAngle(40, .8));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(50, .6));
			addSequential(new C_TurnRelativeAngle(-40, .8));
			addSequential(new C_DriveForwardRelative(30, .6));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(-20, .6));
			/*addSequential(new C_TurnRelativeAngle(30, .3));
			addSequential(C_DriveForwardSimple.fromInches(65, .4));
			addSequential(new C_TurnRelativeAngle(-90, .4));
			addSequential(C_DriveForwardSimple.fromInches(36, .4));*/
		} else {

			addSequential(new C_DriveForwardRelative(24, .6));
			addSequential(new C_TurnRelativeAngle(-50, .8));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(50, .6));
			addSequential(new C_TurnRelativeAngle(45, .8));
			addSequential(new C_DriveForwardRelative(30, .6));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(-20, .6));
			/*addSequential(new C_TurnRelativeAngle(-30, .3));
			addSequential(C_DriveForwardSimple.fromInches(65, .4));
			addSequential(new C_TurnRelativeAngle(90, .4));
			addSequential(C_DriveForwardSimple.fromInches(36, .4));*/
		}
	}
}
