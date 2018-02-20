package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto6 extends CommandGroup {

    public CG_Auto6() {
    	addSequential(new C_DriveForwardToPosition(150, .5));
    	addSequential(new C_MoveElevatorToPos(24.0));
    	//addSequential(new C_GriffRotate(degrees to left));
    	addSequential(new C_SetGriffSpeed(-1));
    }
}
