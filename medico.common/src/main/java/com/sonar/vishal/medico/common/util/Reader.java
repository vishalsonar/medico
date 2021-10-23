package com.sonar.vishal.medico.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Reader {

	private Reader() {
		throw new IllegalStateException("Utility class");
	}

	public static Properties loadProperties(Object object, String fileName) {
		Properties properties = new Properties();
		try {
			InputStream stream = object.getClass().getClassLoader().getResourceAsStream(fileName);
			properties.load(stream);
		} catch (IOException e) {
			// Do Nothing. will return new Properties.
		}
		return properties;
	}
}
