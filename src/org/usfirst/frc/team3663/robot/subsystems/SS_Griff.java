package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
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
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public static final double GRIFF_ROT_LIMIT_R = 45;
	public static final double GRIFF_ROT_LIMIT_L = 85;
	
	public static final double GRIFF_ROT_SAFE_R = 85;
	public static final double GRIFF_ROT_SAFE_L = 85;
	// in degrees

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private final WPI_VictorSPX griffon = new WPI_VictorSPX(RobotMap.CUBE_SHOOTER);
	private final WPI_VictorSPX griffRot = new WPI_VictorSPX(RobotMap.CUBE_ROTATOR);
	//private final Optional<DoubleSolenoid> griffPneumatics = HardwareUtil.getDoubleSolenoid(RobotMap.GRIFF_SQUEEZE_FWD, RobotMap.GRIFF_SQUEEZE_REV);
	private DoubleSolenoid griffSqz = new DoubleSolenoid(RobotMap.GRIFF_SQUEEZE_FWD, RobotMap.GRIFF_SQUEEZE_REV);
	// Measures the rotation of the griff
	private final Potentiometer griffRotSensor = new AnalogPotentiometer(RobotMap.CUBE_ROTATOR_SENSOR);

	private final Optional<DigitalInput> cubePresent = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_CUBE_PRESENT);

	private void rotateCube(double speed, Double angle) {
		if(Robot.ss_elevator.get() < 100) {
			
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

	public void sqzGriff(boolean pState) {
		DoubleSolenoid.Value direction = pState ? Value.kReverse : Value.kForward;
		//griffPneumatics.ifPresent(p -> p.set(direction));
		griffSqz.set(direction);
	}
	
	public double getAngle() {
		// TODO: convert raw data to angles
		double dataRaw = griffRotSensor.get();
		System.out.println(dataRaw);
		
		return dataRaw;
	} 
	
	//TODO : use pid to slow down imput at the very end of the ROT 
	
	public boolean rotInRange() {
		//true returns safe
		return getAngle() <= GRIFF_ROT_LIMIT_R && getAngle() >= GRIFF_ROT_LIMIT_L ;
	}

	public boolean rotSaftey() {
		//true returns safe
		return getAngle() <= GRIFF_ROT_SAFE_R && getAngle() >= GRIFF_ROT_SAFE_L ;
	}	
	public boolean getSwitchState() {
		boolean jank;
		// Returns digital input result if exists; false otherwise
		if (cubePresent.map(DigitalInput::get).orElse(false)) {
			jank = false;
			
		}
		else {
			jank = true;
		}
		return jank;
	}

}
