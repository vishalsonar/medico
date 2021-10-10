package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.UserData;

public class TestLogin extends UnitTest {

	public TestLogin() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		Role role = null;
		Message message = data.getAddRoleRequest();
		RoleData roleData = (RoleData) message.getData();
		roleData.getRole().setName("role1");
		TestApi(message);
		JsonObject response = TestApi(data.getAllRoleRequest());
		JsonArray responseArray = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray();
		for (JsonElement element : responseArray) {
			role = gson.fromJson(element.getAsJsonObject(), Role.class);
			if (role.getName().equals("role1")) {
				break;
			}
		}
		assertNotNull(role);
		message = data.getAddUserRequest(role);
		UserData userData = (UserData) message.getData();
		userData.getUser().setUserName("admin1");
		userData.getUser().getRole().setId(role.getId());
		TestApi(message);
		TestApi(data.getLoginData(userData.getUser()));
	}

}
