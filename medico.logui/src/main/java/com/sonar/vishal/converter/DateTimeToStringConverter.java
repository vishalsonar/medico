package com.sonar.vishal.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.sonar.vishal.medico.common.message.common.Now;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class DateTimeToStringConverter implements Converter<LocalDateTime, String> {

	private static final long serialVersionUID = 3474970671617652772L;

	@Override
	public Result<String> convertToModel(LocalDateTime value, ValueContext context) {
		if (value == null) {
			return Result.ok(null);
		}
		return Result.ok(value.toString());
	}

	@Override
	public LocalDateTime convertToPresentation(String value, ValueContext context) {
		try {
			Date date = new SimpleDateFormat(Now.FORMAT).parse(value);
			return Instant.ofEpochMilli(date.getTime()).atZone(ZoneOffset.systemDefault()).toLocalDateTime();
		} catch (ParseException e) {
			return null;
		}
	}

}