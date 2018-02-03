/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.subsystems.*;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class C_IntakeMonitor extends Command {
	public C_IntakeMonitor() {
		requires(Robot.ss_cubeIntake);
		
	}
	protected void initialize() {
    }
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		System.out.println(Robot.ss_cubeIntake.getSwitchState());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

	}
}
