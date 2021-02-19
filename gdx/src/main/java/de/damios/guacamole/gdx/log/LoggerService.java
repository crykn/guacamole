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

/**
 * This class is used to {@link #getLogger(Class) obtain} and configure
 * {@link Logger}s.
 * <p>
 * Loggers have the following advantages: they offer simple to use
 * {@linkplain String#format(String, Object...) formatting} support, and the
 * (abbreviated) name of the class from wherein the logger was called is
 * printed.
 */
public class LoggerService {

	private final static Map<String, Logger> loggers = new HashMap<>();
	private static boolean abbreviateClassNames = true;

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
		return getLogger(
				abbreviateClassNames ? ClassUtils.getAbbreviatedClassName(clazz)
						: clazz.getName());
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
	 * Disables all log messages.
	 */
	public static void showNone() {
		Gdx.app.setLogLevel(Application.LOG_NONE);
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
	public static void showInfoAndErrors() {
		Gdx.app.setLogLevel(Application.LOG_INFO);
	}

	/**
	 * Enables all log messages.
	 */
	public static void showAll() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	/**
	 * @return whether debug messages are logged
	 */
	public static boolean isDebugEnabled() {
		return Gdx.app.getLogLevel() >= Application.LOG_DEBUG;
	}

	/**
	 * @return whether info messages are logged
	 */
	public static boolean isInfoEnabled() {
		return Gdx.app.getLogLevel() >= Application.LOG_INFO;
	}

	/**
	 * @return whether error messages are logged
	 */
	public static boolean isErrorEnabled() {
		return Gdx.app.getLogLevel() >= Application.LOG_ERROR;
	}

	/**
	 * Determines whether to abbreviate class names when logging.
	 * 
	 * @param abbreviateClassNames
	 */
	public static void setUseAbbreviatedClassNames(
			boolean abbreviateClassNames) {
		LoggerService.abbreviateClassNames = abbreviateClassNames;
	}

}
