package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_SimpleDropCube extends CommandGroup {

    public CG_SimpleDropCube() {
    	//Drive into the wall
    	addParallel(C_MoveElevatorToPos.fromInches(24.0));
    	addSequential(C_DriveForwardSimple.fromInches(60, 0.7)); //TODO this needs to not be 2ft

    	//Copy Pasted from CG_AutoHotSwitch
    	addSequential(new C_Wait(500));
		addSequential(new C_SetGriffSpeed(1));
		addSequential(new C_Wait(250));
		addSequential(new C_SetGriffSpeed(0));
		addSequential(C_MoveElevatorToPos.fromInches(0.0));
		//addSequential(C_DriveForwardSimple.fromInches(-12, 0.7));
    }
}
