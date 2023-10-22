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

package de.damios.guacamole.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import de.damios.guacamole.annotations.GwtIncompatible;

/**
 * A class handling the execution of typically short-lived asynchronous tasks.
 * For this, a {@linkplain Executors#newCachedThreadPool() cached thread pool}
 * is used, i.e. previously constructed threads will be reused, if available,
 * otherwise new threads are created. Threads that have not been used for sixty
 * seconds are terminated and removed from the cache. Thus, a
 * {@link ThreadHandler} that remains idle for long enough will not consume any
 * resources.
 * 
 * @author damios
 */
@GwtIncompatible
public class ThreadHandler {

	private static final ThreadHandler instance = new ThreadHandler("ThreadHandler");
	private final ThreadPoolExecutor cachedPool;

	private ThreadHandler(String name) {
		this.cachedPool = (ThreadPoolExecutor) Executors.newCachedThreadPool(new DaemonThreadFactory(name));
	}

	public static ThreadHandler instance() {
		return instance;
	}

	/**
	 * Executes a task asynchronously.
	 * <p>
	 * For this, a {@linkplain Executors#newCachedThreadPool() cached thread pool}
	 * is used, i.e. previously constructed threads will be reused, if available,
	 * otherwise new threads are created. All threads are daemon threads, so they
	 * don't prevent the JVM from exiting.
	 * 
	 * @param r
	 *            the task to execute
	 * @return a {@link Future} representing pending completion of the task
	 */
	@SuppressWarnings("unchecked")
	public Future<Void> executeRunnable(Runnable r) {
		return (Future<Void>) cachedPool.submit(r);
	}

	/**
	 * Returns the approximate number of threads that are actively executing tasks.
	 *
	 * @return the number of active threads
	 */
	public int getActiveThreadCount() {
		return cachedPool.getActiveCount();
	}

	/**
	 * Returns the number of threads currently in the thread pool.
	 *
	 * @return the current number of threads
	 */
	public int getPoolSize() {
		return cachedPool.getPoolSize();
	}

}
