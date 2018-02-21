package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto3 extends CommandGroup {

    public CG_Auto3() {
    	addSequential(new C_DriveForwardToPosition(130, .5));
    	//addSequential(new C_TurnRelativeAngle(90, .3));
    }
}
