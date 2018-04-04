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

    protected void initialize() {
    	Robot.ss_camera.turnLightOn(0);
    	System.out.println("\t\t\tChanged light to blinking");
    }

    protected void execute() {
    	System.out.println("Vision driving at " + speed + " towards " + Robot.ss_camera.getXOffset());
    	Robot.ss_drivetrain.driveCurve(speed, Robot.ss_camera.getXOffset());
    }

    protected boolean isFinished() {
        return !Robot.ss_camera.validTargets() || Robot.ss_camera.getArea() > 30;
    }

    protected void end() {
    	System.out.println("Vision driving finished");
    }
}
