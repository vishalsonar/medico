package com.sonar.vishal.medico.common.rest;

import java.security.Key;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.security.Security;
import com.sonar.vishal.medico.common.structure.Header;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestBackend implements Backend {

	private String url;

	static {
		Security.init();
	}

	public void setKey(Key key) {
		Security.init(key);
	}

	public RestBackend(String functionName) {
		init(functionName);
		url = Constant.REQUEST_URL;
		if (functionName.equals(Constant.GET_KEY)) {
			url = Constant.KEY_URL;
		}
	}

	private String getMacRequest() {
		JsonObject object = JsonParser.parseString(gson.toJson(message)).getAsJsonObject();
		object.get(Constant.HEADER).getAsJsonObject().remove(Constant.MAC);
		String mac = Security.generateMac(object.toString());
		object.get(Constant.HEADER).getAsJsonObject().addProperty(Constant.MAC, mac);
		return object.toString();
	}

	private ClientResponse doPost() {
		try {
			String request = getMacRequest();
			WebResource webResource = client.resource(url);
			return webResource.type(Constant.APPLICATION_JSON).post(ClientResponse.class, request);
		} catch (ClientHandlerException e) {
			ClientResponse response = new ClientResponse(0, null, null, null);
			response.setStatus(404);
			return response;
		}
	}

	private boolean responseHeader(JsonObject responseObject) {
		boolean result = false;
		JsonObject headerObject = responseObject.get(Constant.HEADER).getAsJsonObject();
		Header header = gson.fromJson(headerObject, Header.class);
		if (Constant.SUCCESS.equals(header.getResult())) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean doPostRespondHeader() {
		boolean result = false;
		ClientResponse response = doPost();
		if (response.getStatus() == 200) {
			String responseString = response.getEntity(String.class);
			JsonObject responseObject = JsonParser.parseString(responseString).getAsJsonObject();
			boolean isMac = verifyMac(responseObject);
			if (isMac) {
				result = responseHeader(responseObject);
			}
		}
		return result;
	}

	@Override
	public Object doPostRespondData(Class<?> clazz) {
		Object data = null;
		ClientResponse response = doPost();
		if (response.getStatus() == 200) {
			String responseString = response.getEntity(String.class);
			JsonObject responseObject = JsonParser.parseString(responseString).getAsJsonObject();
			boolean isMac = verifyMac(responseObject);
			if (isMac) {
				JsonObject responseData = responseObject.get(Constant.DATA).getAsJsonObject();
				if (responseData.has(Constant.LIST)) {
					data = responseData;
				} else {
					data = gson.fromJson(responseData, clazz);
				}
			}
		}
		return data;
	}

	private boolean verifyMac(JsonObject object) {
		boolean state = true;
		boolean isGetKey = message.getHeader().getFunction().equals(Constant.GET_KEY);
		if (!isGetKey) {
			JsonObject header = object.get(Constant.HEADER).getAsJsonObject();
			String mac = header.get(Constant.MAC).getAsString();
			header.remove(Constant.MAC);
			String newMac = Security.generateMac(object.toString());
			state = mac.equals(newMac);
		}
		return state;
	}
}
