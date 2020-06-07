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

package de.damios.guacamole.gdx.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import de.damios.guacamole.gdx.assets.TextLoader.TextParameter;

/**
 * A simple asset loader for {@linkplain Text text} files.
 *
 * @author damios
 * @see <a href="https://gamedev.stackexchange.com/a/101331">Inspired by
 *      https://gamedev.stackexchange.com/a/101331</a>
 */
public class TextLoader extends AsynchronousAssetLoader<Text, TextParameter> {

	public TextLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	private Text currentlyLoadedObject;

	@Override
	public void loadAsync(AssetManager manager, String fileName,
			FileHandle file, TextParameter parameter) {
		this.currentlyLoadedObject = null;
		this.currentlyLoadedObject = new Text(file);
	}

	@Override
	public Text loadSync(AssetManager manager, String fileName, FileHandle file,
			TextParameter parameter) {
		Text text = this.currentlyLoadedObject;
		this.currentlyLoadedObject = null;

		return text;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Array<AssetDescriptor> getDependencies(String fileName,
			FileHandle file, TextParameter parameter) {
		return null;
	}

	public static class TextParameter extends AssetLoaderParameters<Text> {
	}

}
