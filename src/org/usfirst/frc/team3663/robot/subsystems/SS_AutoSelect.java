package org.usfirst.frc.team3663.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_AutoSelect extends Subsystem {

    public String getLeverPos() {
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
        return gameData;
    }

    public void initDefaultCommand() {
    }
}
