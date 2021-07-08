package com.sonar.vishal;

import com.sonar.vishal.ui.structure.UserStructure;
import com.vaadin.ui.VerticalLayout;

public class UserView extends MedicoView {

	private static final long serialVersionUID = 5950750741381506759L;

	public UserView() {
		super("User", "Add User", "Update User", "Delete User");
	}
	
	@Override
	public void setUI() {
		this.structure = new UserStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
