package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SS_CubeIntake extends Subsystem {
	private final WPI_TalonSRX rightIntake = new WPI_TalonSRX(
			RobotMap.CUBE_INTAKE_RIGHT);
	private final WPI_TalonSRX leftIntake = new WPI_TalonSRX(
			RobotMap.CUBE_INTAKE_LEFT);

	private final Optional<DoubleSolenoid> intakeSqz = HardwareUtil
			.getDoubleSolenoid(RobotMap.CLIMBER_PNEUM_FWD,
					RobotMap.CLIMBER_PNEUM_REV);

	private final Optional<DoubleSolenoid> intakeLift = HardwareUtil
			.getDoubleSolenoid(RobotMap.INTAKE_LIFT_FOWARD,
					RobotMap.INTAKE_LIFT_REVERSE);

	@Override
	public void initDefaultCommand() {
	}

	public void spinIntake(double speed) {
		rightIntake.set(speed);
		leftIntake.set(-speed);
	}

	public void sqzIntake(boolean isForward) {
		final DoubleSolenoid.Value direction = isForward
				? Value.kForward
				: Value.kReverse;
		intakeSqz.ifPresent(p -> p.set(direction));
	}

	public void extendIntake(boolean isForward) {
		final DoubleSolenoid.Value direction = isForward
				? Value.kForward
				: Value.kReverse;
		intakeLift.ifPresent(p -> p.set(direction));
	}

	// TODO this is test code pls do use unless ur a potato
	/*
	 * public void testIntake() { counter++; sqzIntake(counter > 10);
	 *
	 * if (counter > 20) counter = 0;
	 *
	 * }
	 */

}
