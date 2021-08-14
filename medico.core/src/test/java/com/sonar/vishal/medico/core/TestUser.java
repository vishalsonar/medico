package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;

public class TestUser extends UnitTest {

	public TestUser() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void testRoleAndUser() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddRoleRequest());
		JsonObject response = TestApi(data.getAllRoleRequest());
		JsonObject roleObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Role role = gson.fromJson(roleObject, Role.class);
		assertNotNull(role);
		TestApi(data.getAddUserRequest(role));
		response = TestApi(data.getAllUserRequest());
		TestApi(data.getPageUserRequest());
		JsonObject userObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		User user = gson.fromJson(userObject, User.class);
		assertNotNull(user);
		TestApi(data.getUserRequest(user.getId()));
		TestApi(data.getUpdateUserRequest(user));
		TestApi(data.getDeleteUserRequest(user));
	}

}
