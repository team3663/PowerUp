package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;


public class SS_LimitSwitch extends Subsystem {
	
	static DigitalInput limit = new DigitalInput(RobotMap.limitSwitch);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    
    public void Limit() {
    	//to fix git
    	if (limit.get()==true && Robot.oi.last == false) {
    		Robot.oi.last = true;
    	}
    	if (limit.get() == false && Robot.oi.last == true) {
    		System.out.println("feels good bois");
    		Robot.oi.last = false;
    	}
    }

}

