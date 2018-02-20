/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */

public class SS_Gearbox extends Subsystem {
	
	private WPI_TalonSRX left1 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_1);
	private WPI_TalonSRX left2 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_2);
	private WPI_TalonSRX left3 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_3);
	
	private WPI_TalonSRX right1 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_1);
	private WPI_TalonSRX right2 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_2);
	private WPI_TalonSRX right3 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_3);
	
	private DifferentialDrive drive = new DifferentialDrive(left1, right1);
	
	public SS_Gearbox() {
		left2.follow(left1);
		left3.follow(left1);
		
		right2.follow(right1);
		right3.follow(right1);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	
	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	public void driveForward(double speed) {
		drive.arcadeDrive(speed, 0);
	}
	
	/**
	 * Turns in place
	 * @param turns clockwise if positive
	 */
	public void turn(double speed) {
		drive.arcadeDrive(0, speed);
	}



}
