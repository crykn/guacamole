package de.damios.guacamole.gdx.graphics;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

import de.damios.guacamole.gdx.graphics.ShaderProgramFactory.ShaderPreconditions;

/**
 * Tests the {@link ShaderPreconditions}.
 */
public class ShaderPreconditionsTest {

	@Test
	public void test() {
		ShaderProgram s = Mockito.mock(ShaderProgram.class);
		Mockito.doReturn(false).when(s).isCompiled();

		assertThrows(GdxRuntimeException.class, () -> {
			ShaderPreconditions.checkCompilation(s);
		});

		Mockito.doReturn(true).when(s).isCompiled();
		ShaderPreconditions.checkCompilation(s);
	}

}
