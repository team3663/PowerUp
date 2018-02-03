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
    	/*
    	 * Variables `tx`, `ta`, and `tv` are a part of the Limelight
    	 * API. See: <http://docs.limelightvision.io/en/latest/getting_started.html#basic-programming>
    	 */
    	NetworkTableEntry tx = table.getEntry("tx");
    	NetworkTableEntry ta = table.getEntry("ta");
    	NetworkTableEntry tv = table.getEntry("tv");
    	
    	final int sThresh = 4;
    	final int lThresh = 8;
    	
    	table.getEntry("ledMode").setNumber(0); // 0=on  1=off
    	
    	// Get vision camera data
    	final double tgtOffset = tx.getDouble(0); // Horizontal offset from crosshair to target (-27° to 27°)
    	final double tgtArea = ta.getDouble(0); // Target area (range 0-100)
    	boolean isTargetDetected = (tv.getDouble(0) == 1); // 1 = target detected; 0 otherwise
    	
    	
    	if (isTargetDetected) {
    		if (tgtArea < 60) {
    			if (Math.abs(tgtOffset) > sThresh) {
    				double speed = .2;
    				if (Math.abs(tgtOffset) > lThresh)
    					speed = .3;
    				
    				if (tgtOffset > 0) {
    					System.out.println("right");
    					Robot.ss_drivetrain.turn(speed);
    				} else {
    					System.out.println("left");
    					Robot.ss_drivetrain.turn(-speed);
    				}
    				Robot.cTime = 0;
    			} else {
        			System.out.println(">>>>>>better");
        			Robot.cTime++;
        			System.out.println(Robot.cTime);
    			}
    				
    		}
    		
    		if (Robot.cTime > 10) {
    			if (tgtArea < 45) {
    				Robot.ss_drivetrain.drivetest(-.3);
    			}
    			System.out.println("drivefowward u tard");
    		}
    	}
    }	
}
    	//float heading_error = tx;
    	//steering_adjust = Kp * tx;
    	


