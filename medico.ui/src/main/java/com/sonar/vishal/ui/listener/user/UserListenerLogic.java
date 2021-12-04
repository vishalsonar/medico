package com.sonar.vishal.ui.listener.user;

import com.google.gson.JsonObject;
import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.rest.Backend;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.BiListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
import com.sonar.vishal.ui.util.UIConstant;
import com.sonar.vishal.ui.util.UIUtil;
import com.sonar.vishal.ui.validator.UserDataValidation;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UserListenerLogic implements BiListenerLogic<Binder<User>, Binder<Password>> {

	@Override
	public Data process(Binder<User> parentBinder, Binder<Password> childBinder, Integer id) throws ValidationException, MedicoValidationException {
		User user = new User();
		UserData data = new UserData();
		Password password = new Password();
		parentBinder.writeBean(user);
		childBinder.writeBean(password);
		if (id != null) {
			user.setId(id);
		} else {
			user.setLoginAttempt(0);
		}
		user.setRole(UserRoleValueListener.getSelectedRole());
		data.setUser(user);
		new UserDataValidation(password.getConfirmPassword()).doValidation(data);
		if (id == null) {
			JsonObject responseObject = (JsonObject) UIUtil.getSearchBackend(Constant.SEARCH_USER, user.getUserName()).doPostRespondData(User[].class);
			User[] userList = Backend.gson.fromJson(responseObject.get(Constant.LIST).getAsJsonArray(), User[].class);
			for (User entry : userList) {
				if (entry.getUserName().equals(user.getUserName())) {
					throw new MedicoValidationException(UIConstant.USER_NAME_EXIST);
				}
			}
		}
		return data;
	}

}
