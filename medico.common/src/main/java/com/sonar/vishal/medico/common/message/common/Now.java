package com.sonar.vishal.medico.common.message.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {

	public static final String FORMAT = "yyyy-MM-dd'T'HH:mm";

	private Now() {
		throw new IllegalStateException("Utility class");
	}

	public static String get() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		return format.format(date);
	}

}
