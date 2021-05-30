package com.sonar.vishal.ui.window.user;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sonar.vishal.medico.common.message.common.Constant;
import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.medico.common.pojo.User;
import com.sonar.vishal.ui.backend.RestBackend;
import com.sonar.vishal.ui.listener.user.AddUserListener;
import com.sonar.vishal.ui.listener.user.UserRoleValueListener;
import com.sonar.vishal.ui.structure.UserStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class AddUserWindow extends MedicoWindow {

	private static final long serialVersionUID = 7007173310923942341L;
	private Role[] roles;
	private Binder<User> userBinder = new Binder<>();

	public AddUserWindow(UserStructure structure) {
		super("Add User", structure);
		this.structure = structure;
		updateRoles();
	}
	
	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Name", "User Name", "300");
		PasswordField password = COMPONENT.getPasswordField("Password", "Password", "300");
		PasswordField confirmPassword = COMPONENT.getPasswordField("Confirm Password", "Confirm Password", "300");
		List<String> options = Arrays.asList(roles).stream().map(role -> role.getName()).collect(Collectors.toList());
		RadioButtonGroup<String> optionGroup = COMPONENT.getRadioGroupList("Role Options", options);
		optionGroup.addValueChangeListener(new UserRoleValueListener(roles));
		userBinder.bind(name, User::getUserName, User::setUserName);
		userBinder.bind(password, User::getPassword, User::setPassword);
		addComponents(name, password, confirmPassword, optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddUserListener(userBinder, this, structure));
	}
	
	public void updateRoles() {
		RestBackend backend = new RestBackend(Constant.GET_ROLE_LIST);
		roles = (Role[]) backend.doPostRespondData(Role[].class);
	}
}
