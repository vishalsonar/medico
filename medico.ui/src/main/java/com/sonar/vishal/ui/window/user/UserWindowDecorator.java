package com.sonar.vishal.ui.window.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.listener.user.UserRoleValueListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class UserWindowDecorator {

	Role[] roles;
	TextField name;
	PasswordField password;
	PasswordField confirmPassword;
	RadioButtonGroup<String> optionGroup;
	Binder<User> userBinder = new Binder<>();
	
	public UserWindowDecorator() {
		RestBackend backend = new RestBackend(Constant.GET_ROLE_LIST);
		roles = (Role[]) backend.doPostRespondData(Role[].class);
		Component component = Component.getInstance();
		name = component.getTextField(UIConstant.NAME, UIConstant.USER_NAME, UIConstant.FIELD_LENGTH_300);
		password = component.getPasswordField(UIConstant.PASSWORD, UIConstant.PASSWORD, UIConstant.FIELD_LENGTH_300);
		confirmPassword = component.getPasswordField(UIConstant.CONFIRM_PASSWORD, UIConstant.CONFIRM_PASSWORD, UIConstant.FIELD_LENGTH_300);
		List<String> options = Arrays.asList(roles).stream().map(Role::getName).collect(Collectors.toList());
		optionGroup = component.getRadioGroupList(UIConstant.ROLE_OPTIONS, options);
		optionGroup.addValueChangeListener(new UserRoleValueListener(roles));
		userBinder.bind(name, User::getUserName, User::setUserName);
		userBinder.bind(password, User::getPassword, User::setPassword);
	}
}
