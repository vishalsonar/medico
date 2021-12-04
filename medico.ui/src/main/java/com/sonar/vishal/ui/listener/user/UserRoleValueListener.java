package com.sonar.vishal.ui.listener.user;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.util.StringUtils;
import com.sonar.vishal.medico.common.pojo.Role;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;

public class UserRoleValueListener implements ValueChangeListener<String> {

	private static final long serialVersionUID = 7511205159232322696L;
	private static List<Role> roles;
	private static Role selectedRole;

	public static List<Role> getRoles() {
		return roles;
	}

	public static void setRoles(List<Role> roles) {
		UserRoleValueListener.roles = roles;
	}

	public static Role getSelectedRole() {
		return selectedRole;
	}

	public static void setSelectedRole(Role selectedRole) {
		UserRoleValueListener.selectedRole = selectedRole;
	}

	public UserRoleValueListener(Role[] roles) {
		setRoles(Arrays.asList(roles));
	}

	@Override
	public void valueChange(ValueChangeEvent<String> event) {
		String selectedOption = event.getValue();
		if (!StringUtils.isEmptyOrWhitespaceOnly(selectedOption)) {
			Optional<Role> optionalRole = UserRoleValueListener.roles.stream()
															   .filter(role -> selectedOption.equals(role.getName()))
															   .findFirst();
			if (optionalRole.isPresent()) {
				setSelectedRole(optionalRole.get());
			}
		}
	}

}
