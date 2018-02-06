package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_IntakeMonitor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SS_CubeIntake extends Subsystem {
	private WPI_TalonSRX rightIntake = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_RIGHT);
	//private WPI_TalonSRX leftIntake = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_LEFT);
	
	private DoubleSolenoid intakePneumatic = new DoubleSolenoid(RobotMap.CUBE_INTAKE_FORWARD, 
				RobotMap.CUBE_INTAKE_REVERSE);
	
	
	
	public void spinIntake(double speed) {
		rightIntake.set(speed);
		//leftIntake.set(speed);
	}
	
	public void stopIntake() {
		rightIntake.set(0);
		//leftIntake.set(0);
	}
	
	public void extendIntake(boolean pState) {
		//true = foward
		//false = reverse
		if(pState == true) {
			intakePneumatic.set(DoubleSolenoid.Value.kForward);
		}
		else if (pState == false){
			intakePneumatic.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	
	public void secureCube() {
		stopIntake();
		extendIntake(true);
	}
	//TODO this is test code pls dont use unless ur a potato
	public void testIntake() {
		Robot.testCounter ++;
		int count = Robot.testCounter;
		if(count > 10) {
			extendIntake(true);
		}
		else {
			extendIntake(false);
		}
		if(count > 20) {
			Robot.testCounter = 0;
		}
	}
	
	
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_IntakeMonitor()); 
	}
	
	public SS_CubeIntake() {
		// TODO Auto-generated constructor stub
	}

}
