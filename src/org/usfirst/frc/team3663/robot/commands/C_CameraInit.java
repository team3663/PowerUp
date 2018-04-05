package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_CameraInit extends Command {

    public C_CameraInit() {
        requires(Robot.ss_camera);
    }
    protected boolean isFinished() {
        return Robot.ss_camera.waitAndInitCamera();
    }
}
