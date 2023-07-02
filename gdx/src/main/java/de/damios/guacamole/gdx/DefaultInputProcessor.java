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

import com.badlogic.gdx.InputProcessor;

/**
 * An InputProcessor is used to receive input events from keyboard, mouse and
 * touch screen. Each method returns a boolean denoting whether the event was
 * handled.
 * <p>
 * Provides empty default methods to reduce boilerplate code.
 * 
 * @author damios
 */
public interface DefaultInputProcessor extends InputProcessor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean keyTyped(char character) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean touchUp(int screenX, int screenY, int pointer,
			int button) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean touchCancelled(int screenX, int screenY, int pointer,
			int button) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
