package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
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
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
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
		//CameraServer.getInstance().startAutomaticCapture();
		
		table = NetworkTableInstance.getDefault().getTable("limelight");
		x = table.getEntry("tx");
		y = table.getEntry("ty");
		area = table.getEntry("ta");
		valid = table.getEntry("tv");
		turnLightOn(0);
		setPipeline(0);
	}
	
	//0 = on, 1 = off, 2 = blink
	public void turnLightOn(int mode) {
		table.getEntry("ledMode").setNumber(mode);
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
	
/*	public void trackCube() {
		if (isTargetDetected) {
			if (tgtArea < 60) {
				if (Math.abs(tgtOffset) > sThresh) {
					double speed = .2;
					if (Math.abs(tgtOffset) > lThresh) {
						speed = .3;
					}
					if (tgtOffset > 0) {
						System.out.println("right");
						Robot.ss_drivetrain.turn(speed);
					} else {
						System.out.println("left");
						Robot.ss_drivetrain.turn(-speed);
					}
					//Robot.time.reset();
				} else {
					System.out.println(">>>>>>better");
					//System.out.println(Robot.time.getElapsedMillis());
				}
	
			}
			if (time.getElapsedMillis() > 500) {
				if (tgtArea < 45) {
					Robot.ss_drivetrain.driveForward(.3);
				}
				System.out.println("drivefowward u tard");
			}
		}
	
	}*/
}