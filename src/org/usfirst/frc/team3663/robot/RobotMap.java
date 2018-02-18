package org.usfirst.frc.team3663.robot;

/**
 * If the motor number is 19 that means that it will not be assigned to any
 * thing
 */
public class RobotMap {
	// Digital Inputs
	public static final int LIMIT_SWITCH_ELEVATOR_TOP = 1;
	public static final int LIMIT_SWITCH_ELEVATOR_BOTTOM = -1;
	public static final int LIMIT_SWITCH_INTAKE = 0;

	// Talons
	public static final int DRIVE_LEFT_1 = 0;
	public static final int DRIVE_LEFT_2 = -1;
	public static final int DRIVE_LEFT_3 = -1;
	
	public static final int DRIVE_RIGHT_1 = 1;
	public static final int DRIVE_RIGHT_2 = -1;
	public static final int DRIVE_RIGHT_3 = -1;

	public static final int LIFT = 35;
	public static final int ELEVATOR = 1;

	public static final int CUBE_INTAKE_LEFT = 51;
	public static final int CUBE_INTAKE_RIGHT = 3;
	public static final int CUBE_SHOOTER = 2;
	public static final int CUBE_ROTATOR = 53;
	public static final int CUBE_ROTATOR_SENSOR = -1;

	// Pneumatics
	public static final int CUBE_SHOOTER_FORWARD = 3;
	public static final int CUBE_SHOOTER_REVERSE = 2;

	public static final int CUBE_INTAKE_FORWARD = 1;
	public static final int CUBE_INTAKE_REVERSE = 0;

	public static final int CUBE_INTAKE_LIFT_FOWARD = 7;
	public static final int CUBE_INTAKE_LIFT_REVERSE = 6;

}
