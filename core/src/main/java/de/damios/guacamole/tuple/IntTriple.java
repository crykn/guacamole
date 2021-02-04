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

import java.util.Objects;

import javax.annotation.Nullable;

/**
 * An immutable integer value triple.
 * 
 * @author damios
 */
public class IntTriple extends IntPair {

	public final int z;

	public IntTriple(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	@Override
	public String toString() {
		return "IntTriple{" + x + "," + y + "," + z + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof IntTriple)) {
			return false;
		}

		IntTriple other_ = (IntTriple) other;
		return x == other_.x && y == other_.y && z == other_.z;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

}
