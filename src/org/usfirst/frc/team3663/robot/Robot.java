/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot;

import org.usfirst.frc.team3663.robot.commands.*;
import org.usfirst.frc.team3663.robot.commands.C_SetIntakeState;
import org.usfirst.frc.team3663.robot.subsystems.SS_AutoSelect;
import org.usfirst.frc.team3663.robot.subsystems.SS_Camera;
import org.usfirst.frc.team3663.robot.subsystems.SS_Climber;
import org.usfirst.frc.team3663.robot.subsystems.SS_CubeIntake;
import org.usfirst.frc.team3663.robot.subsystems.SS_DriveTrain;
import org.usfirst.frc.team3663.robot.subsystems.SS_Elevator;
import org.usfirst.frc.team3663.robot.subsystems.SS_Griff;
import org.usfirst.frc.team3663.robot.subsystems.SS_Gyro;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

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
	
	public static NetworkTableInstance nti;
	public static NetworkTable autoControlTable;

	private Command auto;
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
		ss_camera = new SS_Camera();
		
		oi = new OI(); // oi must be initilized last PLEASE

		nti = NetworkTableInstance.getDefault();
		autoControlTable = nti.getTable("hashboard");

		auto = null;
		
	}
	
	@Override
	public void autonomousInit() {
		Robot.ss_gyro.hardResetGyro();
		
		auto = new C_AutoWait((int) autoControlTable.getEntry("autoChoice").getDouble(-1));
		
		
		//System.out.println( "TRENTS STUFF :    " + autoControlTable.getEntry("autoChoice").getDouble(-1));
		Robot.ss_drivetrain.enableBreakMode(true);
		new C_SetIntakeState(false, false).start();
		auto.start();
		
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
		if(auto != null)
			auto.cancel();
		Robot.ss_drivetrain.enableBreakMode(false);
		//Robot.ss_camera.turnLightOn(1);
		
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
	@Override
	public void disabledInit() {
		Robot.ss_gyro.fakeReset();
		Robot.ss_gyro.hardResetGyro();
		
	}

}
