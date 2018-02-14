package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
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
		

		try {
			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (final RuntimeException ex) {
			//DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
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
		return 0;//ahrs.getAngle();
	}
	   public double calcGyro(double pLoc){
           double tract = .75; //feild = .8
           double speed = (pLoc-gyroGetAngle())/60;
           
           System.out.println(gyroGetAngle());
           if(speed < tract && speed > 0){
               speed = tract;
           }if(speed > -tract && speed < 0){
               speed = -tract;
           }
           return speed;
       }
   public boolean turnGyro(int pickles) {
	   double spd = calcGyro(pickles);
	   System.out.println("  Speed : " + spd + "   Current : " + gyroGetAngle() + "  Dest : " + pickles);
	   Robot.ss_drivetrain.drive.arcadeDrive(0, spd);
	
	   return Math.abs(pickles) < Math.abs(gyroGetAngle());
   }

}
