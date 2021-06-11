package com.sonar.vishal.ui.util;

import java.util.regex.Pattern;

public class UIUtil {

	public static final String STRING_REGEX = "[a-zA-Z]*";
	public static final String NUMERIC_REGEX = "\\d*";
	public static final String ALPHA_NUMERIC_REGEX = "\\w*";

	public static boolean isValidString(String data) {
		boolean result = false;
		if (data != null && data.length() > 0) {
			if (!data.trim().equals(UIConstant.EMPTY)) {
				result = true;
			}
		}
		return result;
	}

	public static boolean isValidStringOfN(String data, int length) {
		boolean result = false;
		if (isValidString(data)) {
			result = data.length() == length;
		}
		return result;
	}

	public static boolean isString(String data) {
		boolean result = false;
		if (isValidString(data)) {
			result = Pattern.matches(STRING_REGEX, data);
		}
		return result;
	}

	public static boolean isString(String data, int length) {
		boolean result = false;
		if (isString(data)) {
			result = data.length() == length;
		}
		return result;
	}

	public static boolean isNumericString(String data) {
		boolean result = false;
		if (isValidString(data)) {
			result = Pattern.matches(NUMERIC_REGEX, data);
		}
		return result;
	}

	public static boolean isNumericStringOfN(String data, int length) {
		boolean result = false;
		if (isNumericString(data)) {
			result = data.length() == length;
		}
		return result;
	}

	public static boolean isAlphaNumericString(String data) {
		boolean result = false;
		if (isValidString(data)) {
			result = Pattern.matches(ALPHA_NUMERIC_REGEX, data);
		}
		return result;
	}

	public static boolean isAlphaNumericStringOfN(String data, int length) {
		boolean result = false;
		if (isAlphaNumericString(data)) {
			result = data.length() == length;
		}
		return result;
	}
}
