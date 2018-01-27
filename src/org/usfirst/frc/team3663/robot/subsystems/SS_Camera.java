package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Camera extends Subsystem {
	static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void initCam() {
    	
    	NetworkTableEntry tx = table.getEntry("tx");
    	double x = tx.getDouble(0);
    	double steeringChange = 0;
    
    	System.out.println(x);
    	//float heading_error = tx;
    	//steering_adjust = Kp * tx;
    	

    	
    }
}

