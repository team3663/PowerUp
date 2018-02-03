package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.commands.C_GyroPrint;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
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
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new C_GyroPrint());
		
		try {
			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (final RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
		
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
	 
}
