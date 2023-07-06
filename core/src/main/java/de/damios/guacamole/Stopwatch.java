package de.damios.guacamole;
import java.util.concurrent.TimeUnit;

public class Stopwatch {

	public static Stopwatch createUnstarted() {
		return new Stopwatch();
	}

	public static Stopwatch createStarted() {
		return new Stopwatch().start();
	}

	private boolean isRunning;
	private long startTime;
	private long elapsedTime;

	private Stopwatch() {
		// private default constructor
	}

	public Stopwatch start() {
		isRunning = true;
		startTime = System.nanoTime();
		return this;
	}

	public Stopwatch stop() {
		elapsedTime += (System.nanoTime() - startTime);
		isRunning = false;
		return this;
	}

	public Stopwatch reset() {
		elapsedTime = 0;
		isRunning = false;
		return this;
	}

	public boolean isRunning() {
		return isRunning;
	}

	private long getTime() {
		return isRunning ? System.nanoTime() - startTime + elapsedTime
				: elapsedTime;
	}

	private long getTime(TimeUnit unit) {
		return unit.convert(getTime(), TimeUnit.NANOSECONDS);
	}

}
