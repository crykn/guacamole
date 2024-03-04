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

package de.damios.guacamole;

import java.util.Collection;

import org.jspecify.annotations.Nullable;

/**
 * Static methods to help check whether some argument, state or object is valid.
 * If the precondition in question is not met, an unchecked exception is thrown.
 * <p>
 * Inspired by
 * <a href="https://github.com/google/guava/wiki/PreconditionsExplained">
 * guava</a>.
 * 
 * @author damios
 */
public final class Preconditions {

	private Preconditions() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Ensures the truth of the given expression.
	 * 
	 * @param expr
	 * @throws IllegalArgumentException
	 *             if {@code expr} is false
	 */
	public static void checkArgument(boolean expr) {
		if (!expr) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Ensures the truth of the given expression.
	 * 
	 * @param expr
	 * @param msg
	 *            the exception message used
	 * @throws IllegalArgumentException
	 *             if {@code expr} is false
	 */
	public static void checkArgument(boolean expr, @Nullable String msg) {
		if (!expr) {
			throw new IllegalArgumentException(msg);
		}
	}

	/**
	 * Ensures the truth of the given expression.
	 * 
	 * @param expr
	 * @throws IllegalStateException
	 *             if {@code expr} is false
	 */
	public static void checkState(boolean expr) {
		if (!expr) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Ensures the truth of the given expression.
	 * 
	 * @param expr
	 * @param msg
	 *            the exception message used
	 * @throws IllegalStateException
	 *             if {@code expr} is false
	 */
	public static void checkState(boolean expr, @Nullable String msg) {
		if (!expr) {
			throw new IllegalStateException(msg);
		}
	}

	/**
	 * Ensures that the given object reference is not null.
	 * 
	 * @param obj
	 * @throws NullPointerException
	 *             if {@code obj} is {@code null}
	 */
	public static void checkNotNull(@Nullable Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Ensures that the given object reference is not null.
	 * 
	 * @param obj
	 * @param msg
	 *            the exception message used
	 * @throws NullPointerException
	 *             if {@code obj} is {@code null}
	 */
	public static void checkNotNull(@Nullable Object obj,
			@Nullable String msg) {
		if (obj == null) {
			throw new NullPointerException(msg);
		}
	}

	/**
	 * Ensures that the given collection is not empty.
	 * 
	 * @param <E>
	 * @param coll
	 * @throws IllegalStateException
	 *             if {@code coll} is empty
	 */
	public static <E> void checkNotEmpty(Collection<E> coll) {
		checkArgument(!coll.isEmpty());
	}

	/**
	 * Ensures that the given collection is not empty.
	 * 
	 * @param <E>
	 * @param coll
	 * @param msg
	 *            the exception message used
	 * @throws IllegalStateException
	 *             if {@code coll} is empty
	 */
	public static <E> void checkNotEmpty(Collection<E> coll, String msg) {
		checkArgument(!coll.isEmpty(), msg);
	}

}
