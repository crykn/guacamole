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

package de.damios.guacamole.gdx.log;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import de.damios.guacamole.ClassUtils;
import text.formic.Stringf;

/**
 * This class is used to {@link #getLogger(Class) obtain} and configure
 * {@link Logger}s.
 * <p>
 * Loggers have the following advantages:
 * <ul>
 * <li>they offer simple to use {@linkplain String#format(String, Object...)
 * formatting} support</li>
 * <li>the ({@linkplain #setUseAbbreviatedClassNames(boolean) abbreviated} and
 * {@linkplain #setPadClassNames(boolean) padded}) name of the class from
 * wherein the logger was called is printed</li>
 * </ul>
 */
public class LoggerService {

	public enum LogLevel {

		/**
		 * Log nothing.
		 */
		NONE(0),
		/**
		 * Log errors which are fatal to the operation or application.
		 */
		ERROR(1),
		/**
		 * Log potentially harmful situations.
		 */
		WARN(1),
		/**
		 * Log generally useful information.
		 */
		INFO(2),
		/**
		 * Log information useful for debugging.
		 */
		DEBUG(3),
		/**
		 * Log fine-grained information useful for debugging parts of the
		 * application.
		 */
		TRACE(3);

		private int libgdxLevel;

		private LogLevel(int libgdxLevel) {
			this.libgdxLevel = libgdxLevel;
		}

	}

	private static LogLevel logLevel = LogLevel.INFO;

	private final static Map<String, Logger> loggers = new HashMap<>();
	private static boolean abbreviateClassNames = true;
	private static int minClassNameLength = 34;
	private static int maxClassNameLength = 34;

	private LoggerService() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Used to obtain a {@link Logger} for the specified class.
	 * 
	 * @param clazz
	 *            The class from wherein the logger is called. The class's name
	 *            is logged as well.
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		String className = abbreviateClassNames
				? ClassUtils.getAbbreviatedClassName(clazz)
				: clazz.getName();

		if (minClassNameLength > 0 || maxClassNameLength > 0) {
			className = Stringf.format(
					"%-" + minClassNameLength + "." + maxClassNameLength + "s",
					className);
		}

		return getLogger(className);
	}

	private static Logger getLogger(String name) {
		Logger logger = loggers.get(name);
		if (logger != null)
			return logger;
		else {
			logger = new Logger(name);
			loggers.put(name, logger);
			return logger;
		}
	}

	/**
	 * Set the log level of all {@link Logger}s. This also changes the
	 * {@linkplain Application#setLogLevel(int) libGDX log level} to match it.
	 * 
	 * @param level
	 */
	public static void setLogLevel(LogLevel level) {
		Gdx.app.setLogLevel(level.libgdxLevel);
		logLevel = level;
	}

	/**
	 * @return whether {@linkplain LogLevel#TRACE trace} messages are logged
	 */
	public static boolean isTraceEnabled() {
		return logLevel.ordinal() >= LogLevel.TRACE.ordinal();
	}

	/**
	 * @return whether {@linkplain LogLevel#DEBUG debug} messages are logged
	 */
	public static boolean isDebugEnabled() {
		return logLevel.ordinal() >= LogLevel.DEBUG.ordinal();
	}

	/**
	 * @return whether {@linkplain LogLevel#INFO info} messages are logged
	 */
	public static boolean isInfoEnabled() {
		return logLevel.ordinal() >= LogLevel.INFO.ordinal();
	}

	/**
	 * @return whether {@linkplain LogLevel#WARN warn} messages are logged
	 */
	public static boolean isWarnEnabled() {
		return logLevel.ordinal() >= LogLevel.WARN.ordinal();
	}

	/**
	 * @return whether {@linkplain LogLevel#ERROR error} messages are logged
	 */
	public static boolean isErrorEnabled() {
		return logLevel.ordinal() >= LogLevel.ERROR.ordinal();
	}

	/**
	 * Determines whether newly obtained loggers should abbreviate their class
	 * names. E.g.:
	 * 
	 * <pre>
	* [ERROR] [c.b.g.m.MyGdxGame]: something went wrong!
	* [ERROR] [com.badlogic.gdx.mygame.MyGdxGame]: something went wrong!
	 * </pre>
	 * 
	 * @param abbreviateClassNames
	 */
	public static void setUseAbbreviatedClassNames(
			boolean abbreviateClassNames) {
		LoggerService.abbreviateClassNames = abbreviateClassNames;
	}

	/**
	 * Determines whether newly obtained loggers should pad their class names.
	 * E.g.:
	 * 
	 * <pre>
	* [ERROR] [c.b.g.m.MyGdxGame]: something went wrong!
	* [ERROR] [c.b.g.m.MyGdxGame       ]: something went wrong!
	* [ERROR] [c.b.g.m.i.MyInputProcess]: something went wrong!
	 * </pre>
	 * 
	 * Set both parameters to {@code 0} to disable padding.
	 * 
	 * @param minClassNameLength
	 * @param maxClassNameLength
	 */
	public static void setPadClassNames(int minClassNameLength,
			int maxClassNameLength) {
		LoggerService.minClassNameLength = minClassNameLength;
		LoggerService.maxClassNameLength = maxClassNameLength;
	}

}
