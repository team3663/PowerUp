/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3663.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team3663.robot.commands.C_Drive;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors, a left and right encoder
 * and a gyro.
 */


public class SS_DriveTrain extends Subsystem {
	
	// Highest position elevator should go
	private static final int ELEVATOR_POS_MAX = 4000;
	
	public WPI_TalonSRX left        = new WPI_TalonSRX(1);
	public WPI_TalonSRX right       = new WPI_TalonSRX(3);
	public static WPI_TalonSRX elevator = new WPI_TalonSRX(2);
	
	public DifferentialDrive drive //arcade drive
			= new DifferentialDrive(right, left);
	
	
	public void driveForward(double speed) {
		left.set(speed);
		right.set(-speed);
	}
	

	public void turnRight(double speed) {
		left.set(speed);
		right.set(speed);

	}
	
	/**
	 * Sets lift speed
	 * @param spd Lift speed
	 */
	public void setElevator(double spd) {
		elevator.set(spd);
		elevator.setNeutralMode(null);
	}
	
	/**
	 * Sets up encoder for use
	 */
	public static void initEnc(){
		elevator.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public static int getElevatorPos() {
		return elevator.getSensorCollection().getQuadraturePosition();
	}
	
	public static void elevatorToTop() {
		// Keeps lift moving unless it reaches the highest
		//   it should go.
		int elevatorPos = getElevatorPos();
		elevator.set( (elevatorPos < ELEVATOR_POS_MAX) ? 1 : 0);
	}
	
		/* Encoders may measure differently in the real world and in
		   simulation. In this example the robot moves 0.042 barleycorns
		   per tick in the real world, but the simulated encoders
		   simulate 360 tick encoders. This if statement allows for the
		   real robot to handle this difference in devices.
		   this is a test boi x2 */
		

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_Drive());
	}

}
