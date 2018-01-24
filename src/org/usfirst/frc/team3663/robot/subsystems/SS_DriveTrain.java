/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_Drive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */


public class SS_DriveTrain extends Subsystem {

	public static int liftPos;
	

	public WPI_TalonSRX left
			= new WPI_TalonSRX(Robot.RobotMap.left);
	public WPI_TalonSRX right
			= new WPI_TalonSRX(Robot.RobotMap.right);
	public static WPI_TalonSRX lift
			= new WPI_TalonSRX(Robot.RobotMap.lift);
	
	public DifferentialDrive drive //arcade drive
			= new DifferentialDrive(right, left);
	
	
	public void drivetest (double pVal, double pTim) {
		pVal = -pVal; //reverse motors
		left.set(pVal);
		right.set(pVal);
	}
	public void liftest (double spd) {
		lift.set(spd);
	}
	public static void setEnc(){
		
		lift.getSensorCollection().setQuadraturePosition(0, 0);
	}

	public static void encoder() {
		int val = lift.getSensorCollection().getQuadraturePosition();
		liftPos = val;
		System.out.println(val);
		
	}
	public static void liftToPos() {
		if (liftPos<4000) {
			lift.set(1);
		}
		else {
			lift.set(0);
		}
		
	}
	
		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		//this is a test boi x2
		

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */


	/**
	 * Tank style driving for the DriveTrain.
	 *
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */

	/**
	 * Get the robot's heading.
	 *
	 * @return The robots heading in degrees.
	 */


	/**
	 * Get the average distance of the encoders since the last reset.
	 *
	 * @return The distance driven (average of left and right encoders).
	 */

}
