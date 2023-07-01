/*
 * Copyright 2023 eskalon
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class aggregates multiple iterators.
 *
 * @param <E>
 * @author damios
 */
public final class ConcatenatedIterator<E> implements Iterator<E> {

	private final LinkedList<Iterator<E>> iterators;
	private Iterator<E> currentIterator = null;
	private Iterator<E> lastItemFrom = null;

	public ConcatenatedIterator(List<Iterator<E>> iterators) {
		this.iterators = new LinkedList<>(iterators);
	}

	@Override
	public boolean hasNext() {
		return (currentIterator != null && currentIterator.hasNext())
				|| (!iterators.isEmpty() && iterators.getFirst().hasNext());
	}

	@Override
	public E next() {
		if (currentIterator != null && currentIterator.hasNext()) {
			lastItemFrom = currentIterator;
			return currentIterator.next();
		}
		currentIterator = iterators.pollFirst();
		if (currentIterator != null) {
			lastItemFrom = currentIterator;
			return currentIterator.next();
		}
		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		if (lastItemFrom == null)
			throw new IllegalStateException(
					"next() has not been called since the last call to remove()");
		lastItemFrom.remove();
		lastItemFrom = null;
	}
}