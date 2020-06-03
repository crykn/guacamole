package de.damios.guacamole;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PreconditionsTest {

	@Test
	public void test() {
		// Argument
		assertThrows(IllegalArgumentException.class, () -> {
			Preconditions.checkArgument(false);
		});
		Preconditions.checkArgument(true);

		assertThrows(IllegalArgumentException.class, () -> {
			Preconditions.checkArgument(false, "abc");
		});
		Preconditions.checkArgument(true, "abc");

		// State
		assertThrows(IllegalStateException.class, () -> {
			Preconditions.checkState(false);
		});
		Preconditions.checkState(true);

		assertThrows(IllegalStateException.class, () -> {
			Preconditions.checkState(false, "abc");
		});
		Preconditions.checkState(true, "abc");

		// NPE
		assertThrows(NullPointerException.class, () -> {
			Preconditions.checkNotNull(null);
		});
		Preconditions.checkNotNull("");

		assertThrows(NullPointerException.class, () -> {
			Preconditions.checkNotNull(null, "abc");
		});
		Preconditions.checkNotNull("", "abc");

	}

}
