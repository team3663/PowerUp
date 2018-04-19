package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns both intake wheels the same way, one out, one in, as 
 * to straighten the cube for pickup. It was Nathan-requested. 
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
    protected void end() {
        new CG_CubeCycle().start();
    }
}
