package com.sonar.vishal;

import com.sonar.vishal.ui.structure.RoleStructure;
import com.sonar.vishal.ui.util.UIConstant;
import com.vaadin.ui.VerticalLayout;

public class RoleView extends MedicoView {

	private static final long serialVersionUID = 8727917327993849281L;

	public RoleView() {
		super(UIConstant.ROLE, UIConstant.ADD_ROLE, UIConstant.UPDATE_ROLE, UIConstant.DELETE_ROLE);
	}

	@Override
	public void setUI() {
		this.structure = new RoleStructure();
		rightLayout = (VerticalLayout) structure.get();
		rightLayout.setSizeFull();
	}
}
