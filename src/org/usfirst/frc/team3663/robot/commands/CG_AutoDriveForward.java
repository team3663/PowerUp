package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoDriveForward extends CommandGroup {

    public CG_AutoDriveForward() {
    	addSequential(new C_DriveForwardRelative(145, .7));
    }
}
