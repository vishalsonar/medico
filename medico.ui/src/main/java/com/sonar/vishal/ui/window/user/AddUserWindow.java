package com.sonar.vishal.ui.window.user;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
		updateRoles();
	}
	
	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Name", "User Name", "300");
		PasswordField password = COMPONENT.getPasswordField("Password", "Password", "300");
		PasswordField confirmPassword = COMPONENT.getPasswordField("Confirm Password", "Confirm Password", "300");
		List<String> options = Arrays.asList(roles).stream().map(Role::getName).collect(Collectors.toList());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(roles);
		result = prime * result + Objects.hash(userBinder);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddUserWindow other = (AddUserWindow) obj;
		return Arrays.equals(roles, other.roles) && Objects.equals(userBinder, other.userBinder);
	}
	
}
