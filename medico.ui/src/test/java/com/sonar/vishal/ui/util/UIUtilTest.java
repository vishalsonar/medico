package com.sonar.vishal.ui.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.sonar.vishal.ui.test.TestData;

@RunWith(JUnit4.class)
public class UIUtilTest {

	@Test
	public void testIsValidString() {
		boolean result = UIUtil.isValidString(TestData.NULL);
		assertFalse(result);
		result = UIUtil.isValidString(TestData.EMPTY);
		assertFalse(result);
		result = UIUtil.isValidString(TestData.SPACE);
		assertFalse(result);
		result = UIUtil.isValidString(TestData.LONG_SPACE);
		assertFalse(result);
		result = UIUtil.isValidString(TestData.RANDOM_STRING);
		assertTrue(result);
		result = UIUtil.isValidStringOfN(TestData.RANDOM_STRING, TestData.MAX);
		assertTrue(result);
	}

	@Test
	public void testIsNumericString() {
		boolean result = UIUtil.isNumericString(TestData.RANDOM_NUMERIC_STRING);
		assertTrue(result);
		result = UIUtil.isNumericStringOfN(TestData.RANDOM_NUMERIC_STRING, TestData.MAX);
		assertTrue(result);
	}

	@Test
	public void testIsAlphaNumericString() {
		boolean result = UIUtil.isAlphaNumericString(TestData.RANDOM_ALPHANUMERIC_STRING);
		assertTrue(result);
		result = UIUtil.isAlphaNumericStringOfN(TestData.RANDOM_ALPHANUMERIC_STRING, TestData.MAX);
		assertTrue(result);
		assertFalse(UIUtil.isAlphaNumericString(TestData.NULL));
	}

	@Test
	public void testIsString() {
		boolean result = UIUtil.isString(TestData.RANDOM_ALPHABETIC_STRING);
		assertTrue(result);
		result = UIUtil.isString(TestData.RANDOM_ALPHABETIC_STRING, TestData.MAX);
		assertTrue(result);
		assertFalse(UIUtil.isString(TestData.NULL));
	}

	@Test
	public void testIsAddressLineString() {
		boolean result = UIUtil.isAddressLineString(TestData.RANDOM_ALPHANUMERIC_STRING + "  ,-.()" + " l");
		assertTrue(result);
		assertFalse(UIUtil.isAlphaNumericString(TestData.NULL));
	}
	
	@Test
	public void testIsAlphaNumericSpaceString() {
		boolean result = UIUtil.isAlphaNumericSpaceString(TestData.RANDOM_ALPHANUMERIC_STRING + "  " + " l");
		assertTrue(result);
		assertFalse(UIUtil.isAlphaNumericString(TestData.NULL));
	}
}
