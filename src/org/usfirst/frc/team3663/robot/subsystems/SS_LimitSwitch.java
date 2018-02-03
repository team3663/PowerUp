package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class SS_LimitSwitch extends Subsystem {
	
	static DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitch);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    
    public void limit() {
    	//to fix git
    	if (limitSwitch.get() && !Robot.oi.last) {
    		Robot.oi.last = true;
    	}
    	if (!limitSwitch.get() && Robot.oi.last) {
    		System.out.println("feels good bois");
    		Robot.oi.last = false;
    	}
    }

}

