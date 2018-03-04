package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_AutoSelect extends Command {

	public C_AutoSelect() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.ss_autoSelect);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		final String leverPos = Robot.ss_autoSelect.getLeverPos();

		final double location = Robot.ss_autoSelect.getAngle();

		final char nearSwitch = leverPos.charAt(0);
		final char scale = leverPos.charAt(1);
		final char farSwitch = leverPos.charAt(2);
		
		final boolean left = false;
		final boolean right = true;

		////////////// DRIVE FORWARD ONLY TEST CODE////////////////////
		//new CG_AutoDriveFw().start();
		/*if(nearSwitch == 'R') {
			new CG_AutoRightSwitch(right);
		}*/

		/////////// NEW SELECT CODE////////////////////////////////////

		// add delays if you want, there aren't any
		// all values in the command groups should be perfected, they were set lazily
		// with eyeballing distances

		

		
		if(leverPos != "") {
			
			//LEFT
			if (location >= 75 && location <= 100) { // TODO fix errors here
				if (scale == 'L') {
					new CG_AutoHotScale(left).start();
				} else if (nearSwitch == 'L') {
					new CG_AutoHotSwitch(left).start();
				} else {
					new C_DriveForwardByTime(4, .5).start();
				}
			}
			// MIDDLE // give switch side
			if (location >= 40 && location <= 75) { // TODO fix errors here
				/*if (nearSwitch == 'L' && scale == 'L') {
					new CG_AutoMidSameSide(left).start();
				} else if (nearSwitch == 'R' && scale == 'R') {
					new CG_AutoMidSameSide(right).start();
				} else if (nearSwitch == 'L' && scale == 'R') {
					new CG_AutoMidDiffSide(left).start();
				} else if (nearSwitch == 'R' && scale == 'L') {
					new CG_AutoMidDiffSide(right).start();
				}*/
				
				new C_DriveForwardByTime(4, .5).start();
			}
			// RIGHT
			if (location >= 13 && location <= 40) {
				if (scale == 'R') {
					new CG_AutoHotScale(right).start();
				} else if (nearSwitch == 'R') {
					new CG_AutoHotSwitch(right).start();
				} else {
					new C_DriveForwardByTime(4, .5).start();
				}
			}
		}
	}


	@Override
	protected boolean isFinished() {
		
		return true;
	}
}
