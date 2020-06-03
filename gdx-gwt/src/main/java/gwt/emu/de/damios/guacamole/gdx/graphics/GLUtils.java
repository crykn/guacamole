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

package de.damios.guacamole.gdx.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtGL20;
import com.badlogic.gdx.backends.gwt.GwtGraphics;
import com.badlogic.gdx.graphics.GL20;
import com.google.gwt.webgl.client.WebGLFramebuffer;
import com.google.gwt.webgl.client.WebGLRenderingContext;

/**
 * OpenGL utilities.
 * 
 * @author damios
 */
public class GLUtils {

	/**
	 * The buffer used internally. A size of 64 bytes is required as at most 16
	 * integer elements can be returned.
	 */
	private static final IntBuffer USED_INT_BUFF = ByteBuffer
			.allocateDirect(16 * Integer.BYTES).order(ByteOrder.nativeOrder())
			.asIntBuffer();

	/**
	 * Returns the name of the currently bound frame buffer.
	 * <p>
	 * All credit for the gwt implementation of this method goes to <a href=
	 * "https://github.com/crashinvaders/gdx-vfx/blob/master/gdx-vfx/gwt/src/com/crashinvaders/vfx/gwt/GwtVfxGlExtension.java#L35-L68">metaphore</a>!
	 * 
	 * @return returns the name of the currently bound frame buffer. The initial
	 *         value is {@code 0}, indicating the default frame buffer.
	 */
	public static synchronized int getBoundFboHandle() {
		GwtGraphics graphics = (GwtGraphics) Gdx.graphics;
		GwtGL20 gwtGl = (GwtGL20) graphics.getGL20();
		WebGLRenderingContext glContext = graphics.getContext();
		WebGLFramebuffer frameBuffer = glContext
				.getParametero(WebGLRenderingContext.FRAMEBUFFER_BINDING);

		if (frameBuffer == null) {
			return 0;
		} else {
			return getFrameBufferId(gwtGl, frameBuffer);
		}
	}

	private static native int getFrameBufferId(GwtGL20 gwtGl,
			WebGLFramebuffer frameBuffer)
	/*-{
	// Access GwtGL20#frameBuffers field.
	var frameBuffers = gwtGl.@com.badlogic.gdx.backends.gwt.GwtGL20::frameBuffers;
	// Check if frame buffer ID was cached previously.
	if (frameBuffer.frameBufferId) {
	    return frameBuffer.frameBufferId;
	}
	// Lookup for ID through entire LibGDX GWT frame buffer index.
	for (i = 0; i < frameBuffers.length; i++) {
	    if (frameBuffer === frameBuffers[i]) {
	        // Cache frame buffer ID inside the javascript object.
	        frameBuffer.frameBufferId = i;
	        return i;
	    }
	}
	throw "Failed to find frame buffer ID."
	}-*/;

	/**
	 * @return the current gl viewport ({@code GL_VIEWPORT}) as an array,
	 *         containing four values: the x and y window coordinates of the
	 *         viewport, followed by its width and height.
	 */
	public static synchronized int[] getViewport() {
		IntBuffer intBuf = USED_INT_BUFF;
		Gdx.gl.glGetIntegerv(GL20.GL_VIEWPORT, intBuf);

		return new int[] { intBuf.get(0), intBuf.get(1), intBuf.get(2),
				intBuf.get(3) };
	}

}
