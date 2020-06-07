package de.damios.guacamole.gdx.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;

import de.damios.guacamole.gdx.LibgdxUnitTest;

/**
 * Tests {@link Text} & {@link TextLoader}.
 */
public class TextAssetTest extends LibgdxUnitTest {

	public AssetManager createAssetManager() {
		FileHandleResolver resolver = new InternalFileHandleResolver();
		AssetManager aM = new AssetManager(resolver);
		aM.setLoader(Text.class, new TextLoader(new InternalFileHandleResolver()));

		return aM;
	}

	@Test
	public void test() {
		AssetManager aM = createAssetManager();

		aM.load("text.txt", Text.class);
		aM.finishLoading();

		// Check if assets got actually loaded
		assertTrue(aM.isLoaded("text.txt"));
		assertEquals("Hello World!\nHow are you?", aM.get("text.txt", Text.class).getString());
	}

}
