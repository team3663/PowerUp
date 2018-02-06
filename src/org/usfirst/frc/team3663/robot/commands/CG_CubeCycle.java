package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_CubeCycle extends CommandGroup {

    public CG_CubeCycle() {
    	addSequential(new C_IntakeExtnd(true));
    	addSequential(new C_IntakeSqz(true));
    	addSequential(new C_SetIntakeSpd(.3));
    	addSequential(new C_SetGriffSpd(.3));
    	addSequential(new C_GriffSensor()); //wait for sensor
    	addSequential(new C_GriffSqz(true));
    	addSequential(new C_SetIntakeSpd(0));
    	addSequential(new C_SetGriffSpd(0));
    	addSequential(new C_IntakeSqz(false));
    	addSequential(new C_IntakeExtnd(false));
    	

    }
}
