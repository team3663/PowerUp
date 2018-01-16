package org.usfirst.frc.team3663.robot;

/**
 * If the motor number is 19 that means that it will not be assigned to any thing
 */
public class RobotMap {
	
/***DRIVETRAIN PORTS***/	


	public static int limitSwitch = 0;
/***PICKUP PORTS***/	
	//motors

	
/***SHOOTER PORTS***/
	//motors

	//digtial Input

	//pneumatic

	
/***LIFT PORTS***/
	//motor
	public static int liftFeedMotor = 8;
	public static int liftYellowMotor = 9;
	
/***CLIMBER PORTS***/
	//motor
	public static int climberMotor = 4;
	public static int climberMotor2 = 5;
	
/***GearPickup***/
	//motors
	public static int gearPickupMotor = 11;
	//pneumatics
	public static int gearMain = 0;
	public static int gearPickupUpOne = 2;
	public static int gearPickupUpTwo = 3;
	public static int gearPickupCloseOne = 0;
	public static int gearPickupCloseTwo = 1;
	//DIO

	public static int gearTrigger = 8;
	//Relay

	public static int pickupLED= 0; //0

/***AutoPorts***/
	public static int autoAnalog = 0;
}
