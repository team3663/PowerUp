package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_DriveCurve extends Command {
	private int THRESHOLD_TICKS = Robot.ss_drivetrain.inchesToTicks(3.0);

	private final int destination;
	private final int sideVal;
	private final PIDController controller;

	/**
	 * @param turn
	 * 			  How much its turning by
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	double start = 0;
	double targetTime = 0;
	double current = 0;
	boolean neg = false;
	double turn = 0;
	boolean leftSide;
	boolean firstHalf = false;
	int hotZero = 0;
	double TEA = 27;
	
    public C_DriveCurve(double inches, double speed, double turn) {
    	requires(Robot.ss_drivetrain);

    	//makes sure this doesn't timeout immediately
		if (inches < 0) {
			neg = true;
			inches = inches*-1;
		}
		//finds which direction is turning first
		if (turn < 0)
			this.leftSide = true;
		else
			this.leftSide = false;
		
		targetTime = inches/(speed*TEA); //this is more forgiving than driveForwardReletive cuz it turn
		System.out.println(targetTime);
		
		int ticks = Robot.ss_drivetrain.inchesToTicks(inches);
		destination = ticks;
		sideVal = destination/2; //divides the distance in half to allow for curve
		this.turn = turn;
		controller = new PIDController(.025, 0, 0, -speed, speed);
    }

    protected void initialize() {
    	this.start = Timer.getFPGATimestamp();
		Robot.ss_gyro.fakeResetGyro();
		Robot.ss_drivetrain.reset();
    }
	private int getError() {
		return sideVal - getSide();
	}
	private int getSide() {
		if(!firstHalf) {
			if(leftSide)
				return Robot.ss_drivetrain.getLeft();
			else
				return Robot.ss_drivetrain.getRight();
		}
		else {
			if(leftSide)
				return Robot.ss_drivetrain.getLeft() - hotZero;
			else
				return Robot.ss_drivetrain.getRight() - hotZero;
			
		}
	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// set speed from PID controller
		double error = getError();
		double speed = controller.get(getError());
		
		this.current = Timer.getFPGATimestamp();
		// debug info
		//System.out.println("\nDest: " + destination + "\tPos: " + Robot.ss_drivetrain.getLeft() + "\tErr: " + error + "\nSpd: " + speed);
		if(neg)
			Robot.ss_drivetrain.drive(speed, turn);
		else
			Robot.ss_drivetrain.drive(speed, turn);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (targetTime <= current-start)
			return true;
    	if (Math.abs(getError()) < THRESHOLD_TICKS) {
    		firstHalf=true;
    		if(leftSide) {
    			hotZero = Robot.ss_drivetrain.getLeft();
    			leftSide = false;
    		}
    			
    		else {
    			hotZero = Robot.ss_drivetrain.getLeft();
    			leftSide = true;
    		}
    	}
		if(firstHalf)
			return Math.abs(getError()) < THRESHOLD_TICKS;
		else
			return false;
    }
    protected void end() {
    	Robot.ss_drivetrain.stop();
    }

}
