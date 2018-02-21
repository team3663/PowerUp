package org.usfirst.frc.team3663.robot;

import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;

public enum Direction {
	LEFT, RIGHT;
	
	public static Direction fromChar(char c) {
		switch (c) {
		case 'L':
			return LEFT;
		case 'R':
			return RIGHT;
		default:
			throw new IllegalArgumentException("Character '" + c + "' invalid.");
		}
	}

	/**
	 * Give a list of 3 directions. Each direction corresponds to where our
	 * side of the game elements are:
	 * 
	 * 1: direction of switch at alliance's side
	 * 2: direction of scale
	 * 3: direction of opponent scale
	 */
	public static Optional<Direction[]> fromGameData() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		/*if (gameData == heck) { 
			// If gameData doesn't exist, there's a problem
			System.err.println("WARN: DriverStation gave a null string!");
			return Optional.empty();			
		}*/
			
		if (gameData.length() != 3) {
			// If gameData doesn't have 3 characters, there's a problem
			System.err.println("WARN: Expected message of length 3; got length " + gameData.length());
			return Optional.empty();
		}
			
		
		Direction[] d = new Direction[3];
		for (int i = 0; i < 3; i++)
			d[i] = Direction.fromChar(gameData.charAt(i));
		
		return Optional.of(d);
	}
}
