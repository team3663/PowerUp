package org.usfirst.frc.team3663.robot;

public class ElapsedTime {
	private long time = 0;

	public ElapsedTime() {
		reset();
	}

	public void reset() {
		time = System.nanoTime();
	}

	public long getElapsedNano() {
		final long currTime = System.nanoTime();
		return currTime - time;
	}

	public long getElapsedMillis() {
		return getElapsedNano() / 1000000;
	}

	public double getElapsedSeconds() {
		return getElapsedNano() / 1000000000.0;
	}
}