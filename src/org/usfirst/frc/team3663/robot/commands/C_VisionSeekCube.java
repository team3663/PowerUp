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
        this.speed = speed; ////////////////Change this to speed
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ss_camera.turnLightOn(2);
    	System.out.println("\t\t\tChanged light to blinking");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Vision driving at " + speed + " towards " + Robot.ss_camera.getXOffset());
    	Robot.ss_drivetrain.driveCurve(speed, Robot.ss_camera.getXOffset());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.ss_camera.validTargets() || Robot.ss_camera.getArea() > 30;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Vision driving finished");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
