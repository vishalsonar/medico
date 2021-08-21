package com.sonar.vishal.medico.core.definition;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.sonar.vishal.medico.common.Hibernate;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;

public interface BussinessObject {

	public static final Gson gson = new Gson();
	public static final Message message = new Message();
	public static final Hibernate hibernate = Hibernate.getInstance();

	public default Header getHeader() {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Header header = new Header();
		header.setDateTime(date);
		return header;
	}

	public default void setSucessMessage(String functionName) {
		Header header = getHeader();
		header.setFunction(functionName);
		header.setResult(Constant.SUCCESS);
		header.setMessage(Constant.SUCCESS);
		message.setHeader(header);
	}

	public default void setErrorMessage(String functionName, String errorMessage) {
		Header header = getHeader();
		header.setFunction(functionName);
		header.setResult(Constant.ERROR);
		header.setMessage(errorMessage);
		message.setHeader(header);
	}

	public default String getErrorMessageAsString(String errorMessage) {
		setErrorMessage(Constant.NULL, errorMessage);
		message.getHeader().setType(Constant.RESPONSE);
		message.setData(new Data());
		return gson.toJson(message);
	}
}
