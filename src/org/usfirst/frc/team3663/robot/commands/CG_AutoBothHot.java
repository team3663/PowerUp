package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoBothHot extends CommandGroup {

	public CG_AutoBothHot(boolean isRight) {
		if (isRight) {
			addSequential(new C_DriveForwardRelative(120, .5));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(90, .5));
			//needs to get a cube from the back side of the switch - maybe use camera?
			/*addSequential(C_DriveForwardRelative.fromInches(165, .3));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(90, .5));*/
		} else {
			addSequential(new C_DriveForwardRelative(145, .5));
			addSequential(new C_TurnRelativeAngle(90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(24.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-90, .5));
			/*addSequential(C_DriveForwardRelative.fromInches(165, .3));
			addSequential(new C_TurnRelativeAngle(90, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-90, .5));*/
		}
	}
}
