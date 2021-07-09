package com.sonar.vishal.ui.listener.user;

import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.medico.common.structure.Data;
import com.sonar.vishal.medico.common.structure.UserData;
import com.sonar.vishal.ui.definition.ListenerLogic;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;

public class UserListenerLogic implements ListenerLogic<Binder<User>> {

	@Override
	public Data process(Binder<User> binder, Integer id) throws Exception {
		User user = new User();
		UserData data = new UserData();
		binder.writeBean(user);
		if (id != null) {
			user.setId(id);
		}
		if (UserRoleValueListener.selectedRole == null) {
			throw new ValidationException(null, null);
		}
		user.setRole(UserRoleValueListener.selectedRole);
		data.setUser(user);
		return data;
	}

}
