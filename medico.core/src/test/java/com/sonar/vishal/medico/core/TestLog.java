package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.sonar.vishal.medico.common.message.common.Message;

public class TestLog extends UnitTest {

	public TestLog() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddLogRequest());
		Message response = data.getAllLogRequest();
		assertNotNull(response);
		TestApi(data.getLogRequest());
	}

}
