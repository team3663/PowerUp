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
	public static final int ELEVATOR_MIN = (int)(TICKS_PER_INCH * 3);				//curtis: you hard code the top but not the bottom?
	
	// position to go to if elevator exceeds maximum
	public static final int ELEVATOR_SAFE_AREA = ELEVATOR_MAX - (int)(TICKS_PER_INCH * 3);
	
	// Speed the elevator should go at, i.e. normal speed is multiplied by this.
	// Keep within range (0, 1]
	public static final double ELEVATOR_SPEED = .5;
	
	public WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_1);
	public WPI_TalonSRX elevator2 = new  WPI_TalonSRX (RobotMap.ELEVATOR_2);
		
	//private Optional<DigitalInput> limitSwitchTop = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_TOP);
//	private Optional<DigitalInput> limitSwitchBottom = HardwareUtil.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_BOTTOM);
	
	//private Encoder encoder = new Encoder(RobotMap.ELEVATOR_ENC_1, RobotMap.ELEVATOR_ENC_2);
	
	//***************************************************************************************
	
	public SS_Elevator() {
		elevator2.follow(elevator1);
		elevator1.setInverted(true);
		elevator2.setInverted(true);
	}
	
	//UTILITY METHODS
	public static int inchesToTicks(double inches) {
		return (int)(inches * TICKS_PER_INCH);
	}
	
	public static int clampTicks(double val) {
		return (int) Math.min(Math.max(0, val), ELEVATOR_MAX);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new C_Elevator());
		//setDefaultCommand(new C_DisplayEncoders());

	}
	
	//SWITCHES AND ENCODER GETS
	public boolean getTop() {								//Curtis: these functions are the same as thoues at the bottom
		return false;//limitSwitchTop.map(DigitalInput::get).orElse(false);
	}
	
	public boolean getBottom() {								//CUrtis: look at line 112
		return false;//limitSwitchBottom.map(DigitalInput::get).orElse(false);
	}
	
	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.
		NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;		//Curtis: complicated code is cool and all but make sure all that view this code know what you mean
		elevator1.setNeutralMode(mode);
		elevator2.setNeutralMode(mode);
	}
	
	
	// @return The current position of the encoders
	
	public int get() {								//curtis question : why is this a thing it seemed unused
		return 0;//encoder.get();
	}
	
	/**
	 * Sets up encoder for use
	 */
	public void resetEnc() {
		//encoder.reset();
	}
	
	double thresh = .05;
	public void set(double speed) {
		if (get() < ELEVATOR_MAX)						//Curtis in this you are gathering data from someing thing that is ais always 0 therr for this will constantly increment
			elevator1.set(speed * ELEVATOR_SPEED + .05);
		else 
			elevator1.set(0);						//Curtis: something to consider make sure that there are curlly braces around if statments incase code gets more complicated 
	}

	
	
	
	public boolean atTop() {							//Curtis: this always returns false
		// Uses both softcoded maximum and hardware limit switch
		return false ;//!limitSwitchTop.map(DigitalInput::get).orElse(true);
	}
	
	public boolean atBottom() {							//so far there is no way to see where you are
		// Uses both softcoded minimum and hardware limit switch
		return false ;//!limitSwitchBottom.map(DigitalInput::get).orElse(true);
	}
	
	/**
	 * Make sure the elevator isn't going out of bounds
	 * 
	 * @return true if the elevator doesn't need to correct itself
	 */
	public boolean checkElevator() {
		if (atBottom()) { 							//curtis comment: links to something that only returns false
			resetEnc(); // reset the encoder				//		  function that does nothing
			// If going down when already at bottom
			if (elevator1.get() < 0) {
				set(0);
				return false;
			}
		}
				
		if (atTop() && elevator1.get() > 0) {
			set(0);
			return false;
		}
				
		return true;
	}

}
