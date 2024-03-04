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

package de.damios.guacamole.tuple;

import org.jspecify.annotations.Nullable;

/**
 * An immutable integer value pair.
 * 
 * @author damios
 */
public class IntPair {

	public final int x;
	public final int y;

	public IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "IntPair{" + x + "," + y + "}";
	}

	@Override
	public boolean equals(@Nullable Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof IntPair)) {
			return false;
		}

		IntPair other_ = (IntPair) other;
		return x == other_.x && y == other_.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

}
