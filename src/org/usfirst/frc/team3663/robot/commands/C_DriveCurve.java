package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.PIDController;
import org.usfirst.frc.team3663.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive forward set amount of ticks.
 *
 */
public class C_DriveCurve extends Command {
	private int THRESHOLD_TICKS = Robot.ss_drivetrain.inchesToTicks(3.0);

	private final int destination;
	private final PIDController controller;

	/**
	 *
	 * @param ticks
	 *            Negative value makes it go backwards instead
	 * @param speed
	 *            Maximum speed for robot
	 */
	double start = 0;
	double targetTime = 0;
	double current= 0;
	boolean neg = false;
	double turn;
	boolean leftSide;
	int half = 0;
	boolean turnFin = false;
	
	public C_DriveCurve(int inches, double speed, double turn) {
		requires(Robot.ss_drivetrain);

		if (inches < 0) {
			neg = true;
			inches = inches*-1;
		}
		if(turn < 0) {
			leftSide = true;
		}
		else {
			leftSide = false;
		}
		targetTime = inches/(speed*27.25); //TODO: it was instatnly timing out because this was set to a negetive value
		System.out.println(targetTime);
		
		int ticks = Robot.ss_drivetrain.inchesToTicks(inches);
		
		destination = ticks;
		this.half = destination/2;
		this.turn = turn;
		controller = new PIDController(.025, 0, 0, -speed, speed);
	}
	
	
	private int getError() {
		return destination - getSide();
	}
	private int getSide() {
		if(!leftSide) {
			return Robot.ss_drivetrain.getLeft();
		}
		else {
			return Robot.ss_drivetrain.getRight();
		}
	}
	
	@Override
	protected void initialize() {
		this.start = Timer.getFPGATimestamp();
		Robot.ss_gyro.fakeResetGyro();
		Robot.ss_drivetrain.reset();
	}
	
	@Override
	protected void execute() {
		// set speed from PID controller
		double error = getError();
		double speed = controller.get(getError());
		
		this.current = Timer.getFPGATimestamp();
		// debug info
		System.out.println("\nDest: " + destination + "\tPos: " + Robot.ss_drivetrain.getLeft() + "\tErr: " + error + "\nSpd: " + speed);
		System.out.println(turn);
		
		if(getSide() > half && !turnFin) {
			turnFin = true;
			if(turn < 0) {
				turn -= .07;
			}
			else {
				turn += .07;
			}
			turn *= -1;
			if(leftSide)
				leftSide = false;
			else
				leftSide = true;
		}
		
		if(neg)
			Robot.ss_drivetrain.driveCurve(-speed, turn);
		else
			Robot.ss_drivetrain.driveCurve(speed, turn);
	}

	@Override
	protected boolean isFinished() {
		if (targetTime <= current-start)
			return true;
		else
			return Math.abs(getError()) < THRESHOLD_TICKS;
	}

	@Override
	protected void end() {
		
		System.out.println(current-start);
		// Stop wheels
		Robot.ss_drivetrain.stop();
	}

}
