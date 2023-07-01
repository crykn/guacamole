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

import java.io.PrintWriter;
import java.io.StringWriter;

import de.damios.guacamole.annotations.GwtIncompatible;

/**
 * Static methods for dealing with exceptions.
 * 
 * @author damios
 */
public final class Exceptions {

	private Exceptions() {
		throw new UnsupportedOperationException();
	}

	public static void throwAsRuntimeException(Exception e) {
		throw new RuntimeException(e);
	}

	public static void throwAsRuntimeException(Exception e, String msg) {
		throw new RuntimeException(msg, e);
	}

	/**
	 * Returns a string containing the result of
	 * {@link Exception#printStackTrace() toString()}.
	 */
	@GwtIncompatible // java.io.PrintWriter, java.io.StringWriter
	public static String getStackTraceAsString(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

}
