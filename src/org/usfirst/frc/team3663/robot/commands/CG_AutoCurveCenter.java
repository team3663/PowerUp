package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_AutoCurveCenter extends CommandGroup {

    public CG_AutoCurveCenter(boolean isRight) {
    	if(isRight) {
    		addParallel(new C_SetGriffSpeed(.2));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(120, .7, .74));

			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    	else {
    		addParallel(new C_SetGriffSpeed(.2));
			addParallel(C_MoveElevatorToPos.fromInches(40.0));
    		addSequential(new C_DriveCurve(125, .7, -.74));

			addSequential(new C_SetGriffSpeed(-.8));
			addSequential(new C_Wait(200));
			addSequential(new C_SetGriffSpeed(0));
			addParallel(C_MoveElevatorToPos.fromInches(0.0));
    	}
    }
}
