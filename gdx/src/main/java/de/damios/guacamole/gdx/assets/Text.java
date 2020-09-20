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

import java.io.UnsupportedEncodingException;

import com.badlogic.gdx.files.FileHandle;

/**
 * A simple asset type for text files. Uses the UTF-8-encoding if supported by
 * the local JVM; the platform's default encoding otherwise.
 *
 * @author damios
 * @see TextLoader The respective asset loader.
 * @see <a href="https://gamedev.stackexchange.com/a/101331">Inspired by
 *      https://gamedev.stackexchange.com/a/101331</a>
 */
public class Text {

	private static final String CHARSET = "UTF-8"; // Charset.defaultCharset() etc. is not supported on GWT
	private String string;

	public Text(String string) {
		this(string.getBytes());
	}

	public Text(FileHandle file) {
		this(file.readBytes());
	}

	public Text(byte[] bytes) {
		try {
			this.string = new String(bytes, CHARSET);
		} catch (UnsupportedEncodingException e) {
			this.string = new String(bytes);
		}
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