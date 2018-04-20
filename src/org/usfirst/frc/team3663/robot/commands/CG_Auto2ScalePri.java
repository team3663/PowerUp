package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2ScalePri extends CommandGroup {

    public CG_Auto2ScalePri() {
    	addSequential(new C_DriveForwardRelative(25, .6));
		
		//pickup and turn
		
		addSequential(new C_VisionSeekCube(.45));
		addSequential(new C_DriveForwardRelative(-15, .6));
		
		addSequential(new C_TurnRelativeAngle(180, .8));
    }
}
