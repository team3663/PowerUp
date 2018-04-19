package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2SwitchCenter extends CommandGroup {

    public CG_Auto2SwitchCenter(boolean isRight) {
    	if(isRight) {
    		addParallel(new C_SetGriffSpeed(.2));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(125, .7, .74));

			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			
			//second
			addSequential(new C_DriveForwardRelative(-62, .7));
			addSequential(new C_TurnRelativeAngle(-60, .8));
			addSequential(new C_DriveForwardRelative(96, .7));
			
			//pickup
			addSequential(new C_SetIntakeState(true, true));
			addSequential(new C_GriffSqueeze(true));
			addSequential(new C_SetIntakeSpeed(1));
			addSequential(new C_SetGriffSpeed(.5));
			
			//addSequential(new C_BlindCubePickup());
			
			addSequential(new C_SetGriffSpeed(.2));
			addSequential(new C_SetIntakeSpeed(0));
			addSequential(new C_GriffSqueeze(false));
			addSequential(new C_SetIntakeState(false, false));
			
			//drive back in
			addSequential(new C_DriveForwardRelative(-30, .7));
			addParallel(C_MoveElevatorToPos.fromInches(40));
			addSequential(new C_TurnRelativeAngle(90, .8));
			addSequential(new C_DriveForwardRelative(52, .7));
			
			//spit
			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    	
    	else {
    		addParallel(new C_SetGriffSpeed(.2));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(125, .7, -.74));

			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
			
			//second
			addSequential(new C_DriveForwardRelative(-24, .7));
			addSequential(new C_TurnRelativeAngle(60, .8));  //
			addSequential(new C_DriveForwardRelative(10, .7));
			
			//pickup
			addSequential(new C_SetIntakeState(true, true));
			addSequential(new C_GriffSqueeze(true));
			addSequential(new C_SetIntakeSpeed(1));
			addSequential(new C_SetGriffSpeed(.5));
			
			addSequential(new C_DriveForwardRelative(0, .7));
			addSequential(new C_BlindCubePickup());
			
			addSequential(new C_SetIntakeSpeed(0));
			addSequential(new C_SetGriffSpeed(.2));
			addSequential(new C_GriffSqueeze(false));
			addSequential(new C_SetIntakeState(false, false));
			
			//drive back in
			addSequential(new C_DriveForwardRelative(-15, .7));  //
			addParallel(C_MoveElevatorToPos.fromInches(40));
			addSequential(new C_TurnRelativeAngle(-30, .8));
			
			
			addSequential(new C_DriveForwardRelative(30, .7));

			
			//spit
			addSequential(C_MoveElevatorToPos.fromInches(40));
			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    }
}
