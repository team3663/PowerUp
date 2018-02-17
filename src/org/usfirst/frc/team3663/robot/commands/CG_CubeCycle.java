package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_CubeCycle extends CommandGroup {

	public CG_CubeCycle() {
		addSequential(new C_IntakeExtend(true));
		addSequential(new C_IntakeSqueeze(true));
		addSequential(new C_SetIntakeSpeed(.3));
		addSequential(new C_SetGriffSpeed(.3));
		addSequential(new C_GriffSensor()); // wait for sensor
		addSequential(new C_SetIntakeSpeed(0));
		addSequential(new C_SetGriffSpeed(0));
		addSequential(new C_IntakeSqueeze(false));
		addSequential(new C_IntakeExtend(false));
	}
}
