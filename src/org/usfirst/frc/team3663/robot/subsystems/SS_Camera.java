package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Camera extends Subsystem {
	static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-CPR");
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void initCam() {
    	
    	NetworkTableEntry tx = table.getEntry("tx");
    	NetworkTableEntry ta = table.getEntry("ta");
    	NetworkTableEntry tv = table.getEntry("tv");
    	
    	int thresh = 6;
    	
    	table.getEntry("ledMode").setNumber(1);
    	
   
    	double x = tx.getDouble(0);
    	double a = ta.getDouble(0);
    	double target = tv.getDouble(0);
    	double steeringChange = 0;
    	
    	if (target == 1) {
	    	if (a < 60) {
	    		if (x < -thresh) {
	    			System.out.println("left");
	    			Robot.ss_drivetrain.turn(.2);
	    			
	    		}
	    		else if (x > thresh) {
	    			System.out.println("right");
	    			Robot.ss_drivetrain.turn(-.2);
	    		}
	    		else if (x < thresh || x > -thresh) {
	    			System.out.println(">>>>>>good");
	    		}
	    	}
    	}
    	//float heading_error = tx;
    	//steering_adjust = Kp * tx;
    	

    	
    }
}

