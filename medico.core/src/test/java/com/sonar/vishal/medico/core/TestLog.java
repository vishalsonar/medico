package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class TestLog extends UnitTest {

	public TestLog() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddLogRequest());
		TestApi(data.getAllLogRequest());
		TestApi(data.getLogRequest());
	}

}
