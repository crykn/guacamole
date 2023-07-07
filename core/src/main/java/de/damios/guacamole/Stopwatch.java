/*
 * Copyright 2023 damios
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 * http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.damios.guacamole;

import java.util.concurrent.TimeUnit;

/**
 * A simple stopwatch class to measure time.
 */
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

	public long getTime() {
		return isRunning ? System.nanoTime() - startTime + elapsedTime
				: elapsedTime;
	}

	public long getTime(TimeUnit unit) {
		return unit.convert(getTime(), TimeUnit.NANOSECONDS);
	}

}
