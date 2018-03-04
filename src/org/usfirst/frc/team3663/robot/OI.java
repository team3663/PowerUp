package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Input/Output devices for the driver station
 */
public final class OI {
	// TODO: last is for the limit switch
	public boolean last = true;

	public Joystick driveStick = new Joystick(0);
	public Joystick op = new Joystick(1);

	public OI() {
		// Button exampleButton = new JoystickButton(driveStick, 1);
		// exampleButton.whenPressed(randoms);
		final Button intakeCubeCycle = new JoystickButton(driveStick, 3);
		intakeCubeCycle.whenPressed(new CG_CubeCycle());

		final Button griffSqz = new JoystickButton(driveStick, 1);
		griffSqz.whenPressed(new C_GriffSqueeze(true));
		griffSqz.whenReleased(new C_GriffSqueeze(false));

		final Button intakePneumatics = new JoystickButton(driveStick, 7);
		intakePneumatics.whenPressed(new C_SetIntakeState(true, true));
		intakePneumatics.whenReleased(new C_SetIntakeState(false, false));

		/*
		 * Button intakeExtnd = new JoystickButton(driveStick, 2);
		 * intakeExtnd.whenPressed(new C_IntakeExtnd(true));
		 * intakeExtnd.whenReleased(new C_IntakeExtnd(true));
		 */

		final Button inGriff = new JoystickButton(driveStick, 2);
		inGriff.whenPressed(new C_SetGriffSpeed(1));
		inGriff.whenReleased(new C_SetGriffSpeed(0));

		final Button setIntakeSpd = new JoystickButton(driveStick, 6);
		setIntakeSpd.whenPressed(new C_SetIntakeSpeed(-1));
		setIntakeSpd.whenReleased(new C_SetIntakeSpeed(0));

		final Button outGriff = new JoystickButton(driveStick, 5);
		outGriff.whenPressed(new C_SetGriffSpeed(-1));
		outGriff.whenReleased(new C_SetGriffSpeed(0));

		//final Button lowGoal = new JoystickButton(driveStick, 8);
		//lowGoal.whenPressed(new C_SimpleMoveElevator(500));
		
		final Button climberPneum = new JoystickButton(op, 3);
		climberPneum.whenPressed(new C_SetClimber(true));
		climberPneum.whenReleased(new C_SetClimber(false));
		
		final Button climberTurn = new JoystickButton(op, 6);
		climberTurn.whenPressed(new C_SetClimber(-.5));
		climberTurn.whenReleased(new C_SetClimber(0));
		
		
		final Button rotateTheCube = new JoystickButton(op, 5);
		rotateTheCube.whileHeld(new C_RotateCube());
		
		//pneu = x winch = right trigg
	}
}
/*
 * 1 = A
 *
 * 2 = B
 *
 * 3 = X
 *
 * 4 = Y
 *
 * 5 = LEFT-BUMPER
 *
 * 6 = RIGHT-BUMPER
 *
 * 7 = BACK
 *
 * 8 = START
 *
 * 9 = LEFT-STICK
 *
 * 10 = RIGHT-STICK
 *
 * as a side note please refrain from using 9-10 because the can cause issues
 * with commands using the stick axis AXIS ARE AS FOLLOWS
 *
 * 0 = LEFT-X-AXIS
 *
 * 1 = LEFT-Y-AXIS
 *
 * 2 = LEFT-TRIGGER
 *
 * 3 = RIGHT-TRIGGER
 *
 * 4 = RIGHT-X-AXIS
 *
 * 5 = RIGHT-Y-AXIS
 */