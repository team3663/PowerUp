package org.usfirst.frc.team3663.robot.subsystems;

import java.util.Optional;

import org.usfirst.frc.team3663.robot.HardwareUtil;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.C_MoveElevatorToPos;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Elevator extends Subsystem {
	// Drum diameter = 1.21875in
	// Circumference = dia * pi = 3.8288in
	// Ticks = 256 ticks / rev
	// 256 / 3.8288in = 66.861 ticks/in
	public static final double TICKS_PER_INCH = 66.86;
	
	// Highest position elevator should go
	public static final int ELEVATOR_MAX = 4000;
	public static final int ELEVATOR_MIN = (int)(TICKS_PER_INCH * 3);
	
	// position to go to if elevator exceeds maximum
	public static final int ELEVATOR_SAFE_AREA = ELEVATOR_MAX - (int)(TICKS_PER_INCH * 3);
	
	// Speed the elevator should go at, i.e. normal speed is multiplied by this.
	// Keep within range (0, 1]
	public static final double ELEVATOR_SPEED = 0.333;
	
	 WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_1);
	 WPI_TalonSRX elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_2);
	 
	private Optional<DigitalInput> limitSwitchTop = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_TOP);
	private Optional<DigitalInput> limitSwitchBottom = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_BOTTOM);
	
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
		elevator1.setNeutralMode(mode);
		elevator2.setNeutralMode(mode);
	}
	
	public void elvSet(double speed) {
		elevator1.set(speed * ELEVATOR_SPEED);
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
		boolean topHit = limitSwitchTop.isPresent() && limitSwitchTop.get().get();
		return getPos() >= ELEVATOR_MAX || topHit;
	}
	
	public boolean atBottom() {
		// Uses both softcoded minimum and hardware limit switch
		boolean bottomHit = limitSwitchBottom.isPresent() && limitSwitchBottom.get().get();
		return getPos() <= ELEVATOR_MIN || bottomHit;
	}
	
	public int thresh = 50;
	public int last_err;
	
	public boolean elvSetPos(int target) {
		
		int err = target-getPos();
		int Kp = 60;
		
		if(target < getPos()+thresh && target > getPos()-thresh) {
			return true;
		}
		else {
			return false;
			//last_err = err;
			
		}
	}
	/**
	 * Make sure the elevator isn't going out of bounds
	 * 
	 * @return true if the elevator doesn't need to correct itself
	 */
	public boolean checkElevator() {
		if (limitSwitchBottom.isPresent() && limitSwitchBottom.get().get())
			initEnc(); // reset the encoder
		
		if (atBottom()) {
			// should always be true so long as the previous `if` is true
			
			// if elevator going down, stop ASAP
			if (elevator1.get() < 0)
				elvSet(0);
			
			return false;
		}
		
		if (atTop()) {
			// if elevator going up, stop ASAP
			if (elevator1.get() > 0)
				elvSet(0);
			
			new C_MoveElevatorToPos(ELEVATOR_SAFE_AREA).start();
			return false;
		}
		
		return true;
	}

}
