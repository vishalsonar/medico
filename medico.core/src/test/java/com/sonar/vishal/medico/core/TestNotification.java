package com.sonar.vishal.medico.core;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.message.common.Message;
import com.sonar.vishal.medico.common.pojo.Notification;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.RoleData;
import com.sonar.vishal.medico.common.structure.UserData;

public class TestNotification extends UnitTest {

	private Role role;
	private User user;

	public TestNotification() throws InvalidKeyException, NoSuchAlgorithmException {
		super();
	}

	private void createRole() throws InvalidKeyException, NoSuchAlgorithmException {
		Message message = data.getAddRoleRequest();
		RoleData roleData = (RoleData) message.getData();
		roleData.getRole().setName("role2");
		TestApi(message);
		JsonObject response = TestApi(data.getAllRoleRequest());
		JsonArray responseArray = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray();
		for (JsonElement element : responseArray) {
			role = gson.fromJson(element.getAsJsonObject(), Role.class);
			if (role.getName().equals("role2")) {
				break;
			}
		}
		assertNotNull(role);
	}

	private void createUser() throws InvalidKeyException, NoSuchAlgorithmException {
		Message message = data.getAddUserRequest(role);
		UserData userData = (UserData) message.getData();
		userData.getUser().setUserName("admin2");
		userData.getUser().getRole().setId(role.getId());
		TestApi(message);
		JsonObject response = TestApi(data.getAllUserRequest());
		JsonArray responseArray = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray();
		for (JsonElement element : responseArray) {
			user = gson.fromJson(element.getAsJsonObject(), User.class);
			if (role.getName().equals("admin2")) {
				break;
			}
		}
		assertNotNull(user);
	}

	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException {
		createRole();
		createUser();
		TestApi(data.getAddNotificationRequest(user));
		JsonObject response = TestApi(data.getAllNotificationRequest(user));
		TestApi(data.getPageNotificationRequest(user));
		TestApi(data.getSearchNotificationRequest(user));
		JsonObject notificationObject = response.get(Constant.DATA).getAsJsonObject().get(Constant.LIST).getAsJsonArray().get(0).getAsJsonObject();
		Notification notification = gson.fromJson(notificationObject, Notification.class);
		assertNotNull(notification);
		TestApi(data.getNotificationRequest(notification.getId()));
		TestApi(data.getUpdateNotificationRequest(notification));
		TestApi(data.getDeleteNotificationRequest(notification));
	}

}
