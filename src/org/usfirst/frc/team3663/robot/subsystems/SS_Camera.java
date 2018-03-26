package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_CameraInit;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Camera subsystem to test the vision tracking.
 */

/*
 * from http://docs.limelightvision.io/en/latest/getting_started.html#basic-programming
 * tv	Whether the limelight has any valid targets (0 or 1)
 * tx	Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
 * ty	Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
 * ta	Target Area (0% of image to 100% of image)
 * ts	Skew or rotation (-90 degrees to 0 degrees)
 * tl	The pipelines latency contribution (ms) Add at least 11ms for image capture latency.
 * 
 * ledMode		0 is on, 1 is off, 2 is blink
 * camMode		0 is vision processor, 1 is driver camera (increases exposure, disables vision processing)
 * pipeline		0 to 9, changes the limelight's pipeline (no idea what it does)
 */

public class SS_Camera extends Subsystem {
	private NetworkTable table;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new C_CameraInit());
	}

	// TODO fix this mess up
	final int sThresh = 4;
	final int lThresh = 8;
	
/*	private double tgtArea;
	private boolean isTargetDetected;
	private double tgtOffset;*/
	
	private NetworkTableEntry x;
	private NetworkTableEntry y;
	private NetworkTableEntry area;
	private NetworkTableEntry valid;
	
	public SS_Camera() {
				
		table = NetworkTableInstance.getDefault().getTable("limelight");
		x = table.getEntry("tx");
		y = table.getEntry("ty");
		area = table.getEntry("ta");
		valid = table.getEntry("tv");

	}
	// 0 = processor 1 = driveCam
	public boolean cameraMode(int mode) {
		return table.getEntry("camMode").setNumber(mode);
	}
	
	//0 = on, 1 = off, 2 = blink
	public void turnLightOn(int mode) {
		table.getEntry("ledMode").setNumber(mode);
		System.out.println("Camera light Change");
	}
	
	//Sets the cube detecion settings 0-9
	public void setPipeline(int pipeline) {
		table.getEntry("pipeline").setNumber(pipeline);
	}
	
	//Returns a number -27 to 27 degrees
	public double getXOffset() {
		return x.getDouble(-1);
	}
	
	//Returns a number -20.5 degrees to 20.5 degrees
	public double getYOffset() {
		return y.getDouble(-1);
	}
	
	//Returns a number 0 - 100% of area covered by the target
	public double getArea() {
		return area.getDouble(-1);
	}
	
	//Returns 0 if there are no targets, 1 if there is a target
	public boolean validTargets() {
		return valid.getBoolean(false);
	}
	private boolean init = false;
	public boolean waitAndInitCamera() {
		if(getXOffset() != -1 && !init) {
			turnLightOn(1);//i dont want this on right now
			cameraMode(1);
			setPipeline(0);
			init = true;
			return true;
		}
		else
			return false;
		}
}
