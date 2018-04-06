package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 1. Arms intake wheels and sets them moving 2. Waits for the griffon to sense
 * the cube 3. Stops the wheels, moves cube up, gets intake out of the way
 */
public class CG_CubeCycle extends CommandGroup {

	public CG_CubeCycle() { 
<<<<<<< HEAD
			// Arm cube intake
			addSequential(new C_SetIntakeState(true, true));
			addSequential(new C_GriffSqueeze(true));
			addSequential(new C_SetIntakeSpeed(1));
			addSequential(new C_SetGriffSpeed(.5));
	
			// Wait for sensor
			addSequential(new C_GriffSensor());
	
			// Disarm cube intake
			addSequential(new C_SetIntakeSpeed(0));

			addSequential(new C_GriffSqueeze(false));
			addSequential(new C_SetIntakeState(false, false));
=======
		// Arm cube intake
		addSequential(new C_SetIntakeState(true, true));
		addSequential(new C_GriffSqueeze(true));
		addSequential(new C_SetIntakeSpeed(1));
		addSequential(new C_SetGriffSpeed(.5));

		// Wait for sensor
		addSequential(new C_GriffSensor());

		// Disarm cube intake
		addSequential(new C_SetIntakeSpeed(0));

		addSequential(new C_GriffSqueeze(false));
		addSequential(new C_SetIntakeState(false, false));
>>>>>>> 496e29f7867eea0b06d9aa831b7dcd185326e3fc
	
	}
}
