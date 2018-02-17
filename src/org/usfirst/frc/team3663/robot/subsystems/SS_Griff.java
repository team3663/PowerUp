package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	//private final DoubleSolenoid griffPneumatics = new DoubleSolenoid(RobotMap.CUBE_SHOOTER_FORWARD, RobotMap.CUBE_SHOOTER_REVERSE);
	//private final Potentiometer griffRotSensor = new AnalogPotentiometer(RobotMap.CUBE_ROTATOR_SENSOR);

	private final DigitalInput cubePresent = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE);

	private void rotateCube(double speed, Double angle) {
		griffRot.set(speed);
	}

	public void setGriffSpd(double pSpd) {
		griffon.set(pSpd);
	}

	public void turnCube(double speed) {
		rotateCube(speed, null);
	}

	public void sqzGriff(boolean pState) {
		//DoubleSolenoid.Value direction = pState ? Value.kReverse : Value.kForward;
		//griffPneumatics.set(direction);
	}
	
	public double getAngle() {
		// TODO: Convert raw potentiometer data to angles.
		double dataRaw = /*griffRotSensor.get();*/ 0;
		System.out.println(dataRaw);
		
		return dataRaw;
	}
	
	public boolean rotatorWithinRange() {
		return Math.abs(getAngle()) <= GRIFF_ROT_LIMIT;
	}

	public boolean getSwitchState() {
		return cubePresent.get();
	}

}
