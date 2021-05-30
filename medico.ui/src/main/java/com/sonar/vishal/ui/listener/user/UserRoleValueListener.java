package com.sonar.vishal.ui.listener.user;

import java.util.Arrays;
import java.util.List;

import com.mysql.cj.util.StringUtils;
import com.sonar.vishal.medico.common.pojo.Role;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;

public class UserRoleValueListener implements ValueChangeListener<String> {

	private static final long serialVersionUID = 7511205159232322696L;
	private static List<Role> roles;
	public static Role selectedRole;

	public UserRoleValueListener(Role[] roles) {
		UserRoleValueListener.roles = Arrays.asList(roles);
	}

	@Override
	public void valueChange(ValueChangeEvent<String> event) {
		String selectedOption = event.getValue();
		if (!StringUtils.isEmptyOrWhitespaceOnly(selectedOption)) {
			UserRoleValueListener.selectedRole = UserRoleValueListener.roles.stream()
																	  .filter(role -> selectedOption.equals(role.getName()))
																	  .findFirst().get();
		}
	}

}
