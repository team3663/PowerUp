package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 1. Arms intake wheels and sets them moving 2. Waits for the griffon to sense
 * the cube 3. Stops the wheels, moves cube up, gets intake out of the way
 */
public class CG_CubeCycle extends CommandGroup {

	public CG_CubeCycle() {
		// Arm cube intake
		addSequential(new C_SetIntakeState(true, true));
		addSequential(new C_GriffSqueeze(true));
		addSequential(new C_SetIntakeSpeed(.7));
		addSequential(new C_SetGriffSpeed(.7));

		// Wait for sensor
		addSequential(new C_GriffSensor());

		// Disarm cube intake
		addSequential(new C_SetIntakeSpeed(0));
		addSequential(new C_SetGriffSpeed(0));
		// addSequential(new C_MoveElevatorToPos(24)); // Moves cube up 24"
		addSequential(new C_GriffSqueeze(false));
		addSequential(new C_SetIntakeState(false, false));
	}
}
