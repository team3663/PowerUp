package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;


public class C_AutoSelect extends Command {
	private int target;
	
	public C_AutoSelect(int target) {
		requires(Robot.ss_autoSelect);
		this.target = target;
	}

	@Override
	protected void execute(){
		Robot.ss_autoSelect.runAuto(target);
	}

	
	@Override
	protected boolean isFinished() {
		
		return true;
	}
}
