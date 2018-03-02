package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoMidDiffSide extends CommandGroup {

    public CG_AutoMidDiffSide(boolean switchRight) {
    	if(switchRight) {
    		addSequential(new C_DriveForwardRelative(90, .5));
    		addSequential(new C_TurnRelativeAngle(60, .3));
    		addSequential(new C_DriveForwardRelative(20, .25));
    		addSequential(C_MoveElevatorToPos.fromInches(24.0));
    		addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(-175, .3));
			addSequential(new C_DriveForwardRelative(150, .4));
			addSequential(new C_TurnRelativeAngle(115, .4));
			addSequential(new C_DriveForwardRelative(50, .4));
    	} else {
    		addSequential(new C_DriveForwardRelative(90, .5));
    		addSequential(new C_TurnRelativeAngle(-60, .3));
    		addSequential(new C_DriveForwardRelative(20, .25));
    		addSequential(C_MoveElevatorToPos.fromInches(24.0));
    		addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(500));
			addSequential(new C_SetGriffSpeed(0));
			addSequential(C_MoveElevatorToPos.fromInches(0.0));
			addSequential(new C_TurnRelativeAngle(175, .3));
			addSequential(new C_DriveForwardRelative(150, .4));
			addSequential(new C_TurnRelativeAngle(-115, .4));
			addSequential(new C_DriveForwardRelative(50, .4));
    	}
	}
}
