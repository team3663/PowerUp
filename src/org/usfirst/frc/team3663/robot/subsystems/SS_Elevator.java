package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_Elevator;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Elevator extends Subsystem {
	// Drum diameter = 1.21875in
	// Circumference = dia * pi = 3.8288in
	// Ticks = 256 ticks / rev
	// 256 / 3.8288in = 66.861 ticks/in
	public static final double TICKS_PER_INCH = 66.86;
	
	// Highest position elevator should go
	public static final int ELEVATOR_MAX = 5600;
	
	// position to go to if elevator hits bottom
	public static final int ELEVATOR_SAFE_BOT_IN = 3;
	
	// position to go to if elevator exceeds maximum
	public static final int ELEVATOR_SAFE_TOP_IN = 3;
	
	// Speed the elevator should go at, i.e. normal speed is multiplied by this.
	// Keep within range (0, 1]
	public static final double ELEVATOR_SPEED = 0.333;
	
	public WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_1);
	public WPI_TalonSRX elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_2);
	 
	private Optional<DigitalInput> limitSwitchTop = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_TOP);
	private Optional<DigitalInput> limitSwitchBottom = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_BOTTOM);
	
	private Encoder encoder = new Encoder(RobotMap.ELEVATOR_ENC_1, RobotMap.ELEVATOR_ENC_2);
	
	public SS_Elevator() {
		//elevator2.follow(elevator1);
	}
	
	public static int inchesToTicks(double inches) {
		return (int)(inches * TICKS_PER_INCH);
	}
	
	public static int clampTicks(double val) {
		return (int) Math.min(Math.max(0, val), ELEVATOR_MAX);
	}

	@Override
	protected void initDefaultCommand() {
		
		setDefaultCommand(new C_Elevator());
		//setDefaultCommand(new C_DisplayEncoders());

	}
	
	public boolean getTop() {
		return !limitSwitchTop.map(DigitalInput::get).orElse(true);
	}
	
	public boolean getBottom() {
		return !limitSwitchBottom.map(DigitalInput::get).orElse(true);
	}
	
	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.
		NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;
		elevator1.setNeutralMode(mode);
		elevator2.setNeutralMode(mode);
	}
	
	double thresh = .05;
	
	/**
	 * Moves the elevator up and down
	 * 
	 * @param speed negative = up; pos = down
	 */
	public void set(double speed) {
		if (get() >= ELEVATOR_MAX && speed < 0)
			speed = 0;
		if (get() <= 0 && speed > 0)
			speed = 0;
		
			elevator1.set(speed*ELEVATOR_SPEED);
			elevator2.set(speed*ELEVATOR_SPEED);
	}
	
	/**
	 * @return The current position of the encoders
	 */
	public int get() {
		return encoder.get();
	}
	
	/**
	 * Sets up encoder for use
	 */
	public void initEnc() {
		encoder.reset();
	}
	
	/**
	 * Make sure the elevator isn't going out of bounds
	 * 
	 * @return true if the elevator doesn't need to correct itself
	 */
	public boolean checkElevator() {
		if (getBottom()) {
			encoder.reset(); // reset the encoder
			
			if (elevator1.get() <= 0) {
				set(0);
				//C_MoveElevatorToPos(ELEVATOR_SAFE_BOT_IN).start();
			}
			
			return false;
		}
		
		if (getTop() || get() >= ELEVATOR_MAX) {
			if (elevator1.get() >= 0) {
				set(0);
				//C_MoveElevatorToPos(ELEVATOR_SAFE_TOP_IN).start();
			}
			
			return false;
		}
		
		return true;
	}

}
