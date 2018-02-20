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
		return ahrs.map(AHRS::getAngle).orElse(0.);
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
