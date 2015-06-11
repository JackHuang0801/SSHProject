/**
 * Project Name:SSHProject
 * File Name:StringUtils.java
 * Package Name:com.saas.luna.JackFramework.commons.util
 * Date:2015年6月11日下午5:00:55
 * Copyright (c) 2015, yehuang.happy@163.com All Rights Reserved.
 *
*/

package com.saas.luna.JackFramework.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * ClassName:StringUtils
 * Date:     2015年6月11日 下午5:00:55
 * @author   Jack.Huang
 * @version  V1.0
 * @since    JDK 1.8.0_45
 */

public abstract class StringUtils {
	private static final String FOLDER_SEPARATOR = "/";
	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
	private static final String TOP_PATH = "..";
	private static final String CURRENT_PATH = ".";
	private static final char EXTENSION_SEPARATOR = 46;

	public static boolean hasLength(String str) {
		return ((str != null) && (str.length() > 0));
	}

	public static boolean hasText(String str) {
		if (!(hasLength(str))) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; ++i) {
			if (!(Character.isWhitespace(str.charAt(i)))) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsWhitespace(String str) {
		if (!(hasLength(str))) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; ++i) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public static String trimWhitespace(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(0)))) {
			buf.deleteCharAt(0);
		}
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(buf.length() - 1)))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	public static String trimLeadingWhitespace(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(0)))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	public static String trimTrailingWhitespace(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(buf.length() - 1)))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	public static String trimAllWhitespace(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			}

			++index;
		}

		return buf.toString();
	}

	public static String trimRedundantInbetweenWhitespace(String str) {
		if (!(hasLength(str))) {
			return str;
		}

		StringBuffer buf = new StringBuffer(str);

		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(0)))) {
			buf.deleteCharAt(0);
		}
		while ((buf.length() > 0) && (Character.isWhitespace(buf.charAt(buf.length() - 1)))) {
			buf.deleteCharAt(buf.length() - 1);
		}

		int index = 0;
		int mark = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				if (mark == 0) {
					mark = 1;
					++index;
				}

				buf.deleteCharAt(index);

			}

			mark = 0;
			++index;
		}

		return buf.toString();
	}

	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if ((str == null) || (prefix == null)) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if ((str == null) || (suffix == null)) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	public static int countOccurrencesOf(String str, String sub) {
		if ((str == null) || (sub == null) || (str.length() == 0) || (sub.length() == 0)) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if ((oldPattern == null) || (newPattern == null)) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();

		int pos = 0;
		int index = inString.indexOf(oldPattern);

		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		return sbuf.toString();
	}

	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	public static String deleteAny(String inString, String charsToDelete) {
		if ((inString == null) || (charsToDelete == null)) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); ++i) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}

	public static String quote(String str) {
		return ((str != null) ? "'" + str + "'" : null);
	}

	public static Object quoteIfString(Object obj) {
		return ((obj instanceof String) ? quote((String) obj) : obj);
	}

	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if ((str == null) || (str.length() == 0)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf("/");
		return ((separatorIndex != -1) ? path.substring(separatorIndex + 1) : path);
	}

	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(46);
		return ((sepIndex != -1) ? path.substring(sepIndex + 1) : null);
	}

	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(46);
		return ((sepIndex != -1) ? path.substring(0, sepIndex) : path);
	}

	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf("/");
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!(relativePath.startsWith("/"))) {
				newPath = newPath + "/";
			}
			return newPath + relativePath;
		}

		return relativePath;
	}

	public static String cleanPath(String path) {
		String pathToUse = replace(path, "\\", "/");

		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			pathToUse = pathToUse.substring(prefixIndex + 1);
		}

		String[] pathArray = delimitedListToStringArray(pathToUse, "/");
		List pathElements = new LinkedList();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; --i) {
			if (".".equals(pathArray[i])) {
				continue;
			}
			if ("..".equals(pathArray[i])) {
				++tops;

			} else if (tops > 0) {
				--tops;
			} else {
				pathElements.add(0, pathArray[i]);

			}

		}

		for (int i = 0; i < tops; ++i) {
			pathElements.add(0, "..");
		}

		return prefix + collectionToDelimitedString(pathElements, "/");
	}

	public static boolean pathEquals(String path1, String path2) {
		return cleanPath(path1).equals(cleanPath(path2));
	}

	public static Locale parseLocaleString(String localeString) {
		String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
		String language = (parts.length > 0) ? parts[0] : "";
		String country = (parts.length > 1) ? parts[1] : "";
		String variant = (parts.length > 2) ? parts[2] : "";
		return ((language.length() > 0) ? new Locale(language, country, variant) : null);
	}

	public static String[] addStringToArray(String[] array, String str) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[] { str };
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	public static boolean contains(String[] array, String str, boolean ignoreCase) {
		if ((array != null) && (array.length > 0)) {
			if (ignoreCase) {
				for (String strInArray : array) {
					if (strInArray.equalsIgnoreCase(str)) {
						return true;
					}
				}

			} else {
				for (String strInArray : array) {
					if (strInArray.equals(str)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		List result = new ArrayList();
		result.addAll(Arrays.asList(array1));
		for (int i = 0; i < array2.length; ++i) {
			String str = array2[i];
			if (!(result.contains(str))) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	public static String[] intersectStringArrays(String[] array1, String[] array2, boolean ignoreCase) {
		if ((ObjectUtils.isEmpty(array1)) && (ObjectUtils.isEmpty(array2))) {
			return null;
		}
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}

		List result = new ArrayList();

		for (int i = 0; i < array1.length; ++i) {
			String strInArray1 = array1[i];
			if (contains(array2, strInArray1, ignoreCase)) {
				result.add(strInArray1);
			}
		}

		return toStringArray(result);
	}

	public static String[] sortStringArray(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return ((String[]) (String[]) collection.toArray(new String[collection.size()]));
	}

	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return array;
		}
		Set set = new TreeSet();
		for (int i = 0; i < array.length; ++i) {
			set.add(array[i]);
		}
		return toStringArray(set);
	}

	public static String[] split(String toSplit, String delimiter) {
		if ((!(hasLength(toSplit))) || (!(hasLength(delimiter)))) {
			return null;
		}
		int offset = toSplit.indexOf(delimiter);
		if (offset < 0) {
			return null;
		}
		String beforeDelimiter = toSplit.substring(0, offset);
		String afterDelimiter = toSplit.substring(offset + delimiter.length());
		return new String[] { beforeDelimiter, afterDelimiter };
	}

	public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
		return splitArrayElementsIntoProperties(array, delimiter, null);
	}

	public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {
		if (ObjectUtils.isEmpty(array)) {
			return null;
		}
		Properties result = new Properties();
		for (int i = 0; i < array.length; ++i) {
			String element = array[i];
			if (charsToDelete != null) {
				element = deleteAny(array[i], charsToDelete);
			}
			String[] splittedElement = split(element, delimiter);
			if (splittedElement == null) {
				continue;
			}
			result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
		}
		return result;
	}

	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
			boolean ignoreEmptyTokens) {
		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if ((!(ignoreEmptyTokens)) || (token.length() > 0)) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}

	public static String[] delimitedListToStringArray(String str, String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}
		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); ++i)
				result.add(str.substring(i, i + 1));
		} else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if ((str.length() > 0) && (pos <= str.length())) {
				result.add(str.substring(pos));
			}
		}
		return toStringArray(result);
	}

	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	public static Set commaDelimitedListToSet(String str) {
		Set set = new TreeSet();
		String[] tokens = commaDelimitedListToStringArray(str);
		for (int i = 0; i < tokens.length; ++i) {
			set.add(tokens[i]);
		}
		return set;
	}

	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext())
				;
			sb.append(delim);
		}

		return sb.toString();
	}

	public static String collectionToDelimitedString(Collection coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}

	public static String collectionToCommaDelimitedString(Collection coll) {
		return collectionToDelimitedString(coll, ",");
	}

	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
}

