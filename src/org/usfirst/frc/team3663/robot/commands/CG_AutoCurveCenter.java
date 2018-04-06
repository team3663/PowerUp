package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoCurveCenter extends CommandGroup {

    public CG_AutoCurveCenter(boolean isRight) {
    	if(isRight) {
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(125, .7, .74));

			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    	else {
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(125, .7, -.74));

			addSequential(new C_SetGriffSpeed(-1));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    }
}