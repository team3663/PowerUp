/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_Drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */

public class SS_DriveTrain extends Subsystem {

	// Wheels are 6.125"
	// 6.125 * pi = 19.242"
	// 256 ticks/rev
	// 256 / 19.242" = 13.304 ticks/in
	public static final double TICKS_PER_IN = 13.304;

	public WPI_VictorSPX left1 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_1);
//	public WPI_VictorSPX left2 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_2);
//	public WPI_VictorSPX left3 = new WPI_VictorSPX(RobotMap.DRIVE_LEFT_3);

	public WPI_VictorSPX right1 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_1);
//	public WPI_VictorSPX right2 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_2);
//	public WPI_VictorSPX right3 = new WPI_VictorSPX(RobotMap.DRIVE_RIGHT_3);

	private final Encoder leftEnc = new Encoder(RobotMap.DRIVE_LEFT_ENC_1, RobotMap.DRIVE_LEFT_ENC_2);
	private final Encoder rightEnc = new Encoder(RobotMap.DRIVE_RIGHT_ENC_1, RobotMap.DRIVE_RIGHT_ENC_2);

	public DifferentialDrive drive = new DifferentialDrive(left1, right1);

	public int inchesToTicks(double inches) {
		return (int) (inches * TICKS_PER_IN);
	}

	public SS_DriveTrain() {
		/*																TODO need to add this to the constructor when we replace the Talons
		right1.enableCurrentLimit(true);
		right1.configPeakCurrentLimit(35,2);							//max current followed by time in ms of allowable peak
		right2.enableCurrentLimit(true);
		right2.configPeakCurrentLimit(35,2);							//max current followed by time in ms of allowable peak
		left1.enableCurrentLimit(true);
		left1.configPeakCurrentLimit(35,2);
		left2.enableCurrentLimit(true);
		left2.configPeakCurrentLimit(35,2);
		*/
/*
		left2.follow(left1);
		left3.follow(left1);

		right2.follow(right1);
		right3.follow(right1);
	*/
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}

	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.

		// Samuel Response: I knew what it meant in 5 seconds. It's Brake if
		// breaksEnabled, else it's NeutralMode.
		final NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;
		left1.setNeutralMode(mode);
		right1.setNeutralMode(mode);
	}
	public void stop() {
		drive.arcadeDrive(0, 0);
	}
	
	public void reset() {
		leftEnc.reset();
		rightEnc.reset();
	}

	public void driveForward(double speed) {
		drive.arcadeDrive(speed, 0);
	}

	/**
	 * @return position of left motor by ticks
	 */
	public int getLeft() {
		return leftEnc.get();
	}

	/**
	 * @return position of right motor by ticks
	 */
	public int getRight() {
		return rightEnc.get();
	}
	
	//averages the two enc, but defaults to the other if unplugged
	public int get() {
		if (L() && R())
			return (getLeft() + getRight()) / 2; //average the two encoders
		else if (L())
			return getLeft();
		else if (R())
			return getRight();
		else{
			System.out.println("WARNING ENCODERS NOT PLUGGED IN, FIX NOW");
			return 0;
		}
	}

	/**
	 * Turns in place
	 *
	 * @param turns
	 *            clockwise if positive
	 */
	public void turn(double speed) {
		drive.arcadeDrive(0, speed);
	}
	// checks to make sure that the encoders are plugged in
	public boolean L(){
		if (getLeft() != 0 && left1.get() != 0) 
			return true;
		else {
			System.out.println("WARNING LEFT ENCODER NOT PLUGGED IN, FIX NOW");
			return false;
		}
	}
	public boolean R(){
		if (getRight() != 0 && right1.get() != 0) 
			return true;
		else {
			System.out.println("WARNING RIGHT ENCODER NOT PLUGGED IN, FIX NOW");
			return false;
		}
	}
		
	int pickles = 200; //TODO: this might be horribly wrong, i did math for it that i'm not to sure about sooooo
	public double encoderDiff(){
		if (R() && L())
			return (getLeft()-getRight())/pickles; // TODO: make sure the encoders are in the right direction
		else
			return 0;
	}
	//This code is like a PID for rotation of drivetrain using gyro and encoders, averaging the two for a smoother experiance
	public double diff(){
		
		//System.out.println(Robot.ss_gyro.gyroDiff() + "   " + encoderDiff());
		//System.out.println( L() + "   " + R());
		if( L() && R() && Robot.ss_gyro.gyroPresent()) 
			return (Robot.ss_gyro.gyroDiff() + encoderDiff()) / 2;
		else if (Robot.ss_gyro.gyroPresent()) 
			return Robot.ss_gyro.gyroDiff();
		else if (L() && R())
			return encoderDiff();
		else
			return 0;
	}
	
	public void driveStraight(double pSpd) {
		drive.arcadeDrive(pSpd, diff());
	}
	
	//spins and moves foward whie spinning (extremely useful)
	int spinningPickles  = 180;
	public void driveSpin() {
		drive.arcadeDrive(Robot.ss_gyro.getAngle()/spinningPickles , 1); //TODO: find where the turn ovverrides the foward
	}
	
	//(radius that the robot makes) * (2) * (3.14 pi) / (360)
	double radius = 0;
	double distancePerDegree = 0; // 2 pi (radius) / 360
	double distancePerTicks = .074; //in (just documenting)
	double ticksPerInch = 13.5;
	
	double encPickles = 90; // at +/- 90 degrees speed will be one
	public double encTurn(double target) {
		
		double angle = (get()/TICKS_PER_IN); //converts ticks to inches
		angle = (( 2 * 3.1415 * radius * angle)/360); //finds the angle 
		angle = -angle/this.encPickles; //converts to motorcontroller spd porportionaly
		return angle;
		
	}
	
	

}
