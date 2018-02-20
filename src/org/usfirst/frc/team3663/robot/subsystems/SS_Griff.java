package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * Griff is the cube manipulator that goes up and down
 */
public class SS_Griff extends Subsystem {
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public static final double GRIFF_ROT_LIMIT = 85; // in degrees

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final WPI_TalonSRX griffon = new WPI_TalonSRX(RobotMap.CUBE_SHOOTER);
	private final WPI_TalonSRX griffRot = new WPI_TalonSRX(RobotMap.CUBE_ROTATOR);
	private final Optional<DoubleSolenoid> griffPneumatics = HardwareUtil.getDoubleSolenoid(RobotMap.GRIFF_SQUEEZE_FWD, RobotMap.GRIFF_SQUEEZE_REV);
	private final Potentiometer griffRotSensor = new AnalogPotentiometer(RobotMap.CUBE_ROTATOR_SENSOR);

	private final Optional<DigitalInput> cubePresent = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_INTAKE);

	private void rotateCube(double speed, Double angle) {
		griffRot.set(speed);
	}

	/**
	 * Set speed of cube shooter
	 */
	public void setGriffSpd(double pSpd) {
		griffon.set(pSpd);
	}

	public void turnCube(double speed) {
		rotateCube(speed, null);
	}

	public void sqzGriff(boolean pState) {
		DoubleSolenoid.Value direction = pState ? Value.kReverse : Value.kForward;
		griffPneumatics.ifPresent(p -> p.set(direction));
	}
	
	public double getAngle() {
		// TODO: Convert raw potentiometer data to angles.
		double dataRaw = griffRotSensor.get();
		System.out.println(dataRaw);
		
		return dataRaw;
	}
	
	public boolean rotatorWithinRange() {
		return Math.abs(getAngle()) <= GRIFF_ROT_LIMIT;
	}

	public boolean getSwitchState() {
		// Returns digital input result if exists; false otherwise
		return cubePresent.map(DigitalInput::get).orElse(false);
	}

}
