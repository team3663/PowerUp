package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_Elevator extends Command {
    public C_Elevator() {
    	requires(Robot.ss_elevator);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.ss_elevator.enableBreakMode(true);
    	//Robot.ss_elevator.elevator1.setNeutralMode(NeutralMode.Brake);
		//Robot.ss_elevator.elevator2.setNeutralMode(NeutralMode.Brake);

    }

    @Override
    protected void execute() {
    	//Robot.ss_elevator.set(Robot.oi.driveStick.getRawAxis(5));
    	System.out.println("ELEVATOR  :  "  + Robot.ss_elevator.get());
    	System.out.println("BOT  :  "  + Robot.ss_elevator.getBottom());
    	System.out.println("TOP  :  "  + Robot.ss_elevator.getTop());
    	
		Robot.ss_elevator.set(Robot.oi.driveStick.getRawAxis(5));
		//Robot.ss_elevator.checkElevator();
    	// Makes sure elevator is in working order
    	//Robot.ss_elevator.checkElevator();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
