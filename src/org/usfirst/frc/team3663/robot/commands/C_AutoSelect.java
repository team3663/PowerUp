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
    protected void execute() {
    	String leverPos = Robot.ss_autoSelect.getLeverPos();
    	
    	if (leverPos.charAt(0) == 'L') {
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
		}
    
    }

	@Override
	protected boolean isFinished() {
		return true;
	}
}
