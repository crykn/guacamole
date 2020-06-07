package de.damios.guacamole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.damios.guacamole.tuple.Triple;

public class TripleTest {

	@SuppressWarnings({ "unlikely-arg-type", "unchecked", "rawtypes" })
	@Test
	public void test() {
		Triple t = new Triple("abc", Integer.valueOf(123),
				Boolean.valueOf(false));

		// Equals
		assertEquals("Triple{abc,123,false}", t.toString());
		assertTrue(t.equals(t));
		assertTrue(!t.equals("asdf"));
		assertTrue(t.equals(new Triple("abc", Integer.valueOf(123),
				Boolean.valueOf(false))));
		assertTrue(!t.equals(new Triple("abc", null, null)));
		assertTrue(!t.equals(new Triple(null, "def", null)));
		assertTrue(!t.equals(new Triple(null, null, Integer.valueOf(123))));

		// Hash code
		assertEquals(92631035, t.hashCode());
	}

}
