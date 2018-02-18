package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Elevator extends Subsystem {
	// Highest position elevator should go
	public static final int ELEVATOR_MAX = 4000;
	
	// Drum diameter = 1.21875in
	// Circumference = dia * pi = 3.8288in
	// Ticks = 256 ticks / rev
	// 256 / 3.8288in = 66.861 ticks/in
	public static final double TICKS_PER_INCH = 66.861;
	
	private WPI_TalonSRX elevator = new WPI_TalonSRX(RobotMap.ELEVATOR);
	private DigitalInput limitSwitchTop = new DigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_TOP);
	private DigitalInput limitSwitchBottom = new DigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_BOTTOM);
	
	public static int inchesToTicks(double inches) {
		return (int)(inches * TICKS_PER_INCH);
	}
	
	public static int clampTicks(double val) {
		return (int) Math.min(Math.max(0, val), ELEVATOR_MAX);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.
		NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;
		elevator.setNeutralMode(mode);
	}
	
	public void set(double speed) {
		elevator.set(speed);
	}
	
	/**
	 * Sets up encoder for use
	 */
	public void initEnc() {
		elevator.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public int getPos() {
		return elevator.getSensorCollection().getQuadraturePosition();
	}
	
	public boolean atTop() {
		return getPos() >= ELEVATOR_MAX || limitSwitchTop.get();
	}
	
	public boolean atBottom() {
		return getPos() <= 0 || limitSwitchBottom.get();
	}
	
	/**
	 * Lifts elevator unless it's at the top
	 */
	public void moveToTop() {
		elevator.set(atTop() ? 0 : 1);
	}

}
