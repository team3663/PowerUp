package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoSpin extends CommandGroup {

    public CG_AutoSpin() {
    	addSequential(C_DriveForwardSimple.fromInches(36, .3));
    	addSequential(new C_Spin(12));
    }
}
