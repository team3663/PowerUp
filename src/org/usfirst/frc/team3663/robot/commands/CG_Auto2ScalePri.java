package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_Auto2ScalePri extends CommandGroup {

    public CG_Auto2ScalePri() {
    	//addSequential(new C_DriveForwardRelative(25, .6));
		
		//pickup and turn
		addSequential(new C_VisionSeekCube(.45));
		//addSequential(new C_DriveForwardRelative(45, .6));
		

		addSequential(C_MoveElevatorToPos.fromInches(80.0));
		addSequential(new C_SetGriffSpeed(-.5));
		addSequential(new C_Wait(200));
		addSequential(new C_SetGriffSpeed(0));
		addSequential(C_MoveElevatorToPos.fromInches(0.0));
    }
}
