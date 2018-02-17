package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Elevator extends Subsystem {
	// Highest position elevator should go
	private static final int ELEVATOR_MAX = 4000;
	
	private WPI_TalonSRX elevator = new WPI_TalonSRX(RobotMap.ELEVATOR);

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
	
	/**
	 * Lifts elevator unless it's at the top
	 */
	public void moveToTop() {
		elevator.set((getPos() < ELEVATOR_MAX) ? 1 : 0);
	}

}
