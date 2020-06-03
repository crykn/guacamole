package de.damios.guacamole.gdx;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MeshGeneratorTest extends LibgdxUnitTest {

	@Test
	public void testFullScreenQuad() {
		float[] expectedVertices = { 0F, 0F, 0F, 0F, 0F, 0F, 100F, 0F, 0F, 1F,
				200F, 0F, 0F, 1F, 0F, 200F, 100F, 0F, 1F, 1F };
		assertArrayEquals(expectedVertices,
				MeshGenerator.createFullScreenQuad(200, 100, true)
						.getVertices(new float[20]));
	}

	@Test
	public void testFlippedFullScreenQuad() {
		float[] expectedVertices = { 0F, 0F, 0F, 0F, 1F, 0F, 100F, 0F, 0F, 0F,
				200F, 0F, 0F, 1F, 1F, 200F, 100F, 0F, 1F, 0F };
		assertArrayEquals(expectedVertices,
				MeshGenerator.createFullScreenQuad(200, 100, false)
						.getVertices(new float[20]));
	}

}
