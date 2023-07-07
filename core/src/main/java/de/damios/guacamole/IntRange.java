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

import java.util.function.IntPredicate;

/**
 * This class denotes a range between to integer bounds.
 */
public class IntRange implements IntPredicate {

	public static IntRange createInclusive(int lowerBound, int upperBound) {
		return new IntRange(new IntBound(lowerBound, true, true),
				new IntBound(upperBound, true, false));
	}

	public static IntRange createExclusive(int lowerBound, int upperBound) {
		return new IntRange(new IntBound(lowerBound, false, true),
				new IntBound(upperBound, false, false));
	}

	private IntBound lowerBound;
	private IntBound upperBound;

	private IntRange(IntBound lowerBound, IntBound upperBound) {
		Preconditions.checkArgument(
				lowerBound.getValue() < upperBound.getValue(),
				"lowerBound cannot be bigger than upperBound");
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	@Override
	public boolean test(int value) {
		return upperBound.test(value) && lowerBound.test(value);
	}

	public IntBound getLowerBound() {
		return lowerBound;
	}

	public IntBound getUpperBound() {
		return upperBound;
	}

	private static class IntBound implements IntPredicate {

		int value;
		boolean inclusive;
		boolean lowerBound;

		public IntBound(int value, boolean inclusive, boolean lowerBound) {
			this.value = value;
			this.inclusive = inclusive;
			this.lowerBound = lowerBound;
		}

		@Override
		public boolean test(int test) {
			if (lowerBound) {
				return test > value || (inclusive && test == value);
			} else {
				return test < value || (inclusive && test == value);
			}
		}

		public int getValue() {
			return value;
		}

		public boolean isInclusive() {
			return inclusive;
		}

		public boolean isLowerBound() {
			return lowerBound;
		}

	}

}
