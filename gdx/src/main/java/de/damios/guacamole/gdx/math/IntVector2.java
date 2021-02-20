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

package de.damios.guacamole.gdx.math;

import java.io.Serializable;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public final class IntVector2 implements Serializable, Vector<IntVector2> {

	private static final long serialVersionUID = 1L;
	
	/** The x-component of this vector. **/
	public int x;
	/** The y-component of this vector. **/
	public int y;

	/**
	 * Constructs a new vector at (0,0).
	 */
	public IntVector2() {
	}

	/**
	 * Constructs a vector with the given components.
	 * 
	 * @param x
	 *            The x-component
	 * @param y
	 *            The y-component
	 */
	public IntVector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructs a vector from the given vector
	 * 
	 * @param v
	 *            The vector
	 */
	public IntVector2(IntVector2 v) {
		set(v);
	}

	@Override
	public IntVector2 cpy() {
		return new IntVector2(this);
	}

	@Override
	public float len() {
		return (float) Math.sqrt(x * x + y * y);
	}

	@Override
	public float len2() {
		return x * x + y * y;
	}

	@Override
	public IntVector2 limit(float limit) {
		return limit2(limit * limit);
	}

	@Override
	public IntVector2 limit2(float limit2) {
		float len2 = len2();
		if (len2 > limit2) {
			return scl((float) Math.sqrt(limit2 / len2));
		}
		return this;
	}

	@Override
	public IntVector2 setLength(float len) {
		return setLength2(len * len);
	}

	@Override
	public IntVector2 setLength2(float len2) {
		float oldLen2 = len2();
		return (oldLen2 == 0 || oldLen2 == len2) ? this
				: scl((float) Math.sqrt(len2 / oldLen2));
	}

	@Override
	public IntVector2 clamp(float min, float max) {
		final float len2 = len2();
		if (len2 == 0f)
			return this;
		float max2 = max * max;
		if (len2 > max2)
			return scl((float) Math.sqrt(max2 / len2));
		float min2 = min * min;
		if (len2 < min2)
			return scl((float) Math.sqrt(min2 / len2));
		return this;
	}

	@Override
	public IntVector2 set(IntVector2 v) {
		x = v.x;
		y = v.y;
		return this;
	}

	public IntVector2 set(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public IntVector2 sub(IntVector2 v) {
		x -= v.x;
		y -= v.y;
		return this;
	}

	public IntVector2 sub(int x, int y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	@Override
	public IntVector2 nor() {
		float len = len();
		if (len != 0) {
			x /= len;
			y /= len;
		}
		return this;
	}

	@Override
	public IntVector2 add(IntVector2 v) {
		x += v.x;
		y += v.y;
		return this;
	}

	public IntVector2 add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	@Override
	public float dot(IntVector2 v) {
		return x * v.x + y * v.y;
	}

	@Override
	public IntVector2 scl(float scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}

	public IntVector2 scl(int x, int y) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	@Override
	public IntVector2 scl(IntVector2 v) {
		this.x *= v.x;
		this.y *= v.y;
		return this;
	}

	@Override
	public float dst(IntVector2 v) {
		final float x_d = v.x - x;
		final float y_d = v.y - y;
		return (float) Math.sqrt(x_d * x_d + y_d * y_d);
	}

	@Override
	public float dst2(IntVector2 v) {
		final float x_d = v.x - x;
		final float y_d = v.y - y;
		return x_d * x_d + y_d * y_d;
	}

	@Override
	public IntVector2 lerp(IntVector2 target, float alpha) {
		final float invAlpha = 1.0f - alpha;
		this.x = (int) ((x * invAlpha) + (target.x * alpha));
		this.y = (int) ((y * invAlpha) + (target.y * alpha));
		return this;
	}

	@Override
	public IntVector2 interpolate(IntVector2 target, float alpha,
			Interpolation interpolation) {
		return lerp(target, interpolation.apply(alpha));
	}

	@Override
	public IntVector2 setToRandomDirection() {
		float theta = MathUtils.random(0f, MathUtils.PI2);
		return this.set((int) MathUtils.cos(theta), (int) MathUtils.sin(theta));
	}

	@Override
	public boolean isUnit() {
		return isUnit(0.000000001f);
	}

	@Override
	public boolean isUnit(float margin) {
		return Math.abs(len2() - 1f) < margin;
	}

	@Override
	public boolean isZero() {
		return x == 0 && y == 0;
	}

	@Override
	public boolean isZero(float margin) {
		return len2() < margin;
	}

	@Override
	public boolean isOnLine(IntVector2 other, float epsilon) {
		return MathUtils.isZero(x * other.y - y * other.x, epsilon);
	}

	@Override
	public boolean isOnLine(IntVector2 other) {
		return MathUtils.isZero(x * other.y - y * other.x);
	}

	@Override
	public boolean isCollinear(IntVector2 other, float epsilon) {
		return isOnLine(other, epsilon) && dot(other) > 0f;
	}

	@Override
	public boolean isCollinear(IntVector2 other) {
		return isOnLine(other) && dot(other) > 0f;
	}

	@Override
	public boolean isCollinearOpposite(IntVector2 other, float epsilon) {
		return isOnLine(other, epsilon) && dot(other) < 0f;
	}

	@Override
	public boolean isCollinearOpposite(IntVector2 other) {
		return isOnLine(other) && dot(other) < 0f;
	}

	@Override
	public boolean isPerpendicular(IntVector2 other) {
		return MathUtils.isZero(dot(other));
	}

	@Override
	public boolean isPerpendicular(IntVector2 other, float epsilon) {
		return MathUtils.isZero(dot(other), epsilon);
	}

	@Override
	public boolean hasSameDirection(IntVector2 other) {
		return dot(other) > 0;
	}

	@Override
	public boolean hasOppositeDirection(IntVector2 other) {
		return dot(other) < 0;
	}

	@Override
	public boolean epsilonEquals(IntVector2 other, float epsilon) {
		if (other == null)
			return false;
		if (Math.abs(other.x - x) > epsilon)
			return false;
		if (Math.abs(other.y - y) > epsilon)
			return false;
		return true;
	}

	@Override
	public IntVector2 mulAdd(IntVector2 v, float scalar) {
		this.x += v.x * scalar;
		this.y += v.y * scalar;
		return this;
	}

	@Override
	public IntVector2 mulAdd(IntVector2 v, IntVector2 mulVec) {
		this.x += v.x * mulVec.x;
		this.y += v.y * mulVec.y;
		return this;
	}

	@Override
	public IntVector2 setZero() {
		this.x = 0;
		this.y = 0;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector2 other = (Vector2) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
