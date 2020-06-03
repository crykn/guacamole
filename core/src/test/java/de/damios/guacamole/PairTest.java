package de.damios.guacamole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.damios.guacamole.tuple.Pair;

public class PairTest {

	@SuppressWarnings({ "unlikely-arg-type", "unchecked", "rawtypes" })
	@Test
	public void test() {
		Pair t = new Pair("abc", Integer.valueOf(123));

		// Equals
		assertEquals("(abc,123)", t.toString());
		assertTrue(t.equals(t));
		assertTrue(!t.equals("asdf"));
		assertTrue(t.equals(new Pair("abc", Integer.valueOf(123))));
		assertTrue(!t.equals(new Pair("abc", null)));
		assertTrue(!t.equals(new Pair(null, Integer.valueOf(123))));

		// Hash code
		assertEquals(2988058, t.hashCode());
	}

}
