package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_LimitSwitch extends Subsystem {
	
	static DigitalInput limit = new DigitalInput(RobotMap.limitSwitch);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void Limit() {
    	
    	
    	if (limit.get()==true && Robot.m_oi.last == false) {
    		Robot.m_oi.last = true;
    	}
    	if (limit.get() == false && Robot.m_oi.last == true) {
    		System.out.println("feels good bois");
    		Robot.m_oi.last = false;
    	}
    }
}

