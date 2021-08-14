package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.UserData;

public class TestLogin extends UnitTest {

	public TestLogin() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddRoleRequest());
		JsonObject response = TestApi(data.getAllRoleRequest());
		JsonObject roleObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Role role = gson.fromJson(roleObject, Role.class);
		assertNotNull(role);
		Message message = data.getAddUserRequest(role);
		TestApi(message);
		UserData userData = (UserData) message.getData();
		response = TestApi(data.getAllUserRequest());
		JsonObject userObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		User user = gson.fromJson(userObject, User.class);
		assertNotNull(user);
		user.setPassword(userData.getUser().getPassword());
		TestApi(data.getLoginData(user));
	}

}
