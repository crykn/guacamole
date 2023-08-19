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

package de.damios.guacamole.gdx.reflection;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.badlogic.gdx.utils.reflect.Annotation;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import de.damios.guacamole.Preconditions;
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

	public static @Nullable Class<?> getClassByName(String name) {
		try {
			return ClassReflection.forName(name);
		} catch (ReflectionException e) {
			LOG.debug(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Creates a class via libGDX's reflection. Returns {@code null} if the
	 * reflection or instantiation fails.
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static @Nullable <T> T newInstanceOrNull(Class<T> clazz) {
		try {
			return (T) ClassReflection.newInstance(clazz);
		} catch (ReflectionException e) {
			LOG.debug(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Creates a class via libGDX's reflection by using the classes' name.
	 * Returns {@code null} if the reflection or instantiation fails.
	 * 
	 * @param <T>
	 * @param className
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static @Nullable <T> T newInstanceOrNull(String className,
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
	 * Creates a class via libGDX reflection by using its name. Returns
	 * {@code defaultValue} if the reflection or instantiation fails.
	 * 
	 * @param <T>
	 * @param className
	 * @param clazz
	 * @param parameterTypes
	 *            an array of the constructor's parameter types
	 * @param args
	 *            an array of objects that should be handed to the constructor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static @Nullable <T> T newInstanceWithParamsOrNull(String className,
			Class<T> clazz, Class[] parameterTypes, Object[] args) {
		try {
			return newInstanceWithParamsOrNull(
					ClassReflection.getConstructor(
							ClassReflection.forName(className), parameterTypes),
					parameterTypes, args);
		} catch (ReflectionException e) {
			LOG.debug(e.getLocalizedMessage());
			return null;
		}

	}

	/**
	 * Creates a class via libGDX reflection by using one of its constructors.
	 * Returns {@code defaultValue} if the reflection or instantiation fails.
	 * 
	 * @param <T>
	 * @param className
	 * @param clazz
	 * @param parameterTypes
	 *            an array of the constructor's parameter types
	 * @param args
	 *            an array of objects that should be handed to the constructor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static @Nullable <T> T newInstanceWithParamsOrNull(
			Constructor constructor, Class[] parameterTypes, Object[] args) {
		try {
			return (T) constructor.newInstance(args);
		} catch (ReflectionException e) {
			LOG.debug(e.getLocalizedMessage());
			return null;
		}
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

	public static <A extends java.lang.annotation.Annotation> @Nullable A getAnnotationObject(
			Field field, Class<A> clazz) {
		Annotation annotation = field.getDeclaredAnnotation(clazz);
		if (annotation == null)
			return null;

		return annotation.getAnnotation(clazz);
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
			Class<? extends java.lang.annotation.Annotation> annotationClass,
			Class<?> clazz) {
		return findAnnotatedMethods(annotationClass, clazz, null);
	}

	public static Iterable<Method> findAnnotatedMethods(
			Class<? extends java.lang.annotation.Annotation> annotationClass,
			Class<?> clazz, @Nullable Predicate<Method> predicate) {
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

	public static boolean areMethodsEqual(Method m1, Method m2) {
		// libGDX's Method class doesn't implement an equals() method
		Preconditions.checkNotNull(m1);
		Preconditions.checkNotNull(m2);

		if ((m1.getDeclaringClass() == m2.getDeclaringClass())
				&& (m1.getName() == m2.getName())) {
			if (!m1.getReturnType().equals(m2.getReturnType()))
				return false;
			/* Avoid unnecessary cloning */
			Class<?>[] params1 = m1.getParameterTypes();
			Class<?>[] params2 = m2.getParameterTypes();
			if (params1.length == params2.length) {
				for (int i = 0; i < params1.length; i++) {
					if (params1[i] != params2[i])
						return false;
				}
				return true;
			}
		}
		return false;
	}

}
