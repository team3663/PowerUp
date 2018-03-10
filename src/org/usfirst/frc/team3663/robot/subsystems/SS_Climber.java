package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 *
public class SS_Climber extends Subsystem {

    
	private final DoubleSolenoid climberExtend = new DoubleSolenoid(RobotMap.CLIMBER_PNEUM_FWD,
			RobotMap.CLIMBER_PNEUM_REV);
	
	private final WPI_TalonSRX climber1 = new WPI_TalonSRX(RobotMap.CLIMBER_1);
	private final WPI_TalonSRX climber2 = new WPI_TalonSRX(RobotMap.CLIMBER_2);
	
	public SS_Climber() {
		climber2.follow(climber1);
		//elevator1.setInverted(true);
		//elevator2.setInverted(true);
	}
	

    public void initDefaultCommand() {
        
    }
	public void setClimber(boolean pState) {
		final DoubleSolenoid.Value direction = pState ? Value.kReverse : Value.kForward;
		climberExtend.set(direction);
	}
	public void setClimberSpd(double spd) {
		climber1.set(spd);
	}
}
*/
