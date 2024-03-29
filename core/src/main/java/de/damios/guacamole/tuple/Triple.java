/*
 * Copyright 2020 damios
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

import org.jspecify.annotations.Nullable;

/**
 * An immutable triple.
 * 
 * @author damios
 */
public class Triple</* @Nullable */ X, /* @Nullable */ Y, /* @Nullable */ Z>
		extends Pair<X, Y> {
	// On GWT, @Nullable can't be used in type parameters yet; see
	// https://github.com/jspecify/jspecify/issues/184

	public final @Nullable Z z;

	public Triple(X x, Y y, Z z) {
		super(x, y);
		this.z = z;
	}

	@Override
	public String toString() {
		return "Triple{" + x + "," + y + "," + z + "}";
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (!(other instanceof Triple)) {
			return false;
		}

		Triple<?, ?, ?> other_ = (Triple<?, ?, ?>) other;

		return Objects.equals(other_.x, this.x)
				&& Objects.equals(other_.y, this.y)
				&& Objects.equals(other_.z, this.z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((z == null) ? 0 : z.hashCode());
		return result;
	}

}
