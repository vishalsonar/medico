package com.sonar.vishal.medico.common.message.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now {

	public static final String FORMAT = "YYYY-MM-dd'T'HH:mm";

	public static String get() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(FORMAT);
		return format.format(date);
	}

}
