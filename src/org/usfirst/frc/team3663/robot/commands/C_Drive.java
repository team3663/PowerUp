/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3663.robot.Robot;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class C_Drive extends Command {
	public C_Drive() {
		requires(Robot.ss_griff);
		requires(Robot.ss_drivetrain);
		requires(Robot.ss_elevator);

	}

	@Override
	protected void initialize() {
		Robot.ss_elevator.initEnc();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.ss_drivetrain.drive.arcadeDrive(Robot.oi.driveStick.getRawAxis(1), Robot.oi.driveStick.getRawAxis(0));
		Robot.ss_elevator.set(Robot.oi.driveStick.getRawAxis(5));
		//System.out.println(Robot.ss_griff.getSwitchState());
		// SS_Camera.initCam();
		// Robot.ss_limitSwitch.Limit();
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
