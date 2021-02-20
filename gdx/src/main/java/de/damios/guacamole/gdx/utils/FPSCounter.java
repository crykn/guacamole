/*
 * Copyright 2021 damios
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

package de.damios.guacamole.gdx.utils;

import com.badlogic.gdx.math.WindowedMean;

/**
 * A FPS counter. Logs the past frame times every 500ms to be used in a
 * {@link WindowedMean} instance offering {@linkplain WindowedMean#getLowest()
 * minimum}, {@linkplain WindowedMean#getHighest() maximum} and
 * {@linkplain WindowedMean#getMean() mean} values.
 * <p>
 * Has to be updated every frame via {@link #update(float)}.
 * 
 * @author damios
 */
public class FPSCounter {

	private float timeSinceLastTick = 0;
	private int framesThisTick = 0, framesLastTick = 0;
	private boolean inSecondHalf = false;

	private int fps = 0;
	private WindowedMean pastFrameTimes;

	public FPSCounter() {
		pastFrameTimes = new WindowedMean(40);
	}

	public FPSCounter(int maxSnapshotCount) {
		pastFrameTimes = new WindowedMean(maxSnapshotCount);
	}

	/**
	 * @return frame time in ms; is updated every second
	 */
	public int getAverageFrameTime() {
		return 1000 / fps;
	}

	/**
	 * @return the frames per second; is updated every second
	 */
	public int getFramesPerSecond() {
		return fps;
	}

	/**
	 * @return a list of data points for the frame times for every passed half
	 *         second; has the specified max size
	 */
	public WindowedMean getPastFrameTimes() {
		return pastFrameTimes;
	}

	public void update(float delta) {
		timeSinceLastTick += delta;
		framesThisTick++;

		if (timeSinceLastTick >= 0.5F) { // every 0.5 seconds
			// Update FPS counter per second
			if (inSecondHalf) {
				fps = framesThisTick + framesLastTick;
			} else {
				framesLastTick = framesThisTick;
			}
			inSecondHalf = !inSecondHalf;

			// Update FPS list every half second
			pastFrameTimes.addValue(500F / Math.max(framesThisTick, 0.1F));

			framesThisTick = 0;
			timeSinceLastTick = 0;
		}
	}

}
