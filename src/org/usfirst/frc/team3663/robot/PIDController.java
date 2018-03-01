package org.usfirst.frc.team3663.robot;

/**
 * Generic PID controller for the motors
 *
 * Only currently implements Proportional and Derivative; integral isn't needed
 * for what we're doing // yea but we added it anyway
 */
public final class PIDController {
	private final double minOutput;
	private final double maxOutput;

	private final double gainProportional;
	private final double gainIntegral;
	private final double gainDerivative;

	private boolean firstTime = true;
	private final ElapsedTime timer = new ElapsedTime();

	public PIDController(double gainProportional, double gainIntegral, double gainDerivative,
			double min, double max) {
		this.gainProportional = gainProportional;
		this.gainIntegral = gainIntegral;
		this.gainDerivative = gainDerivative;

		maxOutput = max;
		minOutput = min;
	}

	public PIDController(double gainProportional, double gainIntegral, double gainDerivative) {
		this(gainProportional, gainIntegral, gainDerivative, -1, 1);
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
	 * @return controller output
	 */
	public double get(double error) {
		final double dt = timer.getElapsedSeconds();

		// Proportional
		final double pInput = gainProportional * error;
		
		// Integral
		double iInput = 0;
		if (!firstTime)
			iInput = gainIntegral * error * dt;

		// Derivative
		double dInput = 0;
		if (!firstTime)
			dInput = gainDerivative * error / dt;
		
		
		firstTime = false;

		// Sum them up and constrain them to range [0..1]
		final double result = clamp(pInput + iInput + dInput);
		timer.reset();
		return result;
	}
}
