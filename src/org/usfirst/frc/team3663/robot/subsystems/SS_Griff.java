package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_TriggerButton;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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

	public static final int GRIFF_LIMIT_L = 85;
	public static final int GRIFF_LIMIT_R = 45;
	public static final int GRIFF_SAFE_L = 85;
	public static final int GRIFF_SAFE_R = 85;

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new C_TriggerButton());
	// setDefaultCommand(new C_GriffSanityCheck());
	}

	public static final double GRIFF_ROT_LIMIT = 85; // in degrees

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final WPI_VictorSPX griffon = new WPI_VictorSPX(RobotMap.CUBE_SHOOTER);
	private final WPI_VictorSPX griffRot = new WPI_VictorSPX(RobotMap.CUBE_ROTATOR);
	// private final Optional<DoubleSolenoid> griffPneumatics =
	// HardwareUtil.getDoubleSolenoid(RobotMap.GRIFF_SQUEEZE_FWD,
	// RobotMap.GRIFF_SQUEEZE_REV);
	private final DoubleSolenoid griffSqz = new DoubleSolenoid(RobotMap.GRIFF_SQUEEZE_FWD, RobotMap.GRIFF_SQUEEZE_REV);
	
	// Measures the rotation of the griff
	

	private final Optional<DigitalInput> cubePresentL = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_CUBE_PRESENT);
	private final Optional<DigitalInput> cubePresentR = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_CUBE_PRESENT1);
	private void rotateCube(double speed, Double angle) {
		if (Robot.ss_elevator.get() < 100) {

		}
		griffRot.set(speed);
	}

	/**
	 * Set speed of cube shooter
	 */
	public void setGriffSpd(double pSpd) {
		griffon.set(pSpd);
	}

	public void turnGriff(double speed) {
		rotateCube(speed, null);
	}

	public boolean isBelowBar() {
		return Robot.ss_elevator.get() < 3000;
	}

	

	//public boolean rotatorWithinRange() {
	/*	// TODO FINNISH THIS CODE
		if (isBelowBar()) {
			if (getAngle() > 4) {
				return true;
			} else if (getAngle() > 3) {
				return true;
			}
		} else {
			if (getAngle() > 4) {
				return true;
			} else if (getAngle() > 3) {
				return true;
			}

		}
		return false;
	}
	*/

	public boolean getSwitchStateL() {
		boolean result = !cubePresentL.map(DigitalInput::get).orElse(false);
		
		return result;
	}
	public boolean getSwitchStateR() {
		boolean result = !cubePresentR.map(DigitalInput::get).orElse(false);

		return result;
	}
	
	public boolean getSwitchStateBoth() {
		return getSwitchStateL() && getSwitchStateR();
	}
	
	public boolean getSwitchStateAny() {
		return getSwitchStateL() || getSwitchStateR();
	}

	public void sqzGriff(boolean pState) {
		final DoubleSolenoid.Value direction = pState ? Value.kReverse : Value.kForward;
		// griffPneumatics.ifPresent(p -> p.set(direction));
		griffSqz.set(direction);
	}



}
