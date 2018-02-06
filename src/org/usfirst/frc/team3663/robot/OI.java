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
		//Button exampleButton = new JoystickButton(driveStick, 1);
		//exampleButton.whenPressed(randomms); 
		Button intakeCubeCycle = new JoystickButton(driveStick, 4);
		intakeCubeCycle.whenPressed(new CG_CubeCycle());
		
		Button shootCube = new JoystickButton(driveStick, 3);
		shootCube.whenPressed(new C_ShootCube());
	}
}
/*
* 1 = A
* 2 = B
* 3 = X
* 4 = Y
* 5 = LEFT-BUMPER
* 6 = RIGHT-BUMPER
* 7 = BACK
* 8 = START
* 9 = LEFT-STICK		as a side note please refrain from using 9-10 
* 10 = RIGHT-STICK		because the can cause issues with commands using
* 						the stick axis	
* AXIS ARE AS FOLLOWS
* 0 = LEFT-X-AXIS
* 1 = LEFT-Y-AXIS
* 2 = LEFT-TRIGGER
* 3 = RIGHT-TRIGGER
* 4 = RIGHT-X-AXIS
* 5 = RIGHT-Y-AXIS
*/