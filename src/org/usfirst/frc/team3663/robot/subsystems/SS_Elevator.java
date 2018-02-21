package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_Elevator;
import org.usfirst.frc.team3663.robot.commands.C_MoveElevatorToPos;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

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
	public static final int ELEVATOR_MIN = (int)(TICKS_PER_INCH * 3);
	
	// position to go to if elevator exceeds maximum
	public static final int ELEVATOR_SAFE_AREA = ELEVATOR_MAX - (int)(TICKS_PER_INCH * 3);
	
	// Speed the elevator should go at, i.e. normal speed is multiplied by this.
	// Keep within range (0, 1]
	public static final double ELEVATOR_SPEED = 0.333;
	
	public WPI_VictorSPX elevator1 = new WPI_VictorSPX(RobotMap.ELEVATOR_1);
	public WPI_VictorSPX elevator2 = new  WPI_VictorSPX (RobotMap.ELEVATOR_2);
	 
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
		// TODO Auto-generated method stub

	}
	
	public boolean getTop() {
		return limitSwitchTop.map(DigitalInput::get).orElse(false);
	}
	
	public boolean getBottom() {
		return limitSwitchBottom.map(DigitalInput::get).orElse(false);
	}
	
	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.
		NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;
		elevator1.setNeutralMode(mode);
		elevator2.setNeutralMode(mode);
	}
	
	double thresh = .05;
	public void set(double speed) {
		if (Robot.oi.driveStick.getRawAxis(5) > thresh && Robot.oi.driveStick.getRawAxis(5) < -thresh)
		{
			elevator1.set(-.1);
			elevator2.set(-.1);
		}
		else {
			elevator1.set(speed);
			elevator2.set(speed);
		}
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
		//TODO convert to greyhill enc
	}
	
	public int getPos() {
		//TODO convert to greyhill enc
		return 1;
	}
	
	
	
	
	public boolean atTop() {
		// Uses both softcoded maximum and hardware limit switch
		return getPos() >= ELEVATOR_MAX || limitSwitchTop.map(DigitalInput::get).orElse(false);
	}
	
	public boolean atBottom() {
		// Uses both softcoded minimum and hardware limit switch
		return getPos() <= ELEVATOR_MIN || limitSwitchBottom.map(DigitalInput::get).orElse(false);
	}
	
	/**
	 * Make sure the elevator isn't going out of bounds
	 * 
	 * @return true if the elevator doesn't need to correct itself
	 */
	public boolean checkElevator() {
		if (limitSwitchBottom.map(DigitalInput::get).orElse(false))
			initEnc(); // reset the encoder
		
		if (atBottom()) {
			// should always be true so long as the previous `if` is true
			
			// if elevator going down, stop ASAP
			if (elevator1.get() < 0)
				set(0);
			
			return false;
		}
		
		if (atTop()) {
			// if elevator going up, stop ASAP
			if (elevator1.get() > 0)
				set(0);
			
			new C_MoveElevatorToPos(ELEVATOR_SAFE_AREA).start();
			return false;
		}
		
		return true;
	}

}
