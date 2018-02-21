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
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String leverPos = Robot.ss_autoSelect.getLeverPos();
    	String sub = "LLL";
    	if(true) {
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
        } else {
        	new C_Wait(50).start();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
