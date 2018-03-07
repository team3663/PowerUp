package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;


public class C_AutoSelect extends Command {
	
	private int counter = 0;
	
	public C_AutoSelect() {
		requires(Robot.ss_autoSelect);
	}


	@Override
	protected boolean isFinished() {
		if(Robot.ss_autoSelect.runAuto()) {
			return true;
		} else if (!Robot.ss_autoSelect.runAuto()) {
			counter++;
		}
		if(counter == 4) {
			C_DriveForwardSimple.fromInches(145, 0.5).start();
			return true;
		}
		return false;
	}
}
