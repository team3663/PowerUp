/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardByTime;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardRelative;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardSimple;
import org.usfirst.frc.team3663.robot.commands.C_SetIntakeState;
import org.usfirst.frc.team3663.robot.commands.CG_SimpleDropCube;
import org.usfirst.frc.team3663.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//   |   || 
	//  ||   |__
	public static OI oi;
	public static SS_DriveTrain ss_drivetrain;

	public static RobotMap RobotMap;
	public static SS_Gyro ss_gyro;
	public static SS_CubeIntake ss_cubeIntake;
	public static SS_Griff ss_griff;
	public static SS_Camera ss_camera;
	public static SS_Elevator ss_elevator;
	public static SS_AutoSelect ss_autoSelect;
	public static SS_Climber ss_climber;

	private Command driveForward;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.git test
	 */
	@Override
	public void robotInit() {
		// Initialize all subsystems
		ss_elevator = new SS_Elevator();
		ss_drivetrain = new SS_DriveTrain();
		ss_cubeIntake = new SS_CubeIntake();
		ss_griff = new SS_Griff();
		ss_gyro = new SS_Gyro();
		ss_autoSelect = new SS_AutoSelect();
		ss_climber = new SS_Climber();
		
		oi = new OI(); // oi must be initilized last PLEASE

		//driveForward = new C_DriveForwardByTime(4, .5);
		//driveForward = new C_DriveForwardSimple.fromInches ( 1000, 0.8);
		//driveForward = new C_SimpleDropCube();

		// SS_DriveTrain.setEnc();
	}

	// private final Command driveForward = new C_DriveForwardByTime(5, .5);
	@Override
	public void autonomousInit() {
		new C_SetIntakeState(false, false).start();
		driveForward.start();
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
		//driveForward.cancel();
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
