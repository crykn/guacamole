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
	 * {@link Exception#printStackTrace()}.
	 */
	public static String getStackTraceAsString(Throwable e) {
		return getStackTraceAsStringImpl(e, "", "");
	}

	// Inspired by the emulated Throwable#printStackTraceImpl
	private static String getStackTraceAsStringImpl(Throwable e, String prefix,
			String ident) {
		String out = "\n" + ident + prefix + e.toString();
		out += getStackTraceItems(e, ident);

		for (Throwable t : e.getSuppressed()) {
			out += getStackTraceAsStringImpl(t, "Suppressed: ", "\t" + ident);
		}

		Throwable theCause = e.getCause();
		if (theCause != null) {
			out += getStackTraceAsStringImpl(theCause, "Caused by: ", ident);
		}

		return out;
	}

	// Inspired by the emulated Throwable#printStackTraceItems
	private static String getStackTraceItems(Throwable e, String ident) {
		String out = "";
		for (StackTraceElement element : e.getStackTrace()) {
			out += "\n" + ident + "\tat " + element;
		}

		return out;
	}

}
