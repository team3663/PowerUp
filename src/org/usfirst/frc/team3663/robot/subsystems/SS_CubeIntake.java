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
	
	private DoubleSolenoid intakePneumaticSqz = new DoubleSolenoid(Robot.RobotMap.CUBE_INTAKE_FORWARD, RobotMap.CUBE_INTAKE_REVERSE);
	private DoubleSolenoid intakePneumaticLft = new DoubleSolenoid(Robot.RobotMap.CUBE_INTAKE_LIFT_FOWARD, RobotMap.CUBE_INTAKE_LIFT_REVERSE);
	
	
	public void spinIntake(double speed) {
		rightIntake.set(speed);
		//leftIntake.set(speed);
	}
	
	public void sqzIntake(boolean pState) {
		//true = foward
		//false = reverse
		if(pState == true) {
			intakePneumaticSqz.set(DoubleSolenoid.Value.kForward);
		}
		else if (pState == false){
			intakePneumaticSqz.set(DoubleSolenoid.Value.kReverse);
		}
	}
	public void extndIntake(boolean pState) {
		//true = foward
		//false = reverse
		if(pState == true) {
			intakePneumaticLft.set(DoubleSolenoid.Value.kForward);
		}
		else if (pState == false){
			intakePneumaticLft.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	//TODO this is test code pls dont use unless ur a potato
	
	public void testIntake() {
		Robot.testCounter ++;
		int count = Robot.testCounter;
		if(count > 10) {
			sqzIntake(true);
		}
		else {
			sqzIntake(false);
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
