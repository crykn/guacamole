/*
 * Copyright 2020 eskalon
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

package de.damios.guacamole.reflection;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.FieldConverter;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import de.damios.guacamole.gdx.log.Logger;
import de.damios.guacamole.gdx.log.LoggerService;

/**
 * Utility methods for dealing with reflection.
 * 
 * @author damios
 */
public class ReflectionUtils {

	private static final Logger LOG = LoggerService
			.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts a {@link java.lang.reflect.Field} to a
	 * {@link com.badlogic.gdx.utils.reflect.Field}.
	 * 
	 * @param field
	 * @return
	 */
	public static Field convertFieldObject(java.lang.reflect.Field field) {
		return FieldConverter.convertFieldObject(field);
	}

	/**
	 * Creates a class via libGDX reflection by using its name. Returns
	 * {@code null} if the reflection or instantiation fails.
	 * 
	 * @param <T>
	 * @param className
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static @Nullable <T> T newInstance(String className,
			Class<T> clazz) {
		try {
			return (T) ClassReflection
					.newInstance(ClassReflection.forName(className));
		} catch (ReflectionException e) {
			LOG.debug(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * @param method
	 * @return the {@linkplain Object#hashCode() hash code} for the method's
	 *         first parameter
	 */
	public static int computeParameterHashCode(Method method) {
		Class<?> parameterClass = method.getParameterTypes()[0];
		return parameterClass.getName().hashCode();
	}

	/**
	 * Finds all super classes and interfaces for the given class.
	 *
	 * @param clazz
	 *            the event class
	 * @return a list of subscriber methods
	 */
	public static Set<Class<?>> retrieveAllSuperTypes(Class<?> clazz) {
		Set<Class<?>> allSuperTypes = new LinkedHashSet<>();
		while (clazz != null) {
			allSuperTypes.add(clazz);
			try {
				for (Class<?> interfaceType : ClassReflection
						.getInterfaces(clazz)) {
					allSuperTypes.addAll(retrieveAllSuperTypes(interfaceType));
				}
			} catch (RuntimeException e) {
				if (LoggerService.isInfoEnabled())
					LOG.info("Cannot retrieve the types of '" + clazz
							+ "'. Skipping it for now.");
			}
			clazz = clazz.getSuperclass();
		}
		return Collections.unmodifiableSet(allSuperTypes);
	}

	/**
	 * Finds all methods within {@code clazz} and its super types annotated with
	 * {@code annotationClass}.
	 * 
	 * @param annotationClass
	 * @param clazz
	 * @return
	 */
	public static Iterable<Method> findAnnotatedMethods(
			Class<? extends Annotation> annotationClass, Class<?> clazz) {
		return findAnnotatedMethods(annotationClass, clazz, null);
	}

	public static Iterable<Method> findAnnotatedMethods(
			Class<? extends Annotation> annotationClass, Class<?> clazz,
			Predicate<Method> predicate) {
		Map<Integer, Method> subscribingMethods = new HashMap<>();
		Set<Class<?>> allSuperTypes = retrieveAllSuperTypes(clazz);
		for (Class<?> type : allSuperTypes) {
			for (Method method : ClassReflection.getDeclaredMethods(type)) {
				if (method.isAnnotationPresent(annotationClass)) {
					if (predicate == null || predicate.test(method)) {
						int hashCode = Objects.hash(method.getName(),
								method.getParameterTypes());
						if (!subscribingMethods.containsKey(hashCode)) {
							subscribingMethods.put(hashCode, method);
						}
					}
				}
			}
		}
		return Collections.unmodifiableCollection(subscribingMethods.values());
	}

}