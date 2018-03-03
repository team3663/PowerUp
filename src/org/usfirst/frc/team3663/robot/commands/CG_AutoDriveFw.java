package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoDriveFw extends CommandGroup {

	public CG_AutoDriveFw() {
		addSequential(new C_DriveForwardRelative(145, .5));
	}
}
