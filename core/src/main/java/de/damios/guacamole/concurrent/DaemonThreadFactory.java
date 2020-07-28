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
import java.util.concurrent.ThreadFactory;

import de.damios.guacamole.annotations.GwtIncompatible;

/**
 * A {@link ThreadFactory} that creates {@linkplain Thread#isDaemon() daemon}
 * threads.
 * 
 * @author damios
 */
@GwtIncompatible
public class DaemonThreadFactory implements ThreadFactory {

	private String name;
	private int i = 0;

	public DaemonThreadFactory(String name) {
		this.name = name + " ";
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = Executors.defaultThreadFactory().newThread(r);
		t.setName(name + i++);
		t.setDaemon(true);
		return t;
	}

}
