/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class C_Drive extends Command {
	public C_Drive() {
		requires(Robot.ss_drivetrain);
		
	}

	@Override
	protected void initialize() {

		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_drivetrain.drive.arcadeDrive(Robot.oi.driveStick.getRawAxis(1), Robot.oi.driveStick.getRawAxis(0)/1.25);
		
		Robot.ss_cubeIntake.spinIntake(Robot.oi.driveStick.getRawAxis(3));
		Robot.ss_griff.setGriffSpd(Robot.oi.driveStick.getRawAxis(2));
		//
		System.out.println(Robot.ss_drivetrain.getRight());
		System.out.println(Robot.ss_drivetrain.getLeft());
		//DriverStation.Alliance side = DriverStation.getInstance().getAlliance();
		//DriverStation.Alliance side = DriverStation.getInstance().getLocation();
		
		//System.out.println("RIGHT :  " + Robot.ss_drivetrain.getRight());
		//System.out.println("LEFT  :  " + Robot.ss_drivetrain.getLeft());
		//System.out.println(Robot.ss_griff.getAngle());
		

;
		

		
		
		
		//System.out.println("TOP:    " + Robot.ss_elevator.getTop());
		//System.out.println("BOTTOM:    " + Robot.ss_elevator.getBottom());
		// SS_Camera.initCam();
		// Robot.ss_limitSwitch.Limit();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

}
