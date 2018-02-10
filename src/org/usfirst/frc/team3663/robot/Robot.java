/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;

import org.usfirst.frc.team3663.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	public static SS_DriveTrain ss_drivetrain;

	public static RobotMap RobotMap;
	public static SS_Gyro ss_gyro;
	public static SS_CubeIntake ss_cubeIntake;
	public static SS_Griff ss_griff;
	public static SS_LimitSwitch ss_limitSwitch;
	public static SS_Camera ss_camera;

	// oi must be initilized last PLEASE

	public static int testCounter = 0;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.git test
	 */
	@Override
	public void robotInit() {
		// Initialize all subsystems
		ss_drivetrain = new SS_DriveTrain();
		ss_cubeIntake = new SS_CubeIntake();
		ss_griff = new SS_Griff();
		oi = new OI();
		Robot.ss_drivetrain.enableBrakeMode(true);
		// SS_DriveTrain.setEnc();

		// init gyro

	}

	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

}
