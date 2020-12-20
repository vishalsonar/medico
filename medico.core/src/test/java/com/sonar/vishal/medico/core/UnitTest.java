package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.security.Security;
import com.sonar.vishal.medico.common.structure.KeyData;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class UnitTest extends TestCase {

	private Api api;
	private UnitTestData data;
	private static Security security;
	private static Gson gson;

	static {
		gson = new Gson();
	}

	public UnitTest() throws InvalidKeyException, NoSuchAlgorithmException {
		security = Security.getIntance().init();
		api = new Api();
		data = new UnitTestData();
	}

	private String getMac(Message message) {
		String messageString = gson.toJson(message);
		JsonObject messageData = JsonParser.parseString(messageString).getAsJsonObject();
		JsonObject header = messageData.get(Constant.HEADER).getAsJsonObject();
		header.remove(Constant.MAC);
		String mac = security.generateMac(messageString);
		return mac;
	}

	private void TestKeyApi() {
		String requestString = gson.toJson(data.getKeyRequest());
		assertNotNull(requestString);
		Response response = api.processKeyRequest(requestString);
		assertNotNull(response);
		String responseString = response.getEntity().toString();
		JsonObject messageObject = JsonParser.parseString(responseString).getAsJsonObject();
		JsonObject dataObject = messageObject.get(Constant.DATA).getAsJsonObject();
		KeyData keyData = new Gson().fromJson(dataObject, KeyData.class);
		byte[] decodedKey = Base64.getDecoder().decode(keyData.getKey());
		Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		try {
			security = Security.getIntance().init(key);
		} catch (Exception e) {

		}
	}

	@Test
	public void TestApi() {
		TestKeyApi();
		Message message = data.getAllStoreRequest();
		String mac = getMac(message);
		message.getHeader().setMac(mac);
		String requestString = gson.toJson(message);
		assertNotNull(requestString);
		Response response = api.processRequest(requestString);
		System.out.println(response.getEntity().toString());
	}
}
