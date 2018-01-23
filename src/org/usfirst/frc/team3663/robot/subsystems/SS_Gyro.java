package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.*;
import org.usfirst.frc.team3663.robot.commands.C_GyroPrint;

import com.kauailabs.navx.frc.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Gyro extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new C_GyroPrint());
    	
    	AHRS ahrs;
    	  try 
      	{
              ahrs = new AHRS(SerialPort.Port.kMXP); 
          } catch (RuntimeException ex ) 
      	{
              DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
          }
    }
    
     //AHRS ahrs;
  /*  
    public double gyroReadx() {
        return ahrs.getRawGyroX();
        }
    public double gyroReady() {
        return ahrs.getRawGyroY();
        }
    public double gyroReadz() {
        return ahrs.getRawGyroZ();
        }
        */
}

