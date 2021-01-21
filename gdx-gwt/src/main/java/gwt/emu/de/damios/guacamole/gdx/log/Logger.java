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

package gwt.emu.de.damios.guacamole.gdx.log;

import java.util.Formatter;

import com.badlogic.gdx.Gdx;

import de.damios.guacamole.gdx.log.LoggerService;
import text.formic.Stringf;

/**
 * A logger for libGDX. Can be obtained via
 * {@link LoggerService#getLogger(Class)}. Adds support for formatting
 * ({@link String#format(String, Object...)}).
 * <p>
 * For example: calling {@code logger.error("something went %s!", "wrong")}
 * leads to the following output:
 * 
 * <pre>
 * [ERROR] [c.b.g.m.MyGdxGame]: something went wrong!
 * </pre>
 * 
 * @author damios
 */
public class Logger {

	private static String classPrefix;

	Logger(String className) {
		this.classPrefix = Stringf.format("[%s]: ", className);
	}

	/**
	 * Logs an informational message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param message
	 *            the log message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 */
	public void info(String message, Object... args) {
		if (LoggerService.isInfoEnabled()) // so the message isn't formatted
											// unnecessarily
			Gdx.app.log("INFO ", classPrefix + Stringf.format(message, args));
	}

	/**
	 * Logs an <i>error</i> message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param message
	 *            the error message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 */
	public void error(String message, Object... args) {
		if (LoggerService.isErrorEnabled()) // so the message isn't formatted
											// unnecessarily
			Gdx.app.error("ERROR", classPrefix + Stringf.format(message, args));
	}

	/**
	 * Logs a <i>debug</i> message. The message is formatted via
	 * {@link Stringf#format(String, Object...)}.
	 *
	 * @param message
	 *            the log message
	 * @param args
	 *            the arguments referenced by the format specifiers in the
	 *            message string. If there are more arguments than format
	 *            specifiers, the extra arguments are ignored. The number of
	 *            arguments is variable and may be zero.
	 * @see Formatter
	 */
	public void debug(String message, Object... args) {
		if (LoggerService.isDebugEnabled()) // so the message isn't formatted
											// unnecessarily
			Gdx.app.debug("DEBUG", classPrefix + Stringf.format(message, args));
	}

}
