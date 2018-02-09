package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_LimitSwitch extends Subsystem {

	static DigitalInput limitSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void limit() {
		// to fix git
		Robot.oi.last = limitSwitch.get();
	}

}
