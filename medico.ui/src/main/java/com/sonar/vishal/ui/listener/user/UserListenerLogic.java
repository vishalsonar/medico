package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.pojo.Password;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.BiListenerLogic;
import com.sonar.vishal.ui.exception.MedicoValidationException;
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
		}
		user.setRole(UserRoleValueListener.selectedRole);
		data.setUser(user);
		new UserDataValidation(password.getConfirmPassword()).doValidation(data);
		return data;
	}

}
