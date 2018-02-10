package org.usfirst.frc.team3663.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import java.util.ArrayList;
import java.util.Collection;

import edu.wpi.first.wpilibj.SpeedController;

/*
 * Control a group of speed controllers in unison, i.e. have a group
 * of talons pretend to be one talon
 */
public class SpeedControllerGroup implements SpeedController {
	private final Collection<? extends SpeedController> controllers;
	
	public SpeedControllerGroup(Collection<? extends SpeedController> controllers) {
		this.controllers = controllers;
	}
	
	/**
	 * If given a variable amount of id's, assume they're all talons.
	 */
	public SpeedControllerGroup(int... ids) {
		ArrayList<WPI_TalonSRX> talons = new ArrayList<>();
		for (int id : ids)
			talons.add(new WPI_TalonSRX(id));
		
		this.controllers = talons;
	}
	
	public Collection<? extends SpeedController> getControllers() {
		return controllers;
	}
	
	/*
	 * For the rest of these methods, they just iterate over each
	 * controller and calls the method. Getters are different, and
	 * each getter is explained below.
	 */

	@Override
	public void pidWrite(double output) {
		for (SpeedController controller : controllers)
			controller.pidWrite(output);
	}

	@Override
	public void set(double speed) {
		for (SpeedController controller : controllers)
			controller.set(speed);
	}

	/**
	 * Returns the speed of any controller. Default 0 if empty.
	 */
	@Override
	public double get() {
		for (SpeedController controller : controllers)
			return controller.get();
		
		return 0; //default value
	}

	@Override
	public void setInverted(boolean isInverted) {
		for (SpeedController controller : controllers)
			controller.setInverted(isInverted);
	}

	/**
	 * Returns true only if *all* of them are inverted
	 */
	@Override
	public boolean getInverted() {
		for (SpeedController controller : controllers)
			if (!controller.getInverted())
				return false;
		
		return true;
	}

	@Override
	public void disable() {
		for (SpeedController controller : controllers)
			controller.disable();
	}

	@Override
	public void stopMotor() {
		for (SpeedController controller : controllers)
			controller.stopMotor();
	}
	
}
