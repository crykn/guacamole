package de.damios.guacamole.gdx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

import org.lwjgl.system.macosx.LibC;

import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;

/**
 * Adds some utilities to ensure that the JVM was started with the
 * {@code -XstartOnFirstThread} argument, which is required on Mac OS for LWJGL
 * 3 to function.
 * 
 * @author damios
 * @see <a href=
 *      "http://www.java-gaming.org/topics/starting-jvm-on-mac-with-xstartonfirstthread-programmatically/37697/view.html">Inspired
 *      by http://www.java-gaming.org/topics/-/37697/view.html</a>
 *
 */
public class StartOnFirstThreadHelper {

	private static final String JVM_RESTARTED_ARG = "jvmIsRestarted";

	private StartOnFirstThreadHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns whether the JVM is restarting. This is the case if the application
	 * was started on Mac OS without the {@code -XstartOnFirstThread} argument.
	 * <p>
	 * <u>Usage:</u>
	 * 
	 * <pre>
	 * public static void main(String... args) {
	 * 	if (StartOnFirstThreadHelper.isJVMRestarting()) {
	 * 		return; // don't execute any code
	 * 	}
	 * 	// the actual main method code
	 * }
	 * </pre>
	 * 
	 * @return whether the JVM is restarting and thus no code should be executed
	 */
	public static boolean isJVMRestarting() {
		if (!UIUtils.isMac) {
			return false;
		}

		long pid = LibC.getpid();

		// check whether -XstartOnFirstThread is enabled
		if ("1".equals(System.getenv("JAVA_STARTED_ON_FIRST_THREAD_" + pid))) {
			return false;
		}

		// check whether the JVM was previously restarted
		// avoids looping, but most certainly leads to a crash
		if ("true".equals(System.getProperty(JVM_RESTARTED_ARG))) {
			System.err.println(
					"There was a problem evaluating whether the JVM was started with the -XstartOnFirstThread argument");
			return false;
		}

		// Restart the JVM with -XstartOnFirstThread
		ArrayList<String> jvmArgs = new ArrayList<String>();
		String separator = System.getProperty("file.separator");
		jvmArgs.add(System.getProperty("java.home") + separator + "bin" + separator + "java");
		jvmArgs.add("-XstartOnFirstThread");
		jvmArgs.add("-D" + JVM_RESTARTED_ARG + "=true");
		jvmArgs.addAll(ManagementFactory.getRuntimeMXBean().getInputArguments());
		jvmArgs.add("-cp");
		jvmArgs.add(System.getProperty("java.class.path"));
		jvmArgs.add(System.getenv("JAVA_MAIN_CLASS_" + pid));

		try {
			Process process = (new ProcessBuilder(jvmArgs)).redirectErrorStream(true).start();
			BufferedReader processOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = processOutput.readLine()) != null) {
				System.out.println(line);
			}

			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * An alternative to {@link #isJVMRestarting()} that takes the actual main
	 * method code as a Runnable. Is easily readable when used with lambdas.
	 * 
	 * @param mainMethodCode
	 */
	public static void executeIfJVMValid(Runnable mainMethodCode) {
		if (isJVMRestarting()) {
			return;
		}
		mainMethodCode.run();
	}

}
