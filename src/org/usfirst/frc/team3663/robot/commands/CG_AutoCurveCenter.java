package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoCurveCenter extends CommandGroup {

    public CG_AutoCurveCenter(boolean isRight) {
    	if(isRight) {
    		addSequential(new C_DriveCurve(145, .6, .6));
    	}
    	else {
    		addSequential(new C_DriveCurve(145, .6, -.6));
    	}
    }
}
