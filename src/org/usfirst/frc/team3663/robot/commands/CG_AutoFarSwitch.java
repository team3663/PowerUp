package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoFarSwitch extends CommandGroup {

    public CG_AutoFarSwitch(boolean startRight) {
    	if(startRight) {
	        addSequential(new C_DriveForwardRelative(130, .7));
	        addSequential(new C_TurnRelativeAngle(-90, .7));
	        addParallel(C_MoveElevatorToPos.fromInches(30));
	        addSequential(new C_DriveForwardRelative(130, .7));
	        addSequential(new C_TurnRelativeAngle(-90, .7));
	        addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
        } else {
	        addSequential(new C_DriveForwardRelative(130, .7));
	        addSequential(new C_TurnRelativeAngle(90, .7));
	        addParallel(C_MoveElevatorToPos.fromInches(30));
	        addSequential(new C_DriveForwardRelative(130, .7));
	        addSequential(new C_TurnRelativeAngle(90, .7));
	        addSequential(new C_Wait(400));
			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(300));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
        }
    }
}
