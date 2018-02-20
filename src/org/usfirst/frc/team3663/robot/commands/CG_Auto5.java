package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto5 extends CommandGroup {

    public CG_Auto5() {
    	addSequential(new C_DriveForwardToPosition(90, .5));
    	addSequential(new C_TurnRelativeAngle(60, .3));
    	addSequential(new C_DriveForwardToPosition(20, .25));
    	addSequential(new C_SetGriffSpeed(-1));
    }
}
