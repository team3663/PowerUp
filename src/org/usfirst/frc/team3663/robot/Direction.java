package org.usfirst.frc.team3663.robot;

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
	public static Direction[] fromGameData() {
		String gameData = "";
		
		while (gameData.length() > 0)
			gameData = DriverStation.getInstance().getGameSpecificMessage();	
		
		Direction[] d = new Direction[3];
		for (int i = 0; i < 3; i++)
			d[i] = Direction.fromChar(gameData.charAt(i));
		
		return d;
	}
}