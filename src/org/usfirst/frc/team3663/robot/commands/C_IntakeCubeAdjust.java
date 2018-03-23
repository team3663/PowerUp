package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_IntakeCubeAdjust extends Command {
	
	double speed;

    public C_IntakeCubeAdjust(double speed) {
        requires(Robot.ss_cubeIntake);
        this.speed = speed;
    }

    protected void execute() {
    	Robot.ss_cubeIntake.spinIntakeSameWay(speed);
    }

    protected boolean isFinished() {
        return true;
    }
}
