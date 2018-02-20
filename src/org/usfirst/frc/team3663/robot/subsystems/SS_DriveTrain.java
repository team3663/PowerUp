/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.SpeedControllerGroup;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */

public class SS_DriveTrain extends Subsystem {
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}
	 
	WPI_TalonSRX left1 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_1);
	WPI_TalonSRX left2 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_2);
	WPI_TalonSRX left3 = new WPI_TalonSRX(RobotMap.DRIVE_LEFT_3);
	
	WPI_TalonSRX right1 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_1);
	WPI_TalonSRX right2 = new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_1);
	WPI_TalonSRX right3= new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_1);

	public DifferentialDrive drive // arcade drive
			= new DifferentialDrive(right1, left1);

	public void initDrive() {
		left2.follow(left1);
		left3.follow(left1);
		
		right2.follow(right1);
		right3.follow(right1);

		
		left1.setInverted(false);
		left2.setInverted(false);
		left3.setInverted(false);
		
		right1.setInverted(false);
		right2.setInverted(false);
		right3.setInverted(false);
	}
	
	public void drive(double spd) {
		//foward  +
		//backward   -
		drive.arcadeDrive(spd, 0);
	}

	public void turn(double spd) {
		//left  -
		//right  +
		drive.arcadeDrive(0, spd);

	}




}
