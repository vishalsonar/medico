package com.sonar.vishal.ui.window.role;

import com.sonar.vishal.medico.common.pojo.Role;
import com.sonar.vishal.ui.listener.role.AddRoleListener;
import com.sonar.vishal.ui.listener.role.RoleOptionValueListener;
import com.sonar.vishal.ui.structure.RoleStructure;
import com.sonar.vishal.ui.window.MedicoWindow;
import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.TextField;

public class AddRoleWindow extends MedicoWindow {

	private static final long serialVersionUID = 791231962125024417L;
	private Binder<Role> roleBinder = new Binder<>();

	public AddRoleWindow(RoleStructure structure) {
		super("Add Role", structure);
		this.structure = structure;
	}

	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Name", "Role Name", "300");
		String[] options = { "Login", "Option", "Bill", "Product", "Patient", "Store", "User", "Role" };
		CheckBoxGroup<String> optionGroup = COMPONENT.getCheckBoxGroup("Options", options);
		optionGroup.addValueChangeListener(new RoleOptionValueListener());
		roleBinder.bind(name, Role::getName, Role::setName);
		addComponents(name, optionGroup);
		addAction();
		addCancelListener(this);
		addSubmitListener(new AddRoleListener(roleBinder, this, structure));
	}
}
