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

package de.damios.guacamole.gdx.graphics;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;

import de.damios.guacamole.gdx.reflection.ReflectionUtils;

/**
 * This class allows programmatically porting GLSL shader code from version 120
 * (~ OpenGL 2.1) to version 150 (~ OpenGL 3.2).
 * <p>
 * This is useful for libraries that have to support OpenGL ES 2 and 3 at the
 * same time. The need for 150 shaders arises due to macOS only providing a 3.2
 * <i>core</i> profile, which is not backward compatible. This means that –
 * other than on Windows or Linux – if one is using OpenGL 3+ on macOS, only
 * shaders of version 150 can be compiled.
 * 
 * @author damios
 * @see <a href=
 *      "https://www.khronos.org/opengl/wiki/OpenGL_Context#OpenGL_3.2_and_Profiles">Additional
 *      information on core profiles on macOS</a>
 */
public class ShaderCompatibilityHelper {

	private ShaderCompatibilityHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts the given GLSL shader code from version 120 to version 150, if
	 * the application is run on macOS and uses OpenGL 3. Ignores any
	 * {@linkplain ShaderProgram#prependVertexCode prepends}.
	 * <p>
	 * This is useful for libraries that – other than real applications – have
	 * to support multiple use cases and therefore have to provide shaders for
	 * versions 120 and 150.
	 * 
	 * @param vert
	 *            the vertex shader code; should not contain a version statement
	 * @param frag
	 *            the fragment shader code; should not contain a version
	 *            statement
	 * @return the compiled shader
	 * @see #toVert150(String)
	 * @see #toFrag150(String)
	 * @see ShaderProgramFactory
	 */
	public static ShaderProgram fromString(String vert, String frag) {
		if (ShaderCompatibilityHelper.mustUse32CShader()) {
			vert = toVert150(vert);
			frag = toFrag150(frag);
		}

		return ShaderProgramFactory.fromString(
				getDefaultShaderVersionStatement() + vert,
				getDefaultShaderVersionStatement() + frag, true, true);
	}

	public static String toVert150(String vert120) {
		vert120 = vert120.replace("\nattribute ", "\nin ");
		vert120 = vert120.replace(" attribute ", " in ");

		vert120 = vert120.replace("\nvarying ", "\nout ");
		vert120 = vert120.replace(" varying ", " out ");

		vert120 = vert120.replace("texture2D(", "texture(");

		return vert120;
	}

	public static String toFrag150(String frag120) {
		frag120 = frag120.replace("\nattribute ", "\nout ");
		frag120 = frag120.replace(" attribute ", " out ");

		frag120 = frag120.replace("\nvarying ", "\nin ");
		frag120 = frag120.replace(" varying ", " in ");

		if (frag120.contains("gl_FragColor")) {
			frag120 = frag120.replace("void main()",
					"out vec4 fragColor; \nvoid main()");
			frag120 = frag120.replace("gl_FragColor", "fragColor");
		}

		frag120 = frag120.replace("texture2D(", "texture(");
		frag120 = frag120.replace("textureCube(", "texture(");

		return frag120;
	}

	public static boolean mustUse32CShader() {
		// TODO use PlatformUtils.isMac (see
		// https://github.com/libgdx/libgdx/pull/5960)
		return Gdx.gl30 != null && UIUtils.isMac;
	}

	public static String getDefaultShaderVersion() {
		if (ReflectionUtils.getClassByNameOrNull(
				"com.badlogic.gdx.backends.lwjgl3.awt.GlfwAWTLoader") != null)
			return "100"; // ANGLE, see
							// https://github.com/google/angle/blob/0ed0de4f0b7f5a81fbe35b28e6a68a739f365556/src/compiler/translator/DirectiveHandler.cpp#L285

		if (mustUse32CShader())
			return "150"; // macOS 3.2 core profile

		if (Gdx.app.getType() == ApplicationType.Desktop
				|| Gdx.app.getType() == ApplicationType.HeadlessDesktop)
			return "120"; // Desktop
		return "100"; // GLSL ES (Android, iOS, WebGL)
	}

	public static String getDefaultShaderVersionStatement() {
		return "#version " + getDefaultShaderVersion() + "\n";
	}

}
