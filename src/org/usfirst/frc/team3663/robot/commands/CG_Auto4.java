package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto4 extends CommandGroup {

	public CG_Auto4() {
		addSequential(new C_DriveForwardRelative(130, .5));
		// addSequential(new C_TurnRelativeAngle(-90, .3));
	}
}
