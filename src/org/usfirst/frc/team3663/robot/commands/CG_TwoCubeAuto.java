package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_TwoCubeAuto extends CommandGroup {

    public CG_TwoCubeAuto(boolean isRight) {
		if (isRight) {
			//Copy Paste from Scale
			addSequential(new C_DriveForwardRelative(280, .75)); //.75 if not broke
			addSequential(new C_TurnRelativeAngle(-90, .8));
			addSequential(new C_DriveForwardRelative(15, .6));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			
			addSequential(new C_TurnRelativeAngle(-60, .8));
			addSequential(new C_VisionSeekCube(.6));
			
			//this might have to be separated into a sepearte command under vision in case it does not grab a cube
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(12, .6));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
		} else {
			addSequential(new C_DriveForwardRelative(280, .75));
			addSequential(new C_TurnRelativeAngle(90, .8));
			addSequential(new C_DriveForwardRelative(15, .5));
			addSequential(C_MoveElevatorToPos.fromInches(75.0));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			
			addSequential(new C_TurnRelativeAngle(60, .8));
			addSequential(new C_VisionSeekCube(.6));
			
			//this might have to be seperated into a sepearte command under vision in case it does not grab a cube
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
			addSequential(new C_DriveForwardRelative(12, .6));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
		}
    }
}
