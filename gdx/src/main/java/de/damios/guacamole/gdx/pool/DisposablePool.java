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

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;

/**
 * A {@link Pool} for objects implementing {@link Disposable}. When objects are
 * {@linkplain #discard(Disposable) discarded} they are automatically
 * {@linkplain Disposable#dispose() disposed}.
 * 
 * @param <T>
 * @author damios
 */
public abstract class DisposablePool<T extends Disposable> extends Pool<T> {

	@Override
	protected void discard(T obj) {
		obj.dispose();
	}

}
