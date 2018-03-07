package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Gyroscope that measures the orientation of the robot
 */
public class SS_Gyro extends Subsystem {
	private final Optional<AHRS> ahrs;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
	}

	public SS_Gyro() {
		ahrs = HardwareUtil.getHardware(() -> new AHRS(SerialPort.Port.kUSB));
	}

	public void resetGyro() {
		ahrs.ifPresent(AHRS::reset);
	}

	/**
	 * Returns the total accumulated Z-axis angle reported by the sensor, in
	 * degrees.
	 * <p>
	 * NOTE: its range is beyond 360 degrees, so that algorithms don't have to
	 * worry about overflows
	 */
	public double gyroGetAngle() {
		return ahrs.map(AHRS::getAngle).orElse(0.0);
	}
	
	//TODO: add code for isPresent to make sure diff() in DT works properly
	
	int pickles = 10; //  at +/- 10 degrees the rotation will turn at 1
	public double gyroDiff(){
		double angle = gyroGetAngle();
		angle = -angle/pickles;
		//TODO: make a clamp
		//if (angle < .05 && angle > -.05) //to prevent ocillations TODO:Make this into a differential 
		//	angle = 0;
		return angle;
	}
}
