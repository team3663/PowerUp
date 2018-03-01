/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */

public class SS_DriveTrain extends Subsystem {

	// Wheels are 6.125"
	// 6.125 * pi = 19.242"
	// 256 ticks/rev
	// 256 / 19.242" = 13.304 ticks/in
	public static final double TICKS_PER_IN = 13.304;

	public WPI_VictorSPX left1 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_1);
	public WPI_VictorSPX left2 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_2);
	public WPI_VictorSPX left3 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_3);

	public WPI_VictorSPX right1 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_1);
	public WPI_VictorSPX right2 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_2);
	public WPI_VictorSPX right3 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_3);

	private final Encoder leftEnc = new Encoder(RobotMap.DRIVE_LEFT_ENC_1,
			RobotMap.DRIVE_LEFT_ENC_2);
	private final Encoder rightEnc = new Encoder(RobotMap.DRIVE_RIGHT_ENC_1,
			RobotMap.DRIVE_RIGHT_ENC_2);

	public DifferentialDrive drive = new DifferentialDrive(left1, right1);

	public static int inchesToTicks(double inches) {
		return (int) (inches * TICKS_PER_IN);
	}

	public SS_DriveTrain() {
		left2.follow(left1);				//Curtis: I see that you are setting the second motor to follow the first do you also need to do this for the third

		right2.follow(right1);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}

	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	public void driveForward(double speed) {
		drive.arcadeDrive(speed, 0);
	}

	/**
	 * @return position of left motor by ticks
	 */
	public int getLeft() {
		return leftEnc.get();
	}

	/**
	 * @return position of right motor by ticks
	 */
	public int getRight() {
		return rightEnc.get();
	}

	/**
	 * Turns in place
	 *
	 * @param turns
	 *            clockwise if positive
	 */
	public void turn(double speed) {
		drive.arcadeDrive(0, speed);
	}

}
