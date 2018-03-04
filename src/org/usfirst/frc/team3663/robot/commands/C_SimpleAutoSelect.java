package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_SimpleAutoSelect extends Command {

	public C_SimpleAutoSelect() {
		requires(Robot.ss_autoSelect);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		final String leverPos = Robot.ss_autoSelect.getLeverPos();
		
		final char nearSwitch = leverPos.charAt(0);

//		//LEFT
//		if (nearSwitch == 'L') {
//			new C__DropIn().start();
//		}
//		else
//		{
//			new C__DriveForwardRelative((int) (13.304 * 12 * 10), 0.8);
//		}

		//RIGHT
		if (nearSwitch == 'R') {
			new CG_SimpleDropCube().start();
		}
		else {
			new C_SimpleDriveForward((int) (13.304 * 12 * 10), 0.8);
		}
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
