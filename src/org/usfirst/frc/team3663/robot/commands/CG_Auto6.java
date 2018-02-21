package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto6 extends CommandGroup {

    public CG_Auto6() {
    	addSequential(new C_DriveForwardRelative(120, .5));
    	addSequential(C_MoveElevatorToPos.fromInches(24.0));
    	//addSequential(new C_GriffRotate(degrees to left));
    	addSequential(new C_SetGriffSpeed(-1));
    	addSequential(new C_Wait(500));
    	addSequential(new C_SetGriffSpeed(0));
    	addSequential(C_MoveElevatorToPos.fromInches(0.0));
    	addSequential(new C_DriveForwardRelative(15, .3));
    }
}
