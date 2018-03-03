package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

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

			while (gameData.length() > 0) {
				gameData = DriverStation.getInstance().getGameSpecificMessage();
			}

			final Direction[] d = new Direction[3];
			for (int i = 0; i < 3; i++) {
				d[i] = Direction.fromChar(gameData.charAt(i));
			}

			return d;
		}
	}

	public String getLeverPos() {
		final String gameData = DriverStation.getInstance().getGameSpecificMessage();
		return gameData;
	}

	@Override
	public void initDefaultCommand() {
	}
}
