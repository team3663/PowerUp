package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Input/Output devices for the driver station
 */
public class OI {
	// TODO: last is for the limit switch
	public boolean last = true;

	public Joystick driveStick = new Joystick(0);

	public OI() {
		// Button exampleButton = new JoystickButton(driveStick, 1);
		// exampleButton.whenPressed(randomms);
		final Button intakeCubeCycle = new JoystickButton(driveStick, 3);
		intakeCubeCycle.whenPressed(new CG_CubeCycle());

		final Button griffSqz = new JoystickButton(driveStick, 1);
		griffSqz.whenPressed(new C_GriffSqz(true));
		griffSqz.whenReleased(new C_GriffSqz(false));

		/*
		 * Button intakeExtnd = new JoystickButton(driveStick, 2);
		 * intakeExtnd.whenPressed(new C_IntakeExtnd(true));
		 * intakeExtnd.whenReleased(new C_IntakeExtnd(true));
		 */
		final Button intakeSqz = new JoystickButton(driveStick, 4);
		intakeSqz.whenPressed(new C_IntakeSqz(true));
		intakeSqz.whenReleased(new C_IntakeSqz(false));

		final Button inGriff = new JoystickButton(driveStick, 5);
		inGriff.whenPressed(new C_SetGriffSpd(1));
		inGriff.whenReleased(new C_SetGriffSpd(0));

		final Button setIntakeSpd = new JoystickButton(driveStick, 6);
		setIntakeSpd.whenPressed(new C_SetIntakeSpd(-1));
		setIntakeSpd.whenReleased(new C_SetIntakeSpd(0));

		final Button outGriff = new JoystickButton(driveStick, 2);
		outGriff.whenPressed(new C_SetGriffSpd(-1));
		outGriff.whenReleased(new C_SetGriffSpd(0));
	}
}
/*
 * 1 = A 2 = B 3 = X 4 = Y 5 = LEFT-BUMPER 6 = RIGHT-BUMPER 7 = BACK 8 = START 9
 * = LEFT-STICK as a side note please refrain from using 9-10 10 = RIGHT-STICK
 * because the can cause issues with commands using the stick axis AXIS ARE AS
 * FOLLOWS 0 = LEFT-X-AXIS 1 = LEFT-Y-AXIS 2 = LEFT-TRIGGER 3 = RIGHT-TRIGGER 4
 * = RIGHT-X-AXIS 5 = RIGHT-Y-AXIS
 */