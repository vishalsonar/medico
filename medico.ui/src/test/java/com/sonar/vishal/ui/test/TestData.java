package com.sonar.vishal.ui.test;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {
	
	private TestData() {
		// avoid instantiation.
	}

	public static final int MAX = 30;
	
	public static final String NULL = null;
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String LONG_SPACE = "       ";
	public static final String RANDOM_STRING = RandomStringUtils.random(MAX);
	public static final String RANDOM_ALPHABETIC_STRING = RandomStringUtils.randomAlphabetic(MAX);
	public static final String RANDOM_ALPHANUMERIC_STRING = RandomStringUtils.randomAlphanumeric(MAX);
	public static final String RANDOM_NUMERIC_STRING = RandomStringUtils.randomNumeric(MAX);
}
