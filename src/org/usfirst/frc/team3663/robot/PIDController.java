package org.usfirst.frc.team3663.robot;

/**
 * Generic PID controller for the motors
 * 
 * Only currently implements Proportional and Derivative; integral isn't needed for what
 * we're doing
 */
public class PIDController {
	private static final double MIN_OUTPUT = -1;
	private static final double MAX_OUTPUT = 1;
	
	private double gainProportional;
	private double gainDerivative;
	
	private double target = 0; // Desired position
	
	private boolean firstTime = true;
	private ElapsedTime timer = new ElapsedTime();
	
	public PIDController(double gainProportional, double gainDerivative) {
		this.gainProportional = gainProportional;
		this.gainDerivative = gainDerivative;
	}
	
	/**
	 * Constrains value to min and max output
	 */
	private static double clamp(double val) {
		if (val > MAX_OUTPUT)
			val = MAX_OUTPUT;
		else if (val < MIN_OUTPUT)
			val = MIN_OUTPUT;
		
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
