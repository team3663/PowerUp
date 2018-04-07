package org.usfirst.frc.team3663.robot.subsystems;

import org.usfirst.frc.team3663.robot.Robot;

import org.usfirst.frc.team3663.robot.RobotMap;
import org.usfirst.frc.team3663.robot.commands.CG_AutoHotScale;
import org.usfirst.frc.team3663.robot.commands.CG_AutoHotSwitch;
import org.usfirst.frc.team3663.robot.commands.CG_NewAutoCenter;
import org.usfirst.frc.team3663.robot.commands.CG_AutoCenter;
import org.usfirst.frc.team3663.robot.commands.CG_AutoCurveCenter;
import org.usfirst.frc.team3663.robot.commands.CG_AutoFarScale;
import org.usfirst.frc.team3663.robot.commands.C_DriveForwardRelative;
import org.usfirst.frc.team3663.robot.commands.C_MoveElevatorToPos;
import org.usfirst.frc.team3663.robot.commands.C_Wait;

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

	@Deprecated
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
	public boolean gameMessagePresesnt() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData == "" || gameData == null)
			return false;
		else
			return true;
	}
	
	/*private final Potentiometer posPot = new AnalogPotentiometer(RobotMap.AUTO_POS_POT);
	
	public double getAngle() {
		double dataRaw = posPot.get() * 100;
		System.out.println(dataRaw);

		return dataRaw ;
	}*/

	public boolean waitForMessage() {
		String gameData = getLeverPos();
		if(gameData == "" || gameData == null)
			return false;
		else
			return true;
			
	}
	public String getLeverPos() {
		final String gameData = DriverStation.getInstance().getGameSpecificMessage();
		return gameData;
	}
	
	private Command selected;
	
	public void runAuto(int location) {
		final String leverPos = Robot.ss_autoSelect.getLeverPos();

		//final double location = Robot.ss_autoSelect.getAngle();

		//System.out.println("                       "+ location  +"        THIS SHOULD ONLY BE SEEN ONCE");
		final char nearSwitch = leverPos.charAt(0);
		final char scale = leverPos.charAt(1);
		final char farSwitch = leverPos.charAt(2);
		
		
		
		final boolean left = false;
		final boolean right = true;
		
		final boolean startLeft = false;
		final boolean startRight = true;
		
		final int n = 0;     // nothing
	    final int c = 1;     // center
	    
	    final int lw = 2;    // left switch only
	    final int rw = 3;    // right switch only
	    
	    final int lc = 4;    // left scale priority
	    final int rc = 5;    // right scale priority
	    
	    final int lwp = 6;   // left switch priority
	    final int rwp = 7;   // right switch priority
	    
	    final int d = 8;     // drive forward
	    
	    //test numbers for test hash
	    final int c2lcp = 9; //two cube left scale priority
	    final int c2rcp = 13;//two cube right scale priority
	    
	    final int c2lwp = 10;//two cube left switch priority
	    final int c2rwp = 12;//two cube right switch priority
	    
	    final int cl = 15; //left chair
	    final int cr = 16; //right chair
	    
	    final int test1 = 11;
	    final int test2 = 14;
	    
	    
	    /////////// NEWER SELECT CODE/////////
	    
	    	//center
	    	if( location == c) {
		    	if (nearSwitch == 'L') {
		    		//selected = new CG_AutoCenter(left);

		    		//selected = new CG_NewAutoCenter(left);
		    		selected = new CG_AutoCurveCenter(left);
		    	} else {
		    		//selected = new CG_AutoCenter(right);
		    		//selected = new CG_NewAutoCenter(right);
		    		selected = new CG_AutoCurveCenter(right);

		    	}
	    	}
	    	//left switch only
	    	else if( location == lw) {
		    	if(nearSwitch == 'L') {
		    		System.out.println("AUTO IS HOT LEFT");
		    		selected = new CG_AutoHotSwitch(left);
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT LEFT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//right switch only
		    else if( location == rw) {
		    	if(nearSwitch == 'R') {
		    		selected = new CG_AutoHotSwitch(right);
		    		System.out.println("AUTO HOT RIGHT");
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT RIGHT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//hotscale priority left
		    else  if( location == lc) {
		    	if (scale == 'L') {
		    		selected = new CG_AutoHotScale(left);
		    	} else if(nearSwitch == 'L') {
		    		selected = new CG_AutoHotSwitch(left);
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//hotscale priority right
		    else if( location == rc) {
		    	if (scale == 'R') {
		    		selected = new CG_AutoHotScale(right);
		    	} else if(nearSwitch == 'R') {
		    		selected = new CG_AutoHotSwitch(right);
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//hotswitch priorty left
		    else if( location == lwp) {
		    	if (nearSwitch == 'L') {
		    		selected = new CG_AutoHotSwitch(left);
		    	} else if (scale == 'L') {
		    		selected = new CG_AutoHotScale(left);
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//hotswitch  priority right
		    else if( location == rwp) {
		    	if (nearSwitch == 'R') {
		    		selected = new CG_AutoHotSwitch(right);
		    	} else if(scale == 'R') {
		    		selected = new CG_AutoHotScale(right);
		    	} else {
		    		System.out.println("WARNING: ATUO IS STRAIGHT");
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//driveforward
		    else if( location == d) {
		    	selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    //nothing
		    else if( location == n) {
		    	selected = C_Wait.fromSeconds(15);
	    	}
	    	
	    	//***********test autos************//
	    	
	    	//two cube right side scale priority
		    else if (location == c2rcp) {
		    	if(scale == 'R' && nearSwitch == 'R') {
		    		//2cube auto
		    	}
		    	else if (scale == 'R') {
		    		selected = new CG_AutoHotScale(right);
		    	}
		    	else if (nearSwitch == 'R') {
		    		selected = new CG_AutoHotSwitch(right);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//two cube left side  scale priority
		    else if (location == c2lcp) {
		    	if(scale == 'L' && nearSwitch == 'L') {
		    		//2cube auto
		    	}
		    	else if (scale == 'L') {
		    		selected = new CG_AutoHotScale(left);
		    	}
		    	else if (nearSwitch == 'L') {
		    		selected = new CG_AutoHotSwitch(left);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//two cube right side switch priority
		    else if (location == c2rwp) {
		    	if(scale == 'R' && nearSwitch == 'R') {
		    		//2cube auto
		    	}
		    	else if (nearSwitch == 'R') {
		    		selected = new CG_AutoHotSwitch(right);
		    	}
		    	else if (scale == 'R') {
		    		selected = new CG_AutoHotScale(right);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//two cube left side switch priority
		    else if (location == c2lwp) {
		    	if(scale == 'L' && nearSwitch == 'L') {
		    		//2cube auto
		    	}
		    	else if (nearSwitch == 'L') {
		    		selected = new CG_AutoHotSwitch(left);
		    	}
		    	else if (scale == 'L') {
		    		selected = new CG_AutoHotScale(left);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//left chair
		    else if (location == cl) {
		    	if(scale == 'L') {
		    		selected = new CG_AutoHotScale(left);
		    	}
		    	else if (scale == 'R') {
		    		selected = new CG_AutoFarScale(right);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//right chair
		    else if (location == cr) {
		    	if(scale == 'R') {
		    		selected = new CG_AutoHotScale(right);
		    	}
		    	else if (scale == 'L') {
		    		selected = new CG_AutoFarScale(left);
		    	}
		    	else {
		    		selected = new C_DriveForwardRelative(145, 0.6);
		    	}
		    }
	    	//currently used for retired center auto
		    else if (location == test1) {
		    	if (nearSwitch == 'L') {
		    		selected = new CG_AutoCenter(left);
		    		//selected = new CG_NewAutoCenter(left);
		    		//selected = new CG_AutoCurveCenter(left);
		    	} else {
		    		selected = new CG_AutoCenter(right);
		    		//selected = new CG_NewAutoCenter(right);
		    		//selected = new CG_AutoCurveCenter(right);
		    	}
		    	
		    }
	    	
		    else if (location == test2) {
		    		
		    }
	    	//if nothing is selected for some reason.
		    else {
		    	selected = new C_Wait(15);
		    }
	    	
		 
	    selected.start();
	    System.out.println(selected);
	   
}

	@Override
	public void initDefaultCommand() {
	}

	public void end() {
		System.out.println("WARNING:               AUTO HAS BEEN CANCLED FOR SOME REASON");
		selected.cancel();

	}
}
