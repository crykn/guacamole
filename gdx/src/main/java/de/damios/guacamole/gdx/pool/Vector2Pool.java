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

package de.damios.guacamole.gdx.pool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * A pool for {@link Vector2} instances.
 * <p>
 * Provides a static {@linkplain #getInstance() instance}.
 * 
 * @author damios
 */
public class Vector2Pool extends Pool<Vector2> {

	private static final Vector2Pool instance = new Vector2Pool(8);

	public static Vector2Pool getInstance() {
		return instance;
	}

	public Vector2Pool() {
		super();
	}

	public Vector2Pool(int initialCapacity) {
		super(initialCapacity);
	}

	public Vector2Pool(int initialCapacity, int max) {
		super(initialCapacity, max);
	}

	@Override
	protected Vector2 newObject() {
		return new Vector2();
	}

	@Override
	protected void reset(Vector2 obj) {
		obj.setZero();
	}

}
