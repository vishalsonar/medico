package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;

public class UnitTestData {

	private Message message;
	private Header header;
	private String date;

	public UnitTestData() throws InvalidKeyException, NoSuchAlgorithmException {
		message = new Message();
		header = new Header();
		date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		header.setDateTime(date);
		header.setMessage(Constant.SUCCESS);
		header.setResult(Constant.SUCCESS);
		header.setType(Constant.REQUEST);
		header.setUuid(UUID.randomUUID().toString());
		message.setHeader(header);
	}

	public Message getAllStoreRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_STORE_LIST);
		return message;
	}

	public Message getKeyRequest() {
		message.setData(new Data());
		message.getHeader().setFunction(Constant.GET_KEY);
		return message;
	}
}
