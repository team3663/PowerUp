/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.commands.C_Drive;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */


public class SS_DriveTrain extends Subsystem {

	
	// Highest position lift should go
	private static final int LIFT_POS_MAX = 4000;
	
	public WPI_TalonSRX left        = new WPI_TalonSRX(1);
	public WPI_TalonSRX right       = new WPI_TalonSRX(3);
	public static WPI_TalonSRX lift = new WPI_TalonSRX(2);
	
	public DifferentialDrive drive //arcade drive
			= new DifferentialDrive(right, left);
	
	
	/**
	 * Drive backwards
	 * @param pVal power of robot
	 * @param pTim time (unused)
	 * 
	 */
	public void drivetest (double pVal) {
		left.set(-pVal);
		right.set(pVal);
	}
	
	public void turn(double pVel) {
		left.set(pVel);
		right .set(pVel);
	}
	
	/**
	 * Sets lift speed
	 * @param spd Lift speed
	 */
	public void liftest(double spd) {
		lift.set(spd);
	}
	
	/**
	 * Sets up encoder for use
	 */
	public static void initEnc(){
		lift.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public static int getLiftPos() {
		return lift.getSensorCollection().getQuadraturePosition();
	}
	
	public static void liftToTop() {
		// Keeps lift moving unless it reaches the highest
		//   it should go.
		int liftPos = getLiftPos();
		lift.set( (liftPos < LIFT_POS_MAX) ? 1 : 0);
	}
	
		/* Encoders may measure differently in the real world and in
		   simulation. In this example the robot moves 0.042 barleycorns
		   per tick in the real world, but the simulated encoders
		   simulate 360 tick encoders. This if statement allows for the
		   real robot to handle this difference in devices.
		   this is a test boi x2 */
		

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}

}
