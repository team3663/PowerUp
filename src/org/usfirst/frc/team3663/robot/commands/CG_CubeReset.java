package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_CubeReset extends CommandGroup {

    public CG_CubeReset() {
		addSequential(new C_SetIntakeSpeed(0));

		addSequential(new C_GriffSqueeze(false));
		addSequential(new C_SetIntakeState(false, false));
    }
}
