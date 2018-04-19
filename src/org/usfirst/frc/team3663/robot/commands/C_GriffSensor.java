package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Holds until the griff sensor detects a cube
 */
public class C_GriffSensor extends Command {

	public C_GriffSensor() {
		requires(Robot.ss_griff);
	}

	int counter = 0;
	int outCounter = 0;
	boolean out = false;
	@Override
	protected boolean isFinished() {
		System.out.println("L  :  " + Robot.ss_griff.getSwitchStateL() + "   R  :  " + Robot.ss_griff.getSwitchStateR());
		
		
		if(Robot.ss_griff.getSwitchStateL() && Robot.ss_griff.getSwitchStateR()) {
			Robot.ss_griff.setGriffSpd(.2);
		}
		
		if((Robot.ss_griff.getSwitchStateL() || Robot.ss_griff.getSwitchStateR())) {
			Robot.ss_cubeIntake.spinIntake(-.5);
			out = true;
			
		}
		
		//out half
		/*
		if (out && outCounter < 5) {
			outCounter++;
		}
		else {
			Robot.ss_cubeIntake.spinIntake(1);
			outCounter = 0;
			out = false;
		}
		*/
		
		if (out) {
			outCounter++;
			if (outCounter >= 5) {
				Robot.ss_cubeIntake.spinIntake(1);
				outCounter = 0;
				out = false;
			}
		}
		
		return Robot.ss_griff.getSwitchStateL() && Robot.ss_griff.getSwitchStateR();
	}
	

}
