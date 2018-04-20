package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_CubeIn extends CommandGroup {

    public CG_CubeIn() {
		addSequential(new C_SetIntakeState(true, true));
		addSequential(new C_SetIntakeSpeed(1));

    }
}
