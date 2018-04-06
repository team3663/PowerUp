package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * unsafe to use in a match - untested
 */
public class CG_AutoHotScale extends CommandGroup {

	public CG_AutoHotScale(boolean isRight) {
		if (isRight) {
<<<<<<< HEAD
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(260, .8));
=======
			addSequential(new C_DriveForwardRelative(280, .6));
>>>>>>> 496e29f7867eea0b06d9aa831b7dcd185326e3fc
			addSequential(new C_TurnRelativeAngle(-90, .8));
			addSequential(new C_DriveForwardRelative(7, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(30, .5));
//			addSequential(new C_TurnRelativeAngle(90, .7));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		} else {
<<<<<<< HEAD
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(260, .8));
=======
			addSequential(new C_DriveForwardRelative(280, .6));
>>>>>>> 496e29f7867eea0b06d9aa831b7dcd185326e3fc
			addSequential(new C_TurnRelativeAngle(90, .8));
			addSequential(new C_DriveForwardRelative(7, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			//addSequential(new C_DriveForwardRelative(30, .5));
//			addSequential(new C_TurnRelativeAngle(-90, .9));
			//addSequential(new C_SimpleTurnRelative(85, .7));
		}
	}
}
