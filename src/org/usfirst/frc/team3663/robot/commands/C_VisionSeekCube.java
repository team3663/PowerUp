 package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_VisionSeekCube extends Command {
	private double speed;
	
    public C_VisionSeekCube(double speed) {
        requires(Robot.ss_camera);
        requires(Robot.ss_drivetrain);
        this.speed = speed; 
    }
	@Override
	protected void initialize() {
		new CG_CubeIn().start();
	}
    @Override
    protected void execute() {
    	System.out.println("Vision driving at " + speed + " towards " + Robot.ss_griff.getSwitchState());
    	Robot.ss_drivetrain.driveCurve(speed, Robot.ss_camera.getXOffset());
    }
    @Override
    protected boolean isFinished() {
        return !Robot.ss_camera.validTargets()  || Robot.ss_griff.getSwitchState();
    }
    @Override
    protected void end() {
    	new CG_CubeReset().start();
    	Robot.ss_drivetrain.stop();
    }
}
