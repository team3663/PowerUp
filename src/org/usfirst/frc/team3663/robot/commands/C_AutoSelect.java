package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;


public class C_AutoSelect extends Command {
	private int target;
	
	public C_AutoSelect(int target) {
		requires(Robot.ss_autoSelect);
		this.target = target;
	}
	@Override
	protected void initialize() {
		Robot.ss_gyro.fakeReset();
		Timer.delay(.5);
	}
	
	boolean first = true;
	@Override
	protected void execute(){
		if(first) {
			Robot.ss_autoSelect.runAuto(target);
			first = false;
		}
	}

	
	@Override
	protected boolean isFinished() {
		return false;
	}
	@Override
	protected void interrupted() {
		Robot.ss_autoSelect.end();
		end();
	}
	@Override
	protected void end() {
	first = true;
	}
}
