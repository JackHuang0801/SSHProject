/**
 * Project Name:SSHProject
 * File Name:Assert.java
 * Package Name:com.saas.luna.JackFramework.commons.util
 * Date:2015年6月11日下午4:59:32
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.JackFramework.commons.util;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName:Assert
 * Date:     2015年6月11日 下午4:59:32
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */
public abstract class Assert {
	public static void isTrue(boolean expression, String message) {
		if (!(expression))
			throw new IllegalArgumentException(message);
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	public static void isNull(Object object, String message) {
		if (object != null)
			throw new IllegalArgumentException(message);
	}

	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}

	public static void notNull(Object object, String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not null");
	}

	public static void hasLength(String text, String message) {
		if (!(StringUtils.hasLength(text)))
			throw new IllegalArgumentException(message);
	}

	public static void hasLength(String text) {
		hasLength(text,
				"[Assertion failed] - this String argument must have length; it must not be <code>null</code> or empty");
	}

	public static void hasText(String text, String message) {
		if (!(StringUtils.hasText(text)))
			throw new IllegalArgumentException(message);
	}

	public static void hasText(String text) {
		hasText(text,
				"[Assertion failed] - this String argument must have text; it must not be <code>null</code>, empty, or blank");
	}

	public static void doesNotContain(String textToSearch, String substring, String message) {
		if ((StringUtils.hasLength(textToSearch)) && (StringUtils.hasLength(substring))
				&& (textToSearch.indexOf(substring) != -1))
			throw new IllegalArgumentException(message);
	}

	public static void doesNotContain(String textToSearch, String substring) {
		doesNotContain(
				textToSearch,
				substring,
				new StringBuilder()
						.append("[Assertion failed] - this String argument must not contain the substring [")
						.append(substring).append("]").toString());
	}

	public static void notEmpty(Object[] array, String message) {
		if (ObjectUtils.isEmpty(array))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Collection collection, String message) {
		if (CollectionUtils.isEmpty(collection))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Collection collection) {
		notEmpty(collection,
				"[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Map map, String message) {
		if (CollectionUtils.isEmpty(map))
			throw new IllegalArgumentException(message);
	}

	public static void notEmpty(Map map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static void isInstanceOf(Class clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	public static void isInstanceOf(Class type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!(type.isInstance(obj)))
			throw new IllegalArgumentException(new StringBuilder().append(message).append("Object of class [")
					.append((obj != null) ? obj.getClass().getName() : "null").append("] must be an instance of ")
					.append(type).toString());
	}

	public static void isAssignable(Class superType, Class subType) {
		isAssignable(superType, subType, "");
	}

	public static void isAssignable(Class superType, Class subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if ((subType == null) || (!(superType.isAssignableFrom(subType))))
			throw new IllegalArgumentException(new StringBuilder().append(message).append(subType)
					.append(" is not assignable to ").append(superType).toString());
	}

	public static void state(boolean expression, String message) {
		if (!(expression))
			throw new IllegalStateException(message);
	}

	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - this state invariant must be true");
	}
}

