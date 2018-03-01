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
		
		final int location = DriverStation.getInstance().getLocation();
		
		final char nearSwitch = leverPos.charAt(0);
		final char scale = leverPos.charAt(1);
		final char farSwitch = leverPos.charAt(2);
		
		final boolean left = false;
		final boolean right = true;
		
		//////////////DRIVE FORWARD ONLY TEST CODE////////////////////
		new CG_AutoDriveFw().start();

		///////////NEW SELECT CODE////////////////////////////////////
		
		// add delays if you want, there aren't any
		// all values in the command groups should be perfected, they were set lazily with eyeballing distances
		
		//LEFT
		if(location == 0) {
			if (nearSwitch == 'L' && scale == 'L') {
				//new CG_AutoBothHot(left).start();
			} else if (scale == 'L'){
				//new CG_AutoHotScale(left).start();
			} else if (nearSwitch == 'L') {
				new CG_AutoHotSwitch(left).start();
			} else {
				new CG_AutoDriveFw().start();
			}
		}
		//MIDDLE // give switch side
		if(location == 1) {
			if(nearSwitch == 'L' && scale == 'L') {
				new CG_AutoMidSameSide(left).start();
			} else if (nearSwitch == 'R' && scale == 'R') {
				new CG_AutoMidSameSide(right).start();
			} else if (nearSwitch == 'L' && scale == 'R') {
				//new CG_AutoMidDiffSide(left).start();
			} else if (nearSwitch == 'R' && scale == 'L') {
				//new CG_AutoMidDiffSide(right).start();
			}
		}
		//RIGHT
		if(location == 2) {
			if (nearSwitch == 'R' && scale == 'R') {
				//new CG_AutoBothHot(right).start();
			} else if (scale == 'R'){
				//new CG_AutoHotScale(right).start();
			} else if (nearSwitch == 'R') {
				new CG_AutoHotSwitch(right).start();
			} else {
				new CG_AutoDriveFw().start();
			}
		}
		///////////PROBABLY SCRAP THIS CODE///////////////////////////
		/*if (leverPos.charAt(0) == 'L') {
			if (DriverStation.getInstance().getLocation() == 1) {
				new CG_Auto1().start();
			}
			if (DriverStation.getInstance().getLocation() == 2) {
				new CG_Auto2().start();
			}
			if (DriverStation.getInstance().getLocation() == 3) {
				new CG_Auto3().start();
			}
		} else {
			if (DriverStation.getInstance().getLocation() == 1) {
				new CG_Auto4().start();
			}
			if (DriverStation.getInstance().getLocation() == 2) {
				new CG_Auto5().start();
			}
			if (DriverStation.getInstance().getLocation() == 3) {
				new CG_Auto6().start();
			}
		}*/

	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
