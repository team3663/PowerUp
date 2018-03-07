package org.usfirst.frc.team3663.robot;

public final class ElapsedTime {
	private static long time = 0;

	public ElapsedTime() {
		reset();
	}

	public static void reset() {
		time = System.nanoTime();
	}

	public static long getElapsedNano() {
		final long currTime = System.nanoTime();
		return currTime - time;
	}

	public static long getElapsedMillis() {
		return getElapsedNano() / 1000000;
	}

	public static double getElapsedSeconds() {
		return getElapsedNano() / 1000000000.0;
	}
}
