package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_IntakeMonitor;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SS_CubeIntake extends Subsystem {
	private final WPI_TalonSRX rightIntake = new WPI_TalonSRX(RobotMap.CUBE_INTAKE_RIGHT);
	// private WPI_TalonSRX leftIntake = new
	// WPI_TalonSRX(RobotMap.CUBE_INTAKE_LEFT);

	//private final DoubleSolenoid intakePneumaticSqz = new DoubleSolenoid(RobotMap.CUBE_INTAKE_FORWARD,
	//		RobotMap.CUBE_INTAKE_REVERSE);
	//private final DoubleSolenoid intakePneumaticLft = new DoubleSolenoid(RobotMap.CUBE_INTAKE_LIFT_FOWARD,
	//		RobotMap.CUBE_INTAKE_LIFT_REVERSE);
	
	int counter = 0;

	public void spinIntake(double speed) {
		rightIntake.set(speed);
		// leftIntake.set(speed);
	}

	public void sqzIntake(boolean isForward) {
		//DoubleSolenoid.Value direction = isForward ? Value.kForward : Value.kReverse;
		//intakePneumaticSqz.set(direction);
	}

	public void extndIntake(boolean isForward) {
		//DoubleSolenoid.Value direction = isForward ? Value.kForward : Value.kReverse;
		//intakePneumaticLft.set(direction);
	}

	// TODO this is test code pls do use unless ur a potato

	public void testIntake() {
		counter++;
		sqzIntake(counter > 10);
		
		if (counter > 20)
			counter = 0;
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_IntakeMonitor());
	}

	public SS_CubeIntake() {
		// TODO Auto-generated constructor stub
	}

}
