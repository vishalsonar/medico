package com.sonar.vishal.medico.core.adapter;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.structure.BillData;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.Header;
import com.sonar.vishal.medico.common.structure.IdData;
import com.sonar.vishal.medico.common.structure.LogData;
import com.sonar.vishal.medico.common.structure.LoginData;
import com.sonar.vishal.medico.common.structure.PageData;
import com.sonar.vishal.medico.common.structure.PatientData;
import com.sonar.vishal.medico.common.structure.ProductData;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.SearchData;
import com.sonar.vishal.medico.common.structure.StoreData;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.medico.core.definition.BusinessAdapter;
import com.sonar.vishal.medico.core.definition.BusinessLogic;
import com.sonar.vishal.medico.core.logic.BillLogic;
import com.sonar.vishal.medico.core.logic.KeyLogic;
import com.sonar.vishal.medico.core.logic.LogLogic;
import com.sonar.vishal.medico.core.logic.LoginLogic;
import com.sonar.vishal.medico.core.logic.PatientLogic;
import com.sonar.vishal.medico.core.logic.ProductLogic;
import com.sonar.vishal.medico.core.logic.RoleLogic;
import com.sonar.vishal.medico.core.logic.StoreLogic;
import com.sonar.vishal.medico.core.logic.UserLogic;

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
			if (functionName.contains("Patient")) {
				logic = new PatientLogic();
				messageData = gson.fromJson(dataObject, PatientData.class);
			}
			if (functionName.contains("Bill")) {
				logic = new BillLogic();
				messageData = gson.fromJson(dataObject, BillData.class);
			}
			if (functionName.contains("Login")) {
				logic = new LoginLogic();
				messageData = gson.fromJson(dataObject, LoginData.class);
			}
			if (functionName.contains("Product")) {
				logic = new ProductLogic();
				messageData = gson.fromJson(dataObject, ProductData.class);
			}
			if (functionName.contains("Role")) {
				logic = new RoleLogic();
				messageData = gson.fromJson(dataObject, RoleData.class);
			}
			if (functionName.contains("Store")) {
				logic = new StoreLogic();
				messageData = gson.fromJson(dataObject, StoreData.class);
			}
			if (functionName.contains("User")) {
				logic = new UserLogic();
				messageData = gson.fromJson(dataObject, UserData.class);
			}
			if (functionName.contains("Log") && messageData == null) {
				logic = new LogLogic();
				messageData = gson.fromJson(dataObject, LogData.class);
			}
			if (functionName.contains("Page")) {
				messageData = gson.fromJson(dataObject, PageData.class);
			}
			if (functionName.contains("Search")) {
				messageData = gson.fromJson(dataObject, SearchData.class);
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
