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

package de.damios.guacamole.gdx;

import java.util.Formatter;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Gdx;

import text.formic.Stringf;

/**
 * A static helper class to make logging in libGDX easier. Adds support for
 * {@link String#format(String, Object...)} (or
 * {@link Stringf#format(String, Object...)} on GWT).
 * 
 * @author damios
 */
public class Log {

	public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MILLISECONDS;

	private Log() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Enables only error messages.
	 */
	public static void showOnlyErrors() {
		Gdx.app.setLogLevel(Application.LOG_ERROR);
	}

	/**
	 * Enables info messages, in addition to error messages.
	 */
	public static void showInfo() {
		Gdx.app.setLogLevel(Application.LOG_INFO);
	}

	/**
	 * Enables all log messages.
	 */
	public static void showAll() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	/**
	 * Disables log messages.
	 */
	public static void showNone() {
		Gdx.app.setLogLevel(Application.LOG_NONE);
	}

	/**
	 * Sets the used logger.
	 * 
	 * @param logger
	 * 
	 * @see Application#setApplicationLogger(ApplicationLogger)
	 */
	public static void setLogger(ApplicationLogger logger) {
		Gdx.app.setApplicationLogger(logger);
	}

	/**
	 * Logs an informational message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param tag
	 *            the tag in front of the message; is usually used to denote the
	 *            logging entity
	 * @param message
	 *            the actual log message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 */
	public static void info(String tag, String message, Object... args) {
		if (Gdx.app.getLogLevel() >= Application.LOG_INFO) // so the message
															// isn't formatted
															// unnecessarily
			Gdx.app.log(tag, Stringf.format(message, args));
	}

	/**
	 * Logs an <i>error</i> message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param tag
	 *            the tag in front of the message; is usually used to denote the
	 *            logging entity
	 * @param message
	 *            the actual error message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 */
	public static void error(String tag, String message, Object... args) {
		if (Gdx.app.getLogLevel() >= Application.LOG_ERROR) // so the message
															// isn't formatted
															// unnecessarily
			Gdx.app.error(tag, Stringf.format(message, args));
	}

	/**
	 * Logs a <i>debug</i> message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param tag
	 *            the tag in front of the message; is usually used to denote the
	 *            logging entity
	 * @param message
	 *            the actual log message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 * @see #enableDebugLogging()
	 */
	public static void debug(String tag, String message, Object... args) {
		if (Gdx.app.getLogLevel() >= Application.LOG_DEBUG) // so the message
															// isn't formatted
															// unnecessarily
			Gdx.app.debug(tag, Stringf.format(message, args));
	}

}
