/*
 * Copyright 2021 damios
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

package de.damios.guacamole;

/**
 * Contains utilities for dealing with classes.
 * 
 * @author damios
 */
public class ClassUtils {

	private ClassUtils() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Abbreviates {@linkplain Class#getName() class names} by turning package names
	 * into single letters. For example,
	 * {@code com.badlogic.gdx.graphics.g2d.Sprite} turns into
	 * {@code c.b.g.g.g.Sprite}.
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getAbbreviatedClassName(final Class<?> clazz) {
		final String className = clazz.getName();
		int packageLevels = 0;

		// Count package levels
		for (int i = 0; i < className.length(); i++) {
			if ('.' == className.charAt(i)) {
				packageLevels++;
			}
		}

		// Shorten names
		final String[] output = new String[packageLevels + 1];

		int startIndex = 0;

		for (int level = 0; level < (packageLevels + 1); level++) {
			if (level == packageLevels) {
				output[level] = className.substring(startIndex);
			} else {
				output[level] = String.valueOf(className.charAt(startIndex));

				startIndex = className.indexOf('.', startIndex) + 1;
			}
		}

		// Join the outputs together
		final StringBuilder buf = new StringBuilder(output.length);
		buf.append(output[0]);
		for (int i = 1; i < output.length; i++) {
			buf.append('.');
			buf.append(output[i]);
		}
		return buf.toString();
	}

}
