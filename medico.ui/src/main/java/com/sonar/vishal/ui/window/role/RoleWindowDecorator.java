package com.sonar.vishal.ui.window.role;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.ui.component.Component;
import com.sonar.vishal.ui.listener.role.RoleOptionValueListener;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.TextField;

public class RoleWindowDecorator {

	private static final String[] ROLE_OPTIONS_LIST = { "Login", "Option", "Bill", "Product", "Patient", "Store", "User", "Role" };
	TextField name;
	CheckBoxGroup<String> optionGroup;
	Binder<Role> roleBinder = new Binder<>();
	
	public RoleWindowDecorator() {
		Component component = Component.getInstance();
		name = component.getTextField(UIConstant.NAME, UIConstant.ROLE_NAME, UIConstant.FIELD_LENGTH_300);
		optionGroup = component.getCheckBoxGroup(UIConstant.OPTIONS, ROLE_OPTIONS_LIST);
		optionGroup.addValueChangeListener(new RoleOptionValueListener());
		roleBinder.bind(name, Role::getName, Role::setName);
	}
}
