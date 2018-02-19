package org.usfirst.frc.team3663.robot;

/**
 * Generic PID controller for the motors
 * 
 * Only currently implements Proportional and Derivative; integral isn't needed for what
 * we're doing
 */
public class PIDController {
	private final double minOutput;
	private final double maxOutput;
	
	private double gainProportional;
	private double gainDerivative;
	
	private double target = 0; // Desired position
	
	private boolean firstTime = true;
	private ElapsedTime timer = new ElapsedTime();
	
	public PIDController(double gainProportional, double gainDerivative, double min, double max) {
		this.gainProportional = gainProportional;
		this.gainDerivative = gainDerivative;
		
		maxOutput = max;
		minOutput = min;
	}
	
	public PIDController(double gainProportional, double gainDerivative) {
		this(gainProportional, gainDerivative, -1, 1);
	}
	
	public PIDController(double gainProportional, double gainDerivative, double min, double max, double target) {
		this(gainProportional, gainDerivative, min, max);
		set(target);
	}
	
	/**
	 * Constrains value to min and max output
	 */
	private double clamp(double val) {
		if (val > maxOutput)
			val = maxOutput;
		else if (val < minOutput)
			val = minOutput;
		
		return val;
	}
	
	/**
	 * Sets the target value
	 */
	public void set(double target) {
		this.target = target;
	}
	
	/**
	 * @return controller output
	 */
	public double get(double actual) {
		double error = target - actual;
		double dt = timer.getElapsedSeconds();
		
		double pInput = gainProportional * error;
		
		double dInput = 0;
		if (firstTime) {
			firstTime = false;
		} else {
			dInput = gainDerivative * error / dt;
		}
		
		
		// Sum them up and constrain them to range [0..1]
		double result = clamp(pInput + dInput);
		timer.reset();
		return result;
	}
}
