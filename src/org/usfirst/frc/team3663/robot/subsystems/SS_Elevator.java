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
	// public static final int ELEVATOR_MIN = (int)(TICKS_PER_INCH * 3); //curtis:
	// you hard code the top but not the bottom?
	public static final int ELEVATOR_MIN = 30;

	// position to go to if elevator exceeds maximum
	public static final int ELEVATOR_SAFE_AREA = ELEVATOR_MAX - (int) (TICKS_PER_INCH * 3);

	// Speed the elevator should go at, i.e. normal speed is multiplied by this.
	// Keep within range (0, 1]
	public static final double ELEVATOR_SPEED = .5;

	public WPI_TalonSRX elevator1 = new WPI_TalonSRX(RobotMap.ELEVATOR_1);
	public WPI_TalonSRX elevator2 = new WPI_TalonSRX(RobotMap.ELEVATOR_2);

	private final Optional<DigitalInput> limitSwitchTop = HardwareUtil
			.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_TOP); // TODO figure DIO problem
	private final Optional<DigitalInput> limitSwitchBottom = HardwareUtil
			.getDigitalInput(RobotMap.LIMIT_SWITCH_ELEVATOR_BOTTOM); // TODO figure DIO problem

	private final Encoder encoder = new Encoder(RobotMap.ELEVATOR_ENC_1, RobotMap.ELEVATOR_ENC_2);

	// ***************************************************************************************

	public SS_Elevator() {
		elevator2.follow(elevator1);
		elevator1.setInverted(true);
		elevator2.setInverted(true);
	}
	
	boolean encPluggedIn = true;
	boolean init = false;
	public boolean reset() {
		if(!atBottom() && get() == 0) {
			encPluggedIn = false;
			System.out.println("LIFT ENCODER NOT PLUGGED IN FIX ASAP");
		}
		else
			encPluggedIn = true;
		
	  if (!init) {
	    set(-.4);
	    init = atBottom();
	    if(atBottom())
	      resetEnc();
	    //System.out.println(atBottom());
	    System.out.println("Lowering Elevator  :  " + get() + atBottom());
	    return true;
	  }
	  else
	    return false;
	}


	// UTILITY METHODS
	public static int inchesToTicks(double inches) {
		return (int) (inches * TICKS_PER_INCH);
	}

	public static int clampTicks(double val) {
		return (int) Math.min(Math.max(0, val), ELEVATOR_MAX);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new C_Elevator());
		// setDefaultCommand(new C_DisplayEncoders());

	}

	// SWITCHES AND ENCODER GETS

	public boolean atTop() {
		// Uses both softcoded maximum and hardware limit switch
		return !limitSwitchTop.map(DigitalInput::get).orElse(true); // TODO fix problem with DIO
	}

	public boolean atBottom() { // so far there is no way to see where you are
		// Uses both softcoded minimum and hardware limit switch
		return !limitSwitchBottom.map(DigitalInput::get).orElse(true); // TODO fix problem with DIO
	}

	public void enableBreakMode(boolean breaksEnabled) {
		// If breaks enabled, use Brake mode. Else, use Coast mode.

		// Samuel Response: I knew what it meant in 5 seconds. It's Brake if
		// breaksEnabled, else it's NeutralMode.
		final NeutralMode mode = breaksEnabled ? NeutralMode.Brake : NeutralMode.Coast;
		elevator1.setNeutralMode(mode);
		elevator2.setNeutralMode(mode);
	}

	// @return The current position of the encoders

	public int get() {
		return encoder.get();
	}

	/**
	 * Sets up encoder for use
	 */
	public void resetEnc() {
		encoder.reset(); // TODO figure out why encoder is not working
	}
	

	double thresh = .05;

	public void set(double speed) {
		if (get() < ELEVATOR_MAX) {
			elevator1.set(speed * ELEVATOR_SPEED + .05);
		} else {
			// Samuel Response: I'll enforce curly braces in my code cleanup.
			elevator1.set(0);
		}
	}

	/**
	 * Make sure the elevator isn't going out of bounds
	 *
	 * @return true if the elevator doesn't need to correct itself
	 */
	
	public boolean checkElevator() {
			if (atBottom()) {
				resetEnc(); // reset the encoder
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


	

	// TODO example code simple move to encoder tick not very accurate but should
	// work
	double firstLimit = (TICKS_PER_INCH * 12); // first value is +/-12 in range when near goal
	double secondLimit = (TICKS_PER_INCH * 5); // first value is +/-5 in range when near goal
	double stopLimit = (TICKS_PER_INCH * 1); // first value is +/-1 in range when near goal
	double defaultSpeed = .75;
	double firstSpeed = .25;
	double secondSpeed = .125;

	public boolean MoveTo(double goal) { // goal is currently in ticks
		int dir = 1; // default goal is 1
		final double pos = get(); // get elevator in ticks
		if (goal < pos) {
			dir = -1;
		}
		if (Math.abs(goal - pos) > firstLimit) {
			setSmoothing(dir * defaultSpeed);
		} else if (Math.abs(goal - pos) < firstLimit) {
			setSmoothing(dir * firstSpeed);
		} else if (Math.abs(goal - pos) > secondLimit) {
			setSmoothing(dir * secondSpeed);
		} else if (Math.abs(goal - pos) >= stopLimit) {
			setSmoothing(0);
			return true;
		}
		return false;
	}

	// double Cur_Speed = 0;
	int smoothing = 10; // smoothing change this to add steps don't make value over 20

	
	
	public void setSmoothing(double speed) {
		
	
		
		final int CLOSE_THRESHOLD = 300;
		if (atBottom()) {
			resetEnc();
		}
		
//		if(encoder.get() == 0 && elevator1.get() != 0) {
//			encPluggedIn = false;
//			System.out.println("LIFT ENCODER NOT PLUGGED IN FIX ASAP");
//		} else {
//			encPluggedIn = true;
//		}
		
		System.out.println(get());
		//System.out.println(encPluggedIn);
		double Cur_Speed = elevator1.get();
		if(true) {
			if ((atTop() || get() >= ELEVATOR_MAX) && speed > 0) {
				Cur_Speed = 0;
			} else if ((atBottom() || get() <= ELEVATOR_MIN) && speed < 0) {
				Cur_Speed = 0;
			} else if (get() >= (ELEVATOR_MAX - CLOSE_THRESHOLD) && speed > 0) {
				Cur_Speed = speed / 3;
	
			} else if (get() <= (ELEVATOR_MIN + CLOSE_THRESHOLD) && speed < 0) {
				Cur_Speed = speed / 3;
				if (!atBottom()) {
					Cur_Speed = -.1;
				} else {
					Cur_Speed = 0;
				}
			} else {
				Cur_Speed = (Cur_Speed * (smoothing - 1) + speed) / smoothing; // linearization for
				if (Math.abs(Cur_Speed) < .05) {
					Cur_Speed = 0;
				}
			}
		}
		/*else { //driving without elevator smoothing, unideal
			if(atTop() && speed > 0) {
				Cur_Speed = 0;
			} else if (atBottom() && speed < 0) {
				Cur_Speed = 0; 
			}
		}*/
		//System.out.println(Cur_Speed);
		elevator1.set(Cur_Speed);
	}

}