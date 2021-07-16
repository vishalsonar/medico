package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;

public class TestRole extends UnitTest {

	public TestRole() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		TestApi(data.getAddRoleRequest());
		JsonObject response = TestApi(data.getAllRoleRequest());
		JsonObject roleObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Role role = gson.fromJson(roleObject, Role.class);
		assertNotNull(role);
		TestApi(data.getRoleRequest(role.getId()));
		TestApi(data.getUpdateRoleRequest(role));
		TestApi(data.getDeleteRoleRequest(role));
	}
}
