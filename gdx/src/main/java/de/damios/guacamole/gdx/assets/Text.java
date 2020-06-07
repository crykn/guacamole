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

import java.nio.charset.Charset;

import com.badlogic.gdx.files.FileHandle;

/**
 * A simple asset type for text files. Uses the UTF-8-encoding if supported by
 * the local JVM.
 *
 * @author damios
 * @see TextLoader The respective asset loader.
 * @see <a href="https://gamedev.stackexchange.com/a/101331">Inspired by
 *      https://gamedev.stackexchange.com/a/101331</a>
 */
public class Text {

	private static final Charset CHARSET = Charset.isSupported("UTF-8")
			? Charset.forName("UTF-8")
			: Charset.defaultCharset();
	private String string;

	public Text(String string) {
		this.string = new String(string.getBytes(), CHARSET);
	}

	public Text(FileHandle file) {
		this(new String(file.readBytes(), CHARSET));
	}

	/**
	 * @return the value of this asset.
	 */
	public String getString() {
		return this.string;
	}

	/**
	 * <i>Do not</i> use this method to retrieve the value of the asset. See
	 * {@link #getString()} instead.
	 */
	@Override
	public String toString() {
		return "Text{string=" + string + "}";
	}

}