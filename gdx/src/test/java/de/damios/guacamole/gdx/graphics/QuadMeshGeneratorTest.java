package de.damios.guacamole.gdx.graphics;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import de.damios.guacamole.gdx.LibgdxUnitTest;
import de.damios.guacamole.gdx.graphics.QuadMeshGenerator;

public class QuadMeshGeneratorTest extends LibgdxUnitTest {

	@Test
	public void testFullScreenQuad() {
		float[] expectedVertices = { 0F, 0F, 0F, 0F, 0F, 0F, 100F, 0F, 0F, 1F,
				200F, 0F, 0F, 1F, 0F, 200F, 100F, 0F, 1F, 1F };
		assertArrayEquals(expectedVertices,
				QuadMeshGenerator.createFullScreenQuad(200, 100, true)
						.getVertices(new float[20]));
	}

	@Test
	public void testFlippedFullScreenQuad() {
		float[] expectedVertices = { 0F, 0F, 0F, 0F, 1F, 0F, 100F, 0F, 0F, 0F,
				200F, 0F, 0F, 1F, 1F, 200F, 100F, 0F, 1F, 0F };
		assertArrayEquals(expectedVertices,
				QuadMeshGenerator.createFullScreenQuad(200, 100, false)
						.getVertices(new float[20]));
	}

}
