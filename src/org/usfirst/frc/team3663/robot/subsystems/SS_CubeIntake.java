package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_Drive;
import org.usfirst.frc.team3663.robot.commands.C_IntakeSanityCheck;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SS_CubeIntake extends Subsystem {
	private final WPI_TalonSRX rightIntake = new WPI_TalonSRX(
			RobotMap.CUBE_INTAKE_RIGHT);
	private final WPI_TalonSRX leftIntake = new WPI_TalonSRX(
			RobotMap.CUBE_INTAKE_LEFT);

	//private final Optional<DoubleSolenoid> intakeSqz = HardwareUtil.getDoubleSolenoid(RobotMap.INTAKE_SQZ_FOWARD,RobotMap.INTAKE_SQZ_REVERSE);
	//private final Optional<DoubleSolenoid> intakeLift = HardwareUtil.getDoubleSolenoid(RobotMap.INTAKE_LIFT_FOWARD,	RobotMap.INTAKE_LIFT_REVERSE);
	
	private final DoubleSolenoid intakeSqz = new DoubleSolenoid(RobotMap.INTAKE_SQZ_FOWARD,	RobotMap.INTAKE_SQZ_REVERSE);
	private final DoubleSolenoid intakeLift = new DoubleSolenoid(RobotMap.INTAKE_LIFT_FOWARD,	RobotMap.INTAKE_LIFT_REVERSE);

	@Override
	public void initDefaultCommand() {
		//setDefaultCommand(new C_IntakeSanityCheck());
	}

	public void spinIntake(double speed) {
		rightIntake.set(speed);
		leftIntake.set(-speed);
	}

	public void sqzIntake(boolean isForward) {
		final DoubleSolenoid.Value direction = isForward ? Value.kForward: Value.kReverse;
		intakeSqz.set(direction);
		//intakeSqz.ifPresent(p -> p.set(direction));
	}

	public void extendIntake(boolean isForward) {
		final DoubleSolenoid.Value direction = isForward? Value.kForward: Value.kReverse;
		intakeLift.set(direction);
		//intakeLift.ifPresent(p -> p.set(direction));
	}

	// TODO this is test code pls do use unless ur a potato
	public int counter = 0;
	 public void testIntake() { 
		 counter++; 
		 sqzIntake(counter<10);
		 extendIntake(counter<10);
	
	 if (counter > 20) counter = 0;
	 sqzIntake(false);
	 extendIntake(true);
	
	 }
	

}
