package de.damios.guacamole;

import java.util.function.IntPredicate;

public class IntRange implements IntPredicate {

	public static IntRange getInclusive(int lowerBound, int upperBound) {
		return new IntRange(new IntBound(lowerBound, true, true),
				new IntBound(upperBound, true, false));
	}

	public static IntRange getExclusive(int lowerBound, int upperBound) {
		return new IntRange(new IntBound(lowerBound, false, true),
				new IntBound(upperBound, false, false));
	}

	private IntBound lowerBound;
	private IntBound upperBound;

	private IntRange(IntBound lowerBound, IntBound upperBound) {
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
