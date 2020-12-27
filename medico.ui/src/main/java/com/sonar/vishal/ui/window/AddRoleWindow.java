package com.sonar.vishal.ui.window;

import com.vaadin.ui.TextField;

public class AddRoleWindow extends MedicoWindow {

	private static final long serialVersionUID = 791231962125024417L;

	public AddRoleWindow() {
		super("Add Role");
	}
	
	@Override
	public void setWindow() {
		TextField name = COMPONENT.getTextField("Namea", "Role Name", "300");
		TextField option = COMPONENT.getTextField("Optioan", "Option", "300");
		addComponents(name, option);
		addAction();
		addCancelListener(this);
	}
}
