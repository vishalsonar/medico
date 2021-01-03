package com.sonar.vishal.medico.core.adapter;

import java.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.security.Security;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;
import com.sonar.vishal.medico.common.structure.KeyData;
import com.sonar.vishal.medico.core.definition.BusinessAdapter;
import com.sonar.vishal.medico.core.definition.BusinessLogic;
import com.sonar.vishal.medico.core.logic.KeyLogic;

public class KeyRequestAdapter implements BusinessAdapter {
	
	public KeyRequestAdapter() {
		Security.init();
	}

	@Override
	public JsonObject process(JsonObject data) {
		BusinessLogic logic = null;
		String response = getErrorMessageAsString("Invalid Function Name");
		
		try {
			Message message = gson.fromJson(data, Message.class);
			Header header = message.getHeader();
			String functionName = header.getFunction();
			if (functionName.equals(Constant.GET_KEY)) {
				logic = new KeyLogic();
				KeyData keyData = new KeyData();
				String key = Base64.getEncoder().encodeToString(Security.getKey().getEncoded());
				keyData.setKey(key);
				Data messageData = keyData;
				Message responesMessage = logic.execute(functionName, messageData);
				response = gson.toJson(responesMessage);
			}
		} catch (Exception e) {
			response = getErrorMessageAsString(e.getMessage());
		}
		return JsonParser.parseString(response).getAsJsonObject();
	}
}
