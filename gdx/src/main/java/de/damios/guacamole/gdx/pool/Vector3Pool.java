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

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * A pool for {@link Vector3} instances.
 * <p>
 * Provides a static {@linkplain #getInstance() instance}.
 * 
 * @author damios
 */
public class Vector3Pool extends Pool<Vector3> {

	private static final Vector3Pool instance = new Vector3Pool(8);

	public static Vector3Pool getInstance() {
		return instance;
	}

	public Vector3Pool() {
		super();
	}

	public Vector3Pool(int initialCapacity) {
		super(initialCapacity);
	}

	public Vector3Pool(int initialCapacity, int max) {
		super(initialCapacity, max);
	}

	@Override
	protected Vector3 newObject() {
		return new Vector3();
	}

	@Override
	protected void reset(Vector3 obj) {
		obj.setZero();
	}

}
