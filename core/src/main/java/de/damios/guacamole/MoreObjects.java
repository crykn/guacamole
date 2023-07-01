/*
 * Copyright 2023 damios
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

import java.util.Objects;

import javax.annotation.Nullable;

/**
 * Contains utilities for dealing with objects that are not already provided by
 * {@link Objects}.
 * 
 * @author damios
 */
public class MoreObjects {

	private MoreObjects() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * Returns the first argument that is not null.
	 *
	 * @return {@code first} if it is not null; {@code second} otherwise,
	 *         provided that is it not null
	 * @throws NullPointerException
	 *             if both {@code first} and {@code second} are null
	 */
	public static <T> T firstNonNull(T first, T second) {
		return first != null ? first : Objects.requireNonNull(second);
	}

}
