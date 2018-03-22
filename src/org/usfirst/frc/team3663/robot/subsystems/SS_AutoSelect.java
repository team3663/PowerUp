package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;
import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.CG_AutoHotScale;
import org.usfirst.frc.team3663.robot.commands.CG_AutoHotSwitch;
import org.usfirst.frc.team3663.robot.commands.CG_AutoMidDiffSide;
import org.usfirst.frc.team3663.robot.commands.CG_AutoMidSameSide;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardRelative;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardSimple;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

// Samuel: We have to derive the game element positions by characters from getGameSpeficMessage().
//   I made the enum Direction to abstract away from that as much as possible.
/**
 *
 */
public class SS_AutoSelect extends Subsystem {

	public enum Direction {
		LEFT, RIGHT;

		public static Direction fromChar(char c) {
			switch (c) {
			case 'L':
				return LEFT;
			case 'R':
				return RIGHT;
			default:
				// Samuel: getGameSpecificMessage(), as per the manual, should *only* return
				// either L's or R's. Any other result would
				// be unpredicable, and if that's the case, the robot *should* do nothing.
				throw new IllegalArgumentException("Character '" + c + "' invalid.");
			}
		}

		/**
		 * Give a list of 3 directions. Each direction corresponds to where our side
		 * of the game elements are:
		 *
		 * 1: direction of switch at alliance's side
		 * 2: direction of scale
		 * 3: direction of opponent scale
		 */
		
		public static Direction[] fromGameData() {
			String gameData = "";

			if (gameData.length() > 0) {
				gameData = DriverStation.getInstance().getGameSpecificMessage();
			}

			final Direction[] d = new Direction[3];
			for (int i = 0; i < 3; i++) {
				d[i] = Direction.fromChar(gameData.charAt(i));
			}

			return d;
		}
	}
	
	/*private final Potentiometer posPot = new AnalogPotentiometer(RobotMap.AUTO_POS_POT);
	
	public double getAngle() {
		double dataRaw = posPot.get() * 100;
		System.out.println(dataRaw);

		return dataRaw ;
	}*/

	public String getLeverPos() {
		final String gameData = DriverStation.getInstance().getGameSpecificMessage();
		return gameData;
	}
	
	private Command selected;
	
	public void runAuto(int location) {
		final String leverPos = Robot.ss_autoSelect.getLeverPos();

		//final double location = Robot.ss_autoSelect.getAngle();

		final char nearSwitch = leverPos.charAt(0);
		final char scale = leverPos.charAt(1);
		final char farSwitch = leverPos.charAt(2);
		
		final boolean left = false;
		final boolean right = true;
		
		final int n = 0;    // nothing
	    final int c = 1;    // center
	    final int lw = 2;    // left switch
	    final int rw = 3;    // right switch
	    final int lc = 4;    // left scale
	    final int rc = 5;    // right scale
	    
	    
	    /////////// NEWER SELECT CODE/////////PROBABLY SCRAP LESS NEW SELECT CODE///////////
	    if(location == c) {
	    	if (nearSwitch == 'L' && scale == 'L') {
				selected = new CG_AutoMidSameSide(left);
			} else if (nearSwitch == 'R' && scale == 'R') {
				selected = new CG_AutoMidSameSide(right);
			} else if (nearSwitch == 'L' && scale == 'R') {
				selected = new CG_AutoMidDiffSide(left);
			} else if (nearSwitch == 'R' && scale == 'L') {
				selected = new CG_AutoMidDiffSide(right);
			}
	    } else if (location == lw) {
	    	if(nearSwitch == 'L') {
	    		System.out.println("AUTO IS HOT LEFT");
	    		selected = new CG_AutoHotSwitch(left);
	    	} else {
	    		System.out.println("WARNING: ATUO IS STRAIGHT");
	    		selected = new C_DriveForwardRelative(50, 0.5);
	    	}
	    } else if (location == rw) {
	    	if(nearSwitch == 'R') {
	    		selected = new CG_AutoHotSwitch(right);
	    	} else {
	    		System.out.println("WARNING: ATUO IS STRAIGHT");
	    		selected = new C_DriveForwardRelative(50, 0.5);
	    	}
	    } else if (location == lc) {
	    	if (scale == 'L') {
	    		selected = new CG_AutoHotScale(left);
	    	} else if(nearSwitch == 'L') {
	    		selected = new CG_AutoHotSwitch(left);
	    	} else {
	    		System.out.println("WARNING: ATUO IS STRAIGHT");
	    		selected = new C_DriveForwardRelative(50, 0.5);
	    	}
	    } else if (location == rc) {
	    	if (scale == 'R') {
	    		selected = new CG_AutoHotScale(right);
	    	} else if(nearSwitch == 'R') {
	    		selected = new CG_AutoHotSwitch(right);
	    	} else {
	    		System.out.println("WARNING: ATUO IS STRAIGHT");
	    		selected = new C_DriveForwardRelative(50, 0.5);
	    	}
	    	
	    }
	    selected.start();
	   
	}

	@Override
	public void initDefaultCommand() {
	}

	public void end() {
		selected.cancel();

	}
}
