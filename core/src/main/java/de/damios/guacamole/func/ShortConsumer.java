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
package de.damios.guacamole.func;

import java.util.function.Consumer;

import de.damios.guacamole.Preconditions;

/**
 * Represents an operation that accepts a single {@code float}-valued argument
 * and returns no result. This is the primitive type specialization of
 * {@link Consumer} for {@code short}.
 *
 * <p>
 * This is a functional interface whose functional method is
 * {@link #accept(float)}.
 *
 * @author damios
 * @see Consumer
 */
@FunctionalInterface
public interface ShortConsumer {

	/**
	 * Performs this operation on the given argument.
	 *
	 * @param value
	 *            the input argument
	 */
	void accept(short value);

	/**
	 * Returns a composed {@code ShortConsumer} that performs, in sequence, this
	 * operation followed by the {@code after} operation. If performing either
	 * operation throws an exception, it is relayed to the caller of the
	 * composed operation. If performing this operation throws an exception, the
	 * {@code after} operation will not be performed.
	 *
	 * @param after
	 *            the operation to perform after this operation
	 * @return a composed {@code ShortConsumer} that performs in sequence this
	 *         operation followed by the {@code after} operation
	 */
	default ShortConsumer andThen(ShortConsumer after) {
		Preconditions.checkNotNull(after);
		return (short t) -> {
			accept(t);
			after.accept(t);
		};
	}
}