 package org.usfirst.frc.team3663.robot.commands;

import org.usfirst.frc.team3663.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class C_VisionSeekCube extends Command {
	private double speed;
	
	boolean out = false;
	int outCounter = 0;
	boolean valid;
	double PICKLES = 10;
	
	boolean active= true;
	
	double MAX_TURN = .8;
	
    public C_VisionSeekCube(double speed) {
        requires(Robot.ss_camera);
        //requires(Robot.ss_griff);
        requires(Robot.ss_drivetrain);
        this.speed = speed; 
        valid = !Robot.ss_camera.validTargets();
    }
	@Override
	protected void initialize() {
		
	}
    @Override
    protected void execute() {
    	new CG_CubeIn().start();
    	
    	System.out.println("Vision driving at " + speed + " switch:   " + Robot.ss_griff.getSwitchStateBoth()
    	+ "  valid  " + Robot.ss_camera.validTargets() + "  offset is:  " + Robot.ss_camera.getXOffset());
    	
    	double turn = Robot.ss_camera.getXOffset()/PICKLES;
    	
    	if (turn > MAX_TURN) {
    		turn = MAX_TURN;
    	}
    	
    	if(active) {
    		turn = turn + .0001;
    		active = false;
    	}
    	else {
    		turn = turn - .0001;
    		active = true;
    	}
    	
    	Robot.ss_drivetrain.driveCurve(speed, turn);

    	System.out.println(speed + turn);
    	//adjustment code
    	if( Robot.ss_griff.getSwitchStateBoth()) {
    		Robot.ss_griff.setGriffSpd(.2);
    	}	
    	else if(Robot.ss_griff.getSwitchStateAny()) {
    		Robot.ss_cubeIntake.spinIntake(-.5);
    		out = true;
    		
    	}
		if (out && outCounter < 5) {
			outCounter++;
		}
		else {
			Robot.ss_cubeIntake.spinIntake(1);
			outCounter = 0;
			out = false;
		}
    }
    @Override
    protected boolean isFinished() {    	
    	//return false;
        return valid || Robot.ss_griff.getSwitchStateBoth();
    }
    @Override
    protected void end() {
    	new CG_CubeReset().start();
    	Robot.ss_drivetrain.stop();
    }
}
