package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Griff extends Subsystem {
   public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final WPI_TalonSRX griffon = new WPI_TalonSRX(RobotMap.CUBE_SHOOTER);
	private final WPI_TalonSRX griffRot = new WPI_TalonSRX(RobotMap.CUBE_ROTATOR);
	private final DoubleSolenoid griffPneumatics = new DoubleSolenoid(Robot.RobotMap.CUBE_SHOOTER_FORWARD,
			Robot.RobotMap.CUBE_SHOOTER_REVERSE);
	
	private DigitalInput cubePresent = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE);

	private static final double SHOOTER_SPEED = 1.0;
	

	
	private void rotateCube(double speed, Double angle) {
		griffRot.set(speed);
	}
	public void setGriffSpd(double pSpd) {
		griffon.set(pSpd);
	}
	public void turnCube(double speed) {
		rotateCube(speed, null);
	}
	public void sqzGriff(boolean pState) {
		//true = foward
		//false = reverse
		if(pState == true) {
			griffPneumatics.set(DoubleSolenoid.Value.kForward);
		}
		else if (pState == false){
			griffPneumatics.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public boolean getSwitchState() {
		return cubePresent.get();
	}
 
}
