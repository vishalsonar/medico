package com.sonar.vishal.ui.definition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;
import com.sun.jersey.api.client.Client;

public interface Backend {

	public static final Message message = new Message();
	public static final Client client = Client.create();
	public static final Gson gson = new Gson();

	public default void init(String functionName) {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Header header = new Header();
		header.setDateTime(date);
		header.setType(Constant.REQUEST);
		header.setUuid(UUID.randomUUID().toString());
		header.setFunction(functionName);
		header.setResult(Constant.SUCCESS);
		header.setMessage(Constant.SUCCESS);
		message.setHeader(header);
		message.setData(new Data());
	}

	public boolean doPostRespondHeader();

	public Object doPostRespondData(Class<?> clazz);
}
