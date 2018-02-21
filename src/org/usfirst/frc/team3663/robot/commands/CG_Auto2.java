package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2 extends CommandGroup {

    public CG_Auto2() {
    	addSequential(new C_DriveForwardRelative(90, .5));
    	addSequential(new C_TurnRelativeAngle(-60, .3));
    	addSequential(new C_DriveForwardRelative(20, .25));
    	addSequential(C_MoveElevatorToPos.fromInches(24.0));
    	addSequential(new C_SetGriffSpeed(-1));
    	addSequential(C_MoveElevatorToPos.fromInches(0.0));
    	addSequential(new C_TurnRelativeAngle(-30, .3));
    	addSequential(new C_DriveForwardRelative(65, .4));
    	addSequential(new C_TurnRelativeAngle(90, .4));
    	addSequential(new C_DriveForwardRelative(36, .4));
    }
}
