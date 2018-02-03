package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_IntakeMonitor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SS_CubeIntake extends Subsystem {
	private WPI_TalonSRX rightIntake = new WPI_TalonSRX(RobotMap.CUBE_INTAKE);
	//private WPI_TalonSRX leftIntake = new WPI_TalonSRX(RobotMap.CUBE_INTAKE);
	
	private DoubleSolenoid intakePneumatic = new DoubleSolenoid(RobotMap.CUBE_INTAKE_FORWARD, 
				RobotMap.CUBE_INTAKE_REVERSE);
	
	private DigitalInput cubePresent = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE);
	
	public void spinIntake() {
		rightIntake.set(.3);
		//leftIntake.set(.3);
	}
	
	public void stopIntake() {
		rightIntake.set(0);
		//leftIntake.set(0);
	}
	
	public void extendIntake() {
		intakePneumatic.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractIntake() {
		intakePneumatic.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void secureCube() {
		stopIntake();
		retractIntake();
	}
	
	public boolean getSwitchState() {
		return cubePresent.get();
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_IntakeMonitor()); 
	}
	
	public SS_CubeIntake() {
		// TODO Auto-generated constructor stub
	}

}
