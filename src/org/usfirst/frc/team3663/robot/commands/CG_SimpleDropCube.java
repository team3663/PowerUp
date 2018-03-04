package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_SimpleDropCube extends CommandGroup {

    public CG_SimpleDropCube() {
    	//Drive into the wall
    	addSequential(new C_SimpleDriveForward((int) (13.304 * 12 * 10.5), 0.8));

    	//Copy Pasted from CG_AutoHotSwitch
		addSequential(C_MoveElevatorToPos.fromInches(24.0));
		addSequential(new C_SetGriffSpeed(-1));
		addSequential(new C_Wait(500));
		addSequential(new C_SetGriffSpeed(0));
		addSequential(C_MoveElevatorToPos.fromInches(0.0));
    }
}
