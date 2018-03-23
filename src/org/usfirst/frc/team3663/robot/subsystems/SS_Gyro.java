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
	
	double currentFoward;
	double angle;

	@Override
	public void initDefaultCommand() {
	}

	public SS_Gyro() {
		ahrs = HardwareUtil.getHardware(() -> new AHRS(SerialPort.Port.kUSB));
	}
	
	public double getAngle() {
		this.angle = get()-currentFoward;
		if(angle >= 360) {
			currentFoward = get();
		}
		return angle;
	}
	double fake = 0;
	public void fakeResetGyro() {
		System.out.println("WARNING: GYRO RESET");
		fake = ahrs.map(AHRS::getAngle).orElse(0.0);
		
	}
	
	public void hardResetGyro() {
		System.out.println("WARNING: HARD GYRO RESET");
		ahrs.ifPresent(AHRS::reset);
	}
	
	//TODO: Gyropresent here
	public boolean gyroPresent() {
		return ahrs.isPresent();
	}
	
	
	/**
	 * Returns the total accumulated Z-axis angle reported by the sensor, in
	 * degrees.
	 * <p>
	 * NOTE: its range is beyond 360 degrees, so that algorithms don't have to
	 * worry about overflows
	 */
	public double get() {
		return ahrs.map(AHRS::getAngle).orElse(0.0) - fake;
	}
		
	int pickles = 10; //  at +/- 10 degrees the rotation will turn at 1
	public double gyroDiff(){
		double angle = get();
		angle = -angle/pickles;
		//TODO: make a clamp
		//if (angle < .05 && angle > -.05) //to prevent ocillations TODO:Make this into a differential 
		//	angle = 0;
		return angle;
	}
}
