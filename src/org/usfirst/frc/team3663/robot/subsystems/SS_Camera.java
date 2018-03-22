package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.ElapsedTime;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Camera subsystem to test the vision tracking.
 */
public class SS_Camera extends Subsystem {
	static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-CPR");
	

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
	
	private double tgtArea;
	private boolean isTargetDetected;
	private double tgtOffset;
	
	public void initCam() {
		CameraServer.getInstance().startAutomaticCapture();
		/*
		 * Variables `tx`, `ta`, and `tv` are a part of the Limelight API. See:
		 * https://goo.gl/G4rMVZ
		 */
		final NetworkTableEntry tx = table.getEntry("tx");
		final NetworkTableEntry ta = table.getEntry("ta");
		final NetworkTableEntry tv = table.getEntry("tv");



		table.getEntry("ledMode").setNumber(0); // 0=on 1=off

		// Get vision camera data
		tgtOffset = tx.getDouble(0); // Horizontal offset from
													// crosshair to target (-27°
													// to 27°)
		tgtArea = ta.getDouble(0); // Target area (range 0-100)
		isTargetDetected = (tv.getDouble(0) == 1); // 1 = target
																	// detected;
																	// 0
																	// otherwise
	}
	
	
	
		public void trackCube() {
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

			if (1 > 500) {
				if (tgtArea < 45) {
					Robot.ss_drivetrain.driveForward(.3);
				}
				System.out.println("drivefowward u tard");
			}
		}
		
	}
}