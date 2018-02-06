package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_CubeManipulator extends Subsystem {
	private final WPI_TalonSRX shooterTalon = new WPI_TalonSRX(RobotMap.CUBE_SHOOTER);
	private final WPI_TalonSRX rotatorTalon = new WPI_TalonSRX(RobotMap.CUBE_ROTATOR);
	private final DoubleSolenoid shooterPneumatic = new DoubleSolenoid(RobotMap.CUBE_SHOOTER_FORWARD,
			RobotMap.CUBE_SHOOTER_REVERSE);
	
	private DigitalInput cubePresent = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE);

	private static final double SHOOTER_SPEED = 1.0;
	@Override
	protected void initDefaultCommand() {
	}
	
	private void rotateCube(double speed, Double angle) {
		rotatorTalon.set(speed);
	}
	
	public void pullCube() {
		shooterTalon.set(.3);
	}

	public void shootCube() {
		shooterTalon.set(SHOOTER_SPEED);
	}

	public void stopManipulatorSpinning() {
		shooterTalon.set(0);
	}

	public void turnCube(double speed) {
		rotateCube(speed, null);
	}

	public void moveShooter(DoubleSolenoid.Value val) {
		shooterPneumatic.set(val);
	}
	public boolean getSwitchState() {
		return cubePresent.get();
	}

}
