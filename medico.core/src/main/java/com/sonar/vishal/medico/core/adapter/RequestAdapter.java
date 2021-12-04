package com.sonar.vishal.medico.core.adapter;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.NotificationPageData;
import com.sonar.vishal.medico.common.structure.NotificationSearchData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.core.data.FunctionMap;
import com.sonar.vishal.medico.core.definition.BusinessAdapter;
import com.sonar.vishal.medico.core.definition.BusinessLogic;
import com.sonar.vishal.medico.core.logic.KeyLogic;

public class RequestAdapter implements BusinessAdapter {

	@Override
	public JsonObject process(JsonObject data) {
		Header header = null;
		Data messageData = null;
		String functionName = null;
		JsonObject dataObject = null;
		BusinessLogic logic = new KeyLogic();
		String response = getErrorMessageAsString("Invalid Function Name");
		
		try {
			macVerification(data);
			JsonObject headerObject = data.get(Constant.HEADER).getAsJsonObject();
			dataObject = data.get(Constant.DATA).getAsJsonObject();
			header = gson.fromJson(headerObject, Header.class);
			functionName = header.getFunction();
			logic = FunctionMap.getLogic(functionName);
			if (functionName.contains("Page")) {
				messageData = gson.fromJson(dataObject, PageData.class);
			}
			if (functionName.contains("Search")) {
				messageData = gson.fromJson(dataObject, SearchData.class);
			}
			if (functionName.contains("Notification")) {
				if (functionName.contains("Page")) {
					messageData = gson.fromJson(dataObject, NotificationPageData.class);
				}
				if (functionName.contains("Search")) {
					messageData = gson.fromJson(dataObject, NotificationSearchData.class);
				}
			}
			if (messageData == null) {
				messageData = logic.fromJson(dataObject);
			}
			Message responesMessage = logic.execute(functionName, messageData);
			response = gson.toJson(responesMessage);
		} catch (ClassCastException e) {
			messageData = gson.fromJson(dataObject, IdData.class);
			Message responesMessage = logic.execute(functionName, messageData);
			response = gson.toJson(responesMessage);
		} catch (Exception e) {
			response = getErrorMessageAsString(e.getMessage());
		}
		return generateFinalMessage(response);
	}
}
