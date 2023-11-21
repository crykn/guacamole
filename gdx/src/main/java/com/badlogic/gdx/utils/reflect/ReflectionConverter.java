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

import de.damios.guacamole.annotations.GwtIncompatible;

/**
 * Converts objects from {@link java.lang.reflect} to
 * {@link com.badlogic.gdx.utils.reflect} one's.
 * 
 * @author damios
 */
@GwtIncompatible
public class ReflectionConverter {

	private ReflectionConverter() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts an {@link java.lang.annotation.Annotation} to a
	 * {@link com.badlogic.gdx.utils.reflect.Constructor}.
	 * 
	 * @param annotation
	 * @return
	 */
	public static Annotation convertAnnotationObject(
			java.lang.annotation.Annotation annotation) {
		// The constructor is package private
		return new Annotation(annotation);
	}

	/**
	 * Converts a {@link java.lang.reflect.Constructor} to a
	 * {@link com.badlogic.gdx.utils.reflect.Constructor}.
	 * 
	 * @param constructor
	 * @return
	 */
	public static Constructor convertConstructorObject(
			java.lang.reflect.Constructor constructor) {
		// The constructor is package private
		return new Constructor(constructor);
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

	/**
	 * Converts a {@link java.lang.reflect.Method} to a
	 * {@link com.badlogic.gdx.utils.reflect.Method}.
	 * 
	 * @param method
	 * @return
	 */
	public static Method convertMethodObject(java.lang.reflect.Method method) {
		// The constructor is package private
		return new Method(method);
	}

}
