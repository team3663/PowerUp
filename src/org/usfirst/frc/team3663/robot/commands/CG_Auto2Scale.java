package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2Scale extends CommandGroup {

    public CG_Auto2Scale(boolean isRight) {

    	if (isRight) {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(-90, .7));
			addSequential(new C_DriveForwardRelative(-4, .6));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			//second half ++++++
			
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-60, .7));
			addSequential(new C_DriveForwardRelative(25, .6));
			
			//pickup and turn
			
			addSequential(new C_VisionSeekCube(.45));
			
			addSequential(new C_DriveForwardRelative(-15, .6));
			addSequential(new C_TurnRelativeAngle(180, .8));
			
			//scoot scoot
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_DriveForwardRelative(25, .7));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			addSequential(new C_DriveForwardRelative(10, .6));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			
		} else {
	    	addParallel(new C_SetGriffSpeed(.2));
			addSequential(new C_Wait(200));
			addSequential(new C_DriveForwardRelative(265, .8));
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_TurnRelativeAngle(90, .7));
			addSequential(new C_DriveForwardRelative(-15, .6));  //not sure if right side needs to backup too
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			
			//second half ++++++
			
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-60, .7));
			addSequential(new C_DriveForwardRelative(25, .6));
			
			//pickup and turn
			addSequential(new C_VisionSeekCube(.45));
			
			addSequential(new C_DriveForwardRelative(-15, .6));
			addSequential(new C_TurnRelativeAngle(-180, .8));
			
			//scoot scoot
			addParallel(C_MoveElevatorToPos.fromInches(35));
			addSequential(new C_DriveForwardRelative(25, .7));
			addSequential(C_MoveElevatorToPos.fromInches(80.0));
			addSequential(new C_DriveForwardRelative(10, .6));
			
			//spit
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
		}
    }
}
