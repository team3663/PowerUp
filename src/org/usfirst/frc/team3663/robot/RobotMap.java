package org.usfirst.frc.team3663.robot;

/**
 * If the motor number is 19 that means that it will not be assigned to any
 * thing
 */
public class RobotMap {
	
	/// You expected analog ports, but it's me!-- DIO!
	public static final int LIMIT_SWITCH_ELEVATOR_TOP = 0;
	public static final int LIMIT_SWITCH_ELEVATOR_BOTTOM = 2;
	public static final int LIMIT_SWITCH_CUBE_PRESENT = 1; // Optical sensor for griff
	
	
	// Encoders
	public static final int DRIVE_RIGHT_ENC_1 = 4;
	public static final int DRIVE_RIGHT_ENC_2 = 5;
	public static final int DRIVE_LEFT_ENC_1 = 6;
	public static final int DRIVE_LEFT_ENC_2 = 7;
	public static final int ELEVATOR_ENC_1 = 9;
	public static final int ELEVATOR_ENC_2 = 10;

	
	/// Analog
	public static final int CUBE_ROTATOR_SENSOR = 0; // Potentiometer

	
	/// CAN BUS
	// Talons
	public static final int CLIMBER_1 = 1;
	public static final int CLIMBER_2 = 4;
	
	public static final int ELEVATOR_1 = 2;
	public static final int ELEVATOR_2 = 3;
	
	public static final int DRIVE_RIGHT_1 = 8;
	public static final int DRIVE_RIGHT_2 = 9;
	public static final int DRIVE_RIGHT_3 = 10;
	
	public static final int DRIVE_LEFT_1 = 11;
	public static final int DRIVE_LEFT_2 = 12;
	public static final int DRIVE_LEFT_3 = 13;

	public static final int CUBE_INTAKE_LEFT = 0;
	public static final int CUBE_INTAKE_RIGHT = 5;
	
	public static final int CUBE_SHOOTER = 6;
	public static final int CUBE_ROTATOR = 7;

	// Pneumatics
	public static final int GRIFF_SQUEEZE_FWD = 0;
	public static final int GRIFF_SQUEEZE_REV = 1;
	
	public static final int CLIMBER_PNEUM_FWD = 3;
	public static final int CLIMBER_PNEUM_REV = 2;

	public static final int INTAKE_LIFT_FOWARD = 7;
	public static final int INTAKE_LIFT_REVERSE = 5;
	
	public static final int INTAKE_SQZ_FOWARD = 6;
	public static final int INTAKE_SQZ_REVERSE = 4;
	

}
