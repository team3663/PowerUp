package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
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
	
	
	// NOTE: if the Gyro can't be read, all angle readings default to 0.

	// Since we have readAngle, should we keep these methods?
	public float gyroReadX() {
		// Here's how this code works:
		//  1. Optional<T>.map() accepts a function which takes in a T and
		//     gives a different value. in this case, it takes an AHRS and
		//     returns a float. `.map()` will always return an Optional<Float>.
		//  2. If `ahrs` is a present Optional (i.e. it's not empty), then
		//     it returns an Optional of ahrs.getRawGyroX(). Else, it returns
		//     an empty Optional<Float>.
		//  3. .orElse(default) takes an Optional<T> and, if it's present, returns the
		//     value it holds. Else, it returns the default value given.
		//
		//  TL;DR: It returns the result of AHRS.getRawGyroX() if the sensor exists;
		//    it returns 0 otherwise.
		return ahrs.map(AHRS::getRawGyroX).orElse(0f);
	}

	public float gyroReadY() {
		return ahrs.map(AHRS::getRawGyroY).orElse(0f);
	}

	public float gyroReadZ() {
		return ahrs.map(AHRS::getRawGyroZ).orElse(0f);
	}

	//below is very much test code
	public void leverSide() {
		DriverStation.Alliance side = DriverStation.getInstance().getAlliance();
		
	}
	
	/**
	 * Returns the total accumulated Z-axis angle reported by the sensor, in degrees.
	 * <p>
	 * NOTE: its range is beyond 360 degrees, so that algorithms don't have to worry about overflows
	 */
	public double gyroGetAngle() {
		return ahrs.map(AHRS::getAngle).orElse(0.0);
	}

	// TODO document what this does
	public double calcGyro(double pLoc) {
		final double tract = .5; // field = .8
		double speed = (pLoc - gyroGetAngle()) / 60;

		System.out.println(gyroGetAngle());
		
		if (speed < tract && speed > 0) {
			speed = tract;
		} else if (speed > -tract && speed < 0) {
			speed = -tract;
		}
		return speed;
	}

}
