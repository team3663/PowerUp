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
    	int sThresh = 4;
    	int lThresh = 8;
    	int time = 0;
    	
    	table.getEntry("ledMode").setNumber(0); //0=on  1=off
    	
   
    	double x = tx.getDouble(0);
    	double a = ta.getDouble(0);
    	double target = tv.getDouble(0);
    	double steeringChange = 0;
    	
    	if (target == 1) {
    		if (a < 60) {
    			// - is left and + is right
    			if (x < -lThresh) {
    				System.out.println("left");
    				Robot.ss_drivetrain.turn(.3);
    				Robot.cTime = 0;
    			
    			}
    			else if (x > lThresh) {
    				System.out.println("right");
    				Robot.ss_drivetrain.turn(-.3);
    				Robot.cTime = 0;
    			}
    				else if (x < -sThresh) {
        				System.out.println("left");
        				Robot.ss_drivetrain.turn(.2);
        				Robot.cTime = 0;
        			
        			}
        			else if (x > sThresh) {
        				System.out.println("right");
        				Robot.ss_drivetrain.turn(-.2);
        				Robot.cTime = 0;
        			}
        			else if (x < sThresh || x > -sThresh) {
        				System.out.println(">>>>>>better");
        				Robot.cTime++;
        				System.out.println(Robot.cTime);
        			
        			}
    				
    			}
    			if (Robot.cTime > 10) {
    				if (a < 45) {
    					
    					Robot.ss_drivetrain.drivetest(-.3);
    				}
    				System.out.println("drivefowward u tard");
    			}
    	
    		}
    	}	
    }
    	//float heading_error = tx;
    	//steering_adjust = Kp * tx;
    	


