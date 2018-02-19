package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.commands.C_Gyro;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Gyro extends Subsystem {
	AHRS ahrs;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_Gyro(10));
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	public void initGyro() {
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (final RuntimeException ex) {
			System.err.println("ya done goofed");
			ex.printStackTrace();
		}
	}

	public void resetGyro() {
		ahrs.reset();
	}

	public double gyroReadX() {
		return ahrs.getRawGyroX();
	}

	public double gyroReadY() {
		return ahrs.getRawGyroY();
	}

	public double gyroReadZ() {
		return ahrs.getRawGyroZ();
	}

	public double gyroGetAngle() {
		return ahrs.getAngle();
	}

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

	// TODO document what the boolean means
	public boolean turnGyro(int destination) {
		final double spd = calcGyro(destination);
		System.out.printf("Speed: %f\tCurrent: %f\tDest: %d\n", spd, gyroGetAngle(), destination);
		Robot.ss_gearbox.turn(-spd);

		return Math.abs(destination) < Math.abs(gyroGetAngle());
	}

}
