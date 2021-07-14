package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.security.Security;
import com.sonar.vishal.medico.common.structure.KeyData;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTest extends TestCase {

	protected Api api;
	protected UnitTestData data;
	protected static Gson gson;

	static {
		gson = new Gson();
	}

	public UnitTest() throws InvalidKeyException, NoSuchAlgorithmException {
		Security.init();
		api = new Api();
		data = new UnitTestData();
	}

	public String getMac(Message message) {
		String messageString = gson.toJson(message);
		JsonObject messageData = JsonParser.parseString(messageString).getAsJsonObject();
		JsonObject header = messageData.get(Constant.HEADER).getAsJsonObject();
		header.remove(Constant.MAC);
		String mac = Security.generateMac(messageString);
		return mac;
	}

	@Before
	public void TestKeyApi() {
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
			Security.init(key);
		} catch (Exception e) {

		}
	}

	public JsonObject TestApi(Message message) throws InvalidKeyException, NoSuchAlgorithmException {
		data = new UnitTestData();
		String mac = getMac(message);
		message.getHeader().setMac(mac);
		String requestString = gson.toJson(message);
		assertNotNull(requestString);
		Response response = api.processRequest(requestString);
		JsonObject responseObject = JsonParser.parseString(response.getEntity().toString()).getAsJsonObject();
		String responseString = responseObject.get(Constant.HEADER).getAsJsonObject().get("Result").getAsString();
		assertEquals("Success", responseString);
		return responseObject;
	}
	
	@Test
	public void testEmpty() {
		assertNotNull(Security.getKey());
	}
}
