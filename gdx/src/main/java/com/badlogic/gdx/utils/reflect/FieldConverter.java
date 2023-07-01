/*
 * Copyright 2020 eskalon
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

package com.badlogic.gdx.utils.reflect;

import javax.annotation.Nullable;

import de.damios.guacamole.gdx.log.Logger;
import de.damios.guacamole.gdx.log.LoggerService;

/**
 * Converts a {@link java.lang.reflect.Field} to a
 * {@link com.badlogic.gdx.utils.reflect.Field}.
 * 
 * @author damios
 */
public class FieldConverter {

	private FieldConverter() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts a {@link java.lang.reflect.Field} to a
	 * {@link com.badlogic.gdx.utils.reflect.Field}.
	 * 
	 * @param field
	 * @return
	 */
	public static Field convertFieldObject(java.lang.reflect.Field field) {
		// The constructor is package private
		return new Field(field);
	}

}
