package com.sonar.vishal.medico.core.definition;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.security.Security;

public interface BusinessAdapter extends BussinessObject {

	public default void macVerification(JsonObject data) throws IllegalAccessException {
		JsonObject header = data.get(Constant.HEADER).getAsJsonObject();
		String mac = header.get(Constant.MAC).getAsString();
		header.remove(Constant.MAC);
		String dataString = data.toString();
		String newMac = Security.generateMac(dataString);
		if (!mac.equals(newMac)) {
			throw new IllegalAccessException("Invalid MAC");
		}
	}

	public default JsonObject generateFinalMessage(String data) {
		JsonObject dataObject = JsonParser.parseString(data).getAsJsonObject();
		JsonObject headerObject = dataObject.get(Constant.HEADER).getAsJsonObject();
		headerObject.remove(Constant.MAC);
		String mac = Security.generateMac(dataObject.toString());
		headerObject.addProperty(Constant.MAC, mac);
		return dataObject;
	}

	public JsonObject process(JsonObject data);
}
