/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public boolean last = true;
	private Joystick m_joystick = new Joystick(0);

	public OI() {
		// Put Some buttons on the SmartDashboard
			}

	public double getStickX() {
		return m_joystick.getRawAxis(1);
	}
	public double getStickY() {
		return m_joystick.getRawAxis(0);
	}
	public double getLiftY() {
		return m_joystick.getRawAxis(5);
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