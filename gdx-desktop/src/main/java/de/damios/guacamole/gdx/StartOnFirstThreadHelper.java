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

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import org.jspecify.annotations.Nullable;
import org.lwjgl.system.macosx.LibC;

import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;

/**
 * Adds some utilities to ensure that the JVM was started with the
 * {@code -XstartOnFirstThread} argument, which is required on macOS for LWJGL 3
 * to function.
 * 
 * @author damios
 * @see <a href=
 *      "https://jvm-gaming.org/t/starting-jvm-on-mac-with-xstartonfirstthread-programmatically/57547">Based
 *      on https://jvm-gaming.org/t/-/57547</a>
 *
 */
public class StartOnFirstThreadHelper {

	private static final String JVM_RESTARTED_ARG = "jvmIsRestarted";

	private StartOnFirstThreadHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Starts a new JVM if the application was started on macOS without the
	 * {@code -XstartOnFirstThread} argument. Returns whether a new JVM was
	 * started and thus no code should be executed.
	 * <p>
	 * <u>Usage:</u>
	 * 
	 * <pre>
	 * public static void main(String... args) {
	 * 	if (StartOnFirstThreadHelper.startNewJvmIfRequired(true, args)) {
	 * 		return; // don't execute any code
	 * 	}
	 * 	// the actual main method code
	 * }
	 * </pre>
	 * 
	 * @param redirectOutput
	 *            whether the output of the new JVM should be rerouted to the
	 *            new JVM, so it can be accessed in the same place; keeps the
	 *            old JVM running if enabled
	 * @param args
	 *            the start arguments passed to the main method of the Java
	 *            application
	 * @return whether a new JVM was started and thus no code should be executed
	 *         in this one
	 * 
	 * @see #executeOnValidJVM(Runnable)
	 * @see #executeOnValidJVM(Runnable, String...)
	 */
	public static boolean startNewJvmIfRequired(boolean redirectOutput,
			@Nullable String... args) {
		/* The -XstartOnFirstThread argument is only needed on macOS */
		if (!UIUtils.isMac) {
			return false;
		}

		/* There is no need for -XstartOnFirstThread on Graal native image */
		if (!System.getProperty("org.graalvm.nativeimage.imagecode", "")
				.isEmpty()) {
			return false;
		}

		/* Check whether -XstartOnFirstThread is enabled */
		long pid = LibC.getpid();
		if ("1".equals(System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" + pid))) {
			return false;
		}

		/* Check whether the JVM was previously restarted to avoid looping */
		if ("true".equals(System.getProperty(JVM_RESTARTED_ARG))) {
			System.err.println(
					"The JVM was restarted, but the -XstartOnFirstThread argument could not be set.");
			return false;
		}

		/* Restart the JVM with -XstartOnFirstThread */
		ArrayList<String> command = new ArrayList<>();
		String separator = System.getProperty("file.separator");
		// TODO Java 9: ProcessHandle.current().info().command();
		String javaExecPath = System.getProperty("java.home") + separator
				+ "bin" + separator + "java";
		if (!(new File(javaExecPath)).exists()) {
			// The java installation needed for restarting the JVM could not be
			// determined
			System.err.println(
					"A Java installation could not be found. If you are distributing this app with a bundled JRE, be sure to set the -XstartOnFirstThread argument manually!");
			return false;
		}
		command.add(javaExecPath);
		command.add("-XstartOnFirstThread");
		command.add("-D" + JVM_RESTARTED_ARG + "=true");
		command.addAll(
				ManagementFactory.getRuntimeMXBean().getInputArguments());
		command.add("-cp");
		command.add(System.getProperty("java.class.path"));
		String mainClass = System.getenv("JAVA_MAIN_CLASS_" + pid);
		if (mainClass == null) {
			// Sometimes the main class cannot be determined, so we'll have to
			// fall back to this workaround
			StackTraceElement trace[] = Thread.currentThread().getStackTrace();
			if (trace.length > 0) {
				mainClass = trace[trace.length - 1].getClassName();
			} else {
				System.err.println("The main class could not be determined.");
				return false;
			}
		}
		command.add(mainClass);
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				command.add(args[i]);
			}
		}

		try {
			if (!redirectOutput) {
				ProcessBuilder processBuilder = new ProcessBuilder(command);
				processBuilder.start();
			} else {
				Process process = (new ProcessBuilder(command))
						.redirectErrorStream(true).start();
				BufferedReader processOutput = new BufferedReader(
						new InputStreamReader(process.getInputStream()));
				String line;

				while ((line = processOutput.readLine()) != null) {
					System.out.println(line);
				}

				process.waitFor();
			}
		} catch (Exception e) {
			System.err.println("There was a problem restarting the JVM");
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Starts a new JVM if required; otherwise executes the main method code
	 * given as Runnable. When used with lambdas, this is allows for less
	 * verbose code than {@link #startNewJvmIfRequired()}:
	 * 
	 * <pre>
	 * public static void main(String... args) {
	 * 	StartOnFirstThreadHelper.executeIfJVMValid(() -> {
	 * 		// the actual main method code
	 * 	}, args);
	 * }
	 * </pre>
	 * 
	 * @param mainMethodCode
	 * @param args
	 *            the start arguments passed to the main method of the Java
	 *            application
	 * @see #startNewJvmIfRequired(boolean, String...)
	 * @see #executeOnValidJVM(Runnable)
	 */
	public static void executeOnValidJVM(Runnable mainMethodCode,
			String... args) {
		if (startNewJvmIfRequired(true, args)) {
			return;
		}
		mainMethodCode.run();
	}

	/**
	 * Starts a new JVM if required; otherwise executes the main method code
	 * given as Runnable. When used with lambdas, this is allows for less
	 * verbose code than {@link #startNewJvmIfRequired()}:
	 * 
	 * <pre>
	 * public static void main(String... args) {
	 * 	StartOnFirstThreadHelper.executeIfJVMValid(() -> {
	 * 		// the actual main method code
	 * 	});
	 * }
	 * </pre>
	 * 
	 * @param mainMethodCode
	 * @see #startNewJvmIfRequired(boolean)
	 * @see #executeOnValidJVM(Runnable)
	 */
	public static void executeOnValidJVM(Runnable mainMethodCode) {
		executeOnValidJVM(mainMethodCode, (String[]) null);
	}

}
