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
	public static final int CUBE_ROTATOR_SENSOR = -1;

	// Talons
	public static final int DRIVE_LEFT_1 = 11;
	public static final int DRIVE_LEFT_2 = 12;
	public static final int DRIVE_LEFT_3 = 13;
	
	public static final int DRIVE_RIGHT_1 = 8;
	public static final int DRIVE_RIGHT_2 = 9;
	public static final int DRIVE_RIGHT_3 = 10;

	public static final int ELEVATOR_1 = 2;
	public static final int ELEVATOR_2 = 3;

	public static final int CUBE_INTAKE_LEFT = 51;
	public static final int CUBE_INTAKE_RIGHT = 43;
	
	public static final int CUBE_SHOOTER = 54;
	public static final int CUBE_ROTATOR = 53;
	
	public static final int CLIMBER_1 = 1;
	public static final int CLIMBER_2 = 4;
	

	// Pneumatics
	public static final int CLIMBER_PNEUM_FWD = 3; // TODO: figure out this order
	public static final int CLIMBER_PNEUM_REV = 2;
	
	public static final int GRIFF_SQUEEZE_FWD = 1;
	public static final int GRIFF_SQUEEZE_REV = 0;

	public static final int CUBE_INTAKE_LIFT_FOWARD = 7;
	public static final int CUBE_INTAKE_LIFT_REVERSE = 6;

}
