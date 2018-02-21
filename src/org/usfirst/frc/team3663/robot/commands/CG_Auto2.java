package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2 extends CommandGroup {

    public CG_Auto2() {
    	addSequential(new C_DriveForwardToPosition(90, .5));
    	addSequential(new C_TurnRelativeAngle(-60, .3));
    	addSequential(new C_DriveForwardToPosition(20, .25));
    	addSequential(new C_MoveElevatorToPos(24.0));
    	addSequential(new C_SetGriffSpeed(-1));
    	addSequential(new C_MoveElevatorToPos(0.0));
    	addSequential(new C_TurnRelativeAngle(-30, .3));
    	addSequential(new C_DriveForwardToPosition(65, .4));
    	addSequential(new C_TurnRelativeAngle(90, .4));
    	addSequential(new C_DriveForwardToPosition(36, .4));
    }
}
